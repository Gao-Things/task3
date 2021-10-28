package csci318.task3.bi.service;

/* This class computes a stream of brand quantities
 * and creates state stores for interactive queries.
 */

import csci318.task3.bi.model.OrderEvent;
import csci318.task3.bi.model.ProductQuantity;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Printed;
import org.apache.kafka.streams.state.KeyValueStore;
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
    public final static String EQUIPMENT_STATE_STORE = "equipment-store";

    @Bean
    public Function<KStream<?, OrderEvent>, KStream<String, ProductQuantity>> process() {
        return inputStream -> {

//            KTable<String, ProductQuantity> productQuantityKTable1 = inputStream.map((k, v) -> {
//                String productName = v.getProductName();
//                long quantity = v.getQuantity();
//                ProductQuantity productQuantity = new ProductQuantity();
//                productQuantity.setProductName(productName);
//                productQuantity.setQuantity(quantity);
//                return KeyValue.pair(productName, productQuantity);
//            }).toTable(
//                    Materialized.<String, ProductQuantity, KeyValueStore<Bytes, byte[]>>as(EQUIPMENT_STATE_STORE).
//                            withKeySerde(Serdes.String()).
//                            // a custom value serde for this state store
//                                    withValueSerde(productSerde())
//            );


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

            KStream<String, ProductQuantity> productQuantityStream = productQuantityKTable.
                    toStream().
                    map((k, v) -> KeyValue.pair(k, new ProductQuantity(k, v)));
            // use the following code for testing
            productQuantityStream.print(Printed.<String, ProductQuantity>toSysOut().withLabel("Console Output"));
            return productQuantityStream;
        };
    }

//    public Serde<ProductQuantity> productSerde() {
//        final JsonSerde<ProductQuantity> productJsonSerde = new JsonSerde<>();
//        Map<String, Object> configProps = new HashMap<>();
//        configProps.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "csci318.task3.bi.model.ProductQuantity");
//        configProps.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
//        productJsonSerde.configure(configProps, false);
//        return productJsonSerde;
//    }
}
