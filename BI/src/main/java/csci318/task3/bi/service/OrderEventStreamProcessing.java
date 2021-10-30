package csci318.task3.bi.service;

/* This class computes a stream of brand quantities
 * and creates state stores for interactive queries.
 */

import csci318.task3.bi.model.*;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Printed;
import org.apache.kafka.streams.state.KeyValueStore;
import org.hibernate.criterion.Order;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerde;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Configuration
public class OrderEventStreamProcessing {

    public final static String PRODUCT_STATE_SOLD = "product-sold";

    @Bean
    public Function<KStream<?, OrderEvent>, KStream<String, QuantitySummary>> process() {
        return inputStream -> {
            inputStream.map((k, v) -> {
                Long customerId = v.getCustomerId();
                String productName = v.getProductName();
                Long quantity = v.getQuantity();
                double price = v.getPrice();
                OrderEvent orderEvent = new OrderEvent(customerId, productName, quantity, price);
                String newKey = productName + ' ' + customerId;
                return KeyValue.pair(newKey, orderEvent);
            }).toTable(
                    Materialized.<String, OrderEvent, KeyValueStore<Bytes, byte[]>>as(PRODUCT_STATE_SOLD).
                            withKeySerde(Serdes.String()).
                            withValueSerde(orderEventSerde())
            );

            KTable<String, Long> productQuantityKTable = inputStream.
                    map(( k, OrderEvent) -> new KeyValue<String, Long>(OrderEvent.getProductName(), OrderEvent.getQuantity())).
                    groupByKey().
                    aggregate(
                            ()-> 0L,
                            (k,v,agg) -> v + agg,
                            Materialized.<String, Long, KeyValueStore<Bytes,byte[]>>as("c152")
                                    .withKeySerde(Serdes.String())
                                    .withValueSerde(Serdes.Long())
                    );

            KStream<String, QuantitySummary> productQuantityStream = productQuantityKTable.
                    toStream().
                    map((k, v) -> KeyValue.pair(k, new QuantitySummary(k, v)));

            // use the following code for testing
            productQuantityStream.print(Printed.<String, QuantitySummary>toSysOut().withLabel("Console Output"));
            return productQuantityStream;
        };
    }

    public Serde<OrderEvent> orderEventSerde() {
        final JsonSerde<OrderEvent> orderEventJsonSerde = new JsonSerde<>();
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "csci318.task3.bi.model.OrderEvent");
        configProps.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        orderEventJsonSerde.configure(configProps, false);
        return orderEventJsonSerde;
    }
}
