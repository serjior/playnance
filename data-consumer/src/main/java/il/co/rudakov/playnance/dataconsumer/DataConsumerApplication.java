package il.co.rudakov.playnance.dataconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DataConsumerApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(DataConsumerApplication.class, args);
		Thread.sleep(600000);
		System.exit(111);
	}

}
