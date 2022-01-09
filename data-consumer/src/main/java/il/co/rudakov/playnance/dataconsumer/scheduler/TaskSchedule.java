package il.co.rudakov.playnance.dataconsumer.scheduler;

import il.co.rudakov.playnance.dataconsumer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.logging.Logger;

@Component
public class TaskSchedule implements ApplicationListener<ApplicationReadyEvent> {

    Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    CustomerService customerService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        customerService.deleteAllFromRedis();

    }

    @PreDestroy
    public void shutDown(){
        System.out.println("TOTAL CUSTOMERS FETCHED BY CONSUMER : " + customerService.getFetchedCustomerCount());
        System.out.println("TOTAL ITERATIONS MADE BY CONSUMER : " + customerService.getIterationCountTotal());
        System.out.println("NUMBER OF EMPTY ITERATIONS MADE BY CONSUMER : " + customerService.getIterationCountEmpty());
        System.out.println("TOTAL CUSTOMERS GENERATED BY PRODUCER : " + customerService.getCustomerTotalCount());

    }
}
