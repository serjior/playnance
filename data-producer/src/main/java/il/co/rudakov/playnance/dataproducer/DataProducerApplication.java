package il.co.rudakov.playnance.dataproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DataProducerApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(DataProducerApplication.class, args);

		Thread.sleep(60000);
		System.exit(111);
	}

}
