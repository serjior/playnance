package il.co.rudakov.playnance.dataconsumer.service;

public interface CustomerService {

    void fetchNewCustomers();
    long getCustomerTotalCount();
    void deleteAllFromRedis();
    long getFetchedCustomerCount();
    int getIterationCountTotal();
    int getIterationCountEmpty();

}
