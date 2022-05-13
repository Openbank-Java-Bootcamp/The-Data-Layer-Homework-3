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
}
