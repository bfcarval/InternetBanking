package com.internet.banking.database.repository;

import com.internet.banking.database.entity.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {


    Optional<Customer> findByAccountNumberAndName(String accountNumber, String name);

    @Query("SELECT c FROM Customer c")
    List<Customer> findAllCustomers(Pageable pageable);
}
