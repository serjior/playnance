package il.co.rudakov.playnance.dataproducer.service;

import il.co.rudakov.playnance.dataproducer.model.Customer;
import il.co.rudakov.playnance.dataproducer.model.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

@Service
public class CustomerServiceImpl implements CustomerService{

    Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    CustomerRepo customerRepo;

    private int iterationCount = 0;

    @Override
    public void saveCustomer(Customer customer){
        iterationCount++;
        customerRepo.save(customer);
        String info = String.format("ITERATION %d: a new customer created %s %s", iterationCount, customer.getFirstName(), customer.getLastName());
        logger.info(info);
    }

    @Override
    public void generateAndSaveRandomCustomer(){
        saveCustomer(generateRandomCustomer());

    }

    @Override
    public long getCustomerCount(){
        return customerRepo.count();
    }

    private Customer generateRandomCustomer(){
        return new Customer(randomAlphabetic(5,11), randomAlphabetic(5,11));
    }

}
