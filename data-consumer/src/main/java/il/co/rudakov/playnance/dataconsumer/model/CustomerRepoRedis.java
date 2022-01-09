package il.co.rudakov.playnance.dataconsumer.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepoRedis extends CrudRepository<RedisCustomer, Long> {

    List<RedisCustomer> findAllByIdGreaterThat(int lastCustomerId);

}
