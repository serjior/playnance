package il.co.rudakov.playnance.dataconsumer.service;


import il.co.rudakov.playnance.dataconsumer.model.Customer;
import il.co.rudakov.playnance.dataconsumer.model.CustomerRepoJpa;
import il.co.rudakov.playnance.dataconsumer.model.CustomerRepoRedis;
import il.co.rudakov.playnance.dataconsumer.model.RedisCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService{

    Logger logger = Logger.getLogger(this.getClass().getName());

    private int lastCustomerId = 0;
    private int iterationCountTotal = 0;
    private int iterationCountEmpty = 0;

    @Autowired
    CustomerRepoJpa customerRepoJpa;

    @Autowired
    CustomerRepoRedis customerRepoRedis;

    @PersistenceContext
    EntityManager em;

    @Override
    @Scheduled(fixedRate = 5000)
    public void fetchNewCustomers(){
        iterationCountTotal++;
        List<Customer> newCustomers = customerRepoJpa.findAllByIdGreaterThan(lastCustomerId);
        if(newCustomers.isEmpty()){
            logger.info("ITERATION : " + iterationCountTotal + " - NOTHING TO FETCH");
            iterationCountEmpty++;
            return;
        }
        logger.info("ITERATION : " + iterationCountTotal);
        lastCustomerId = newCustomers.stream()
                .mapToInt(customer->customer.getId())
                .max().orElse(lastCustomerId);
        List<RedisCustomer> newRedisCustomers = newCustomers.stream()
                .map(customer -> new RedisCustomer(customer))
                .collect(Collectors.toList());
        newCustomers.stream().forEach(customer -> logger.info(customer.toString()));
        customerRepoRedis.saveAll(newRedisCustomers);
        return;
    }

    @Override
    public long getCustomerTotalCount(){
        long count = (long)em.createQuery("SELECT COUNT(c) FROM Customer c").getSingleResult();
        return count;
    }

    @Override
    public void deleteAllFromRedis(){
        customerRepoRedis.deleteAll();
    }

    @Override
    public long getFetchedCustomerCount(){
        return customerRepoRedis.count();
    }

    @Override
    public int getIterationCountTotal() {
        return iterationCountTotal;
    }

    @Override
    public int getIterationCountEmpty() {
        return iterationCountEmpty;
    }
}
