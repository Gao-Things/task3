package csci318.task3.bi;

import csci318.task3.bi.model.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;

@SpringBootApplication
public class BiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BiApplication.class, args);
    }

//    private static final Logger log = LoggerFactory.getLogger(BiApplication.class);
//
//    @Bean
//    public Consumer<OrderEvent> consume() {
//        return input -> log.info(input.toString());
//    }

}
