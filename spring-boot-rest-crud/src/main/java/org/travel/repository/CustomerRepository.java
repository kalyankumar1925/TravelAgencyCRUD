package org.travel.repository;

import org.springframework.data.repository.CrudRepository;
import org.travel.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer,Long> {

}
