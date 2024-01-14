package org.abhi.accounts.Repositories;

import org.abhi.accounts.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer,Long> {

    Optional<Customer> findByMobileNumber(String mobileNumber);

}
