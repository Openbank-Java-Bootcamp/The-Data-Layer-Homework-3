package com.ironhack.TheDataLayerHomework3.repository;

import com.ironhack.TheDataLayerHomework3.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository  extends JpaRepository<Account, String> {

    @Query(value = "SELECT MAX(employee_count) FROM account", nativeQuery = true)
    Integer maxEmployee();

    @Query(value = "SELECT MIN(employee_count) FROM account", nativeQuery = true)
    Integer minEmployee();

    @Query(value = "SELECT AVG(employee_count) FROM account", nativeQuery = true)
    double avgEmployee();

    @Query(value ="SELECT employee_count FROM account ORDER by employee_count" , nativeQuery = true)
    List<Integer> medianEmployee();

}
