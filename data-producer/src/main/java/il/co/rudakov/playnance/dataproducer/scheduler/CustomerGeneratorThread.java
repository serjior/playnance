package il.co.rudakov.playnance.dataproducer.scheduler;

import il.co.rudakov.playnance.dataproducer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.apache.commons.lang3.RandomUtils.nextInt;

@Component
public class CustomerGeneratorThread implements Runnable{

    @Autowired
    CustomerService customerService;

    private int interval = nextInt(5000, 10001);

    @Override
    public void run() {

        while (true) {
            customerService.generateAndSaveRandomCustomer();
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
