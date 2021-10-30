package csci318.task3.bi.service;

import csci318.task3.bi.model.OrderEvent;
import csci318.task3.bi.model.Product;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.binder.kafka.streams.InteractiveQueryService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderInteractiveQuery {
    @Autowired
    private final InteractiveQueryService interactiveQueryService;

//    //@Autowired
    public OrderInteractiveQuery(InteractiveQueryService interactiveQueryService) {
       this.interactiveQueryService = interactiveQueryService;
    }

    public List<String> getProductByCustomerId(Long customerId) {
        List<String> productList = new ArrayList<>();
        KeyValueIterator<String, OrderEvent> all = getOrderEvent().all();
        while (all.hasNext()) {
            OrderEvent orderEvent = all.next().value;
            Long id = orderEvent.getCustomerId();
            if (id != customerId)
                continue;
            String productName = orderEvent.getProductName();
            productList.add(productName);
        }
        return productList;
    }

    public Double getOrderValueByCustomerId(Long customerId) {
        double sum = 0.0;
        KeyValueIterator<String, OrderEvent> all = getOrderEvent().all();
        while (all.hasNext()) {
            OrderEvent orderEvent = all.next().value;
            Long id = orderEvent.getCustomerId();
            if (id != customerId)
                continue;
            Double price = orderEvent.getPrice();
            Long quantity = orderEvent.getQuantity();
            sum += price * (double)quantity;
        }
        return sum;
    }

    private ReadOnlyKeyValueStore<String, OrderEvent> getOrderEvent() {
        return this.interactiveQueryService.getQueryableStore(OrderEventStreamProcessing.PRODUCT_STATE_SOLD,
                QueryableStoreTypes.keyValueStore());
    }
}
