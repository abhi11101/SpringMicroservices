package org.abhi.accounts.Repositories;

import jakarta.transaction.Transactional;
import org.abhi.accounts.Entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface AccountsRepo extends JpaRepository<Accounts,Long> {

    Optional<Accounts> findByCustomerId(Long CustomerId);

    @Transactional
    @Modifying
    void deleteByCustomerId(Long CustomerId);
}
