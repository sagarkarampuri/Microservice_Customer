package com.ms.cd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ms.cd.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
