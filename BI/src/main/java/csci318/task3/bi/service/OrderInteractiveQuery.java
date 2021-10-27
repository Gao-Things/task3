package csci318.task3.bi.service;

import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.cloud.stream.binder.kafka.streams.InteractiveQueryService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class OrderInteractiveQuery {

    private final InteractiveQueryService interactiveQueryService;

    //@Autowired
    public OrderInteractiveQuery(InteractiveQueryService interactiveQueryService) {
        this.interactiveQueryService = interactiveQueryService;
    }

    public long getProductQuantity(String productName) {
        long totalQuantityOfAProduct = -1;
        try{
            totalQuantityOfAProduct = getOrderEventInfo().get(productName);
        }catch (Exception exception)
        {
            exception.printStackTrace();
        }

        if (totalQuantityOfAProduct != -1) {
            return totalQuantityOfAProduct;
        } else {
            throw new NoSuchElementException(); //TODO: should use a customised exception.
        }
    }

    private ReadOnlyKeyValueStore<String, Long> getOrderEventInfo() {
        return this.interactiveQueryService.getQueryableStore(OrderEventStreamProcessing.BRAND_STATE_STORE,
                QueryableStoreTypes.keyValueStore());
    }

}
