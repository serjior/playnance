package il.co.rudakov.playnance.dataproducer.scheduler;

import il.co.rudakov.playnance.dataproducer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Component
public class JobScheduler implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    CustomerGeneratorThread generateCustomerTask;

    @Autowired
    CustomerService customerService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Thread thread = new Thread(generateCustomerTask);
        thread.setDaemon(true);
        thread.start();
    }

    @PreDestroy
    public void shutDownHook(){
        System.out.println("TOTAL ITERATIONS MADE BY PRODUCER : " + customerService.getCustomerCount());
    }
}
