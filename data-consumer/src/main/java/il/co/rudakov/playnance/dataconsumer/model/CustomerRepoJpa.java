package il.co.rudakov.playnance.dataconsumer.model;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Scope("prototype")
public interface CustomerRepoJpa extends JpaRepository<Customer, Integer> {

    List<Customer> findAllByIdGreaterThan(int lastCustomerId);
}
