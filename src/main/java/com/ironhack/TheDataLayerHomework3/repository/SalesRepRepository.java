package com.ironhack.TheDataLayerHomework3.repository;

import com.ironhack.TheDataLayerHomework3.models.SalesRep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SalesRepRepository extends JpaRepository<SalesRep, String> {
    Optional<SalesRep> findById(String id);
    List<SalesRep> findByName(String name);


    //1. The mean employeeCount can be displayed by typing “Mean EmployeeCount”
    // 2. The median employeeCount can be displayed by typing “Median EmployeeCount”
    // 3. The maximum employeeCount can be displayed by typing “Max EmployeeCount”
    // 4. The minimum employeeCount can be displayed by typing “Min EmployeeCount”

}
