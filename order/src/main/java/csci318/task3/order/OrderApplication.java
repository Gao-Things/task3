package csci318.task3.order;

import csci318.task3.order.models.Order;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class OrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}

	private static final String url = "http://localhost:3001/orders";

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			try {
				boolean flag = true;
				System.out.println("start");
				while (flag){
					Order order = new Order(10L, TestProductName.getName
												((int)(Math.random() * 5 + 1)), (long)(Math.random() * 5 + 1));
					Order returnedOrder = restTemplate.postForObject(url, order, Order.class);
					System.out.println(returnedOrder);
					if(returnedOrder == null)
					{
						flag = false;
					}
					Thread.sleep(2000);
				}
				System.out.println("stop");
			}
			catch(Exception exception){}
		};
	}

}

enum TestProductName{
	ProductName1("Test Product Name 1",1),
	ProductName2("Test Product Name 2",2),
	ProductName3("Test Product Name 3",3),
	ProductName4("Test Product Name 4",4),
	ProductName5("Test Product Name 5",5);

	private final String name;
	private final int index;
	TestProductName(String name, int index) {
		this.name = name;
		this.index = index;
	}
	public static String getName(int index) {
		for (TestProductName p : TestProductName.values()) {
			if (p.getIndex() == index) {
				return p.name;
			}
		}
		return null;
	}

	public int getIndex() {
		return index;
	}
}
