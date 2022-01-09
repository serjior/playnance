package il.co.rudakov.playnance.dataproducer.service;

import il.co.rudakov.playnance.dataproducer.model.Customer;

public interface CustomerService {

    void saveCustomer(Customer customer);
    void generateAndSaveRandomCustomer();
    long getCustomerCount();

}
