package csci318.task3.bi.service;

import org.springframework.cloud.stream.binder.kafka.streams.InteractiveQueryService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class Service1 {

    private final InteractiveQueryService interactiveQueryService;

    //@Autowired
    public Service1(InteractiveQueryService interactiveQueryService) {
        this.interactiveQueryService = interactiveQueryService;
    }
}
