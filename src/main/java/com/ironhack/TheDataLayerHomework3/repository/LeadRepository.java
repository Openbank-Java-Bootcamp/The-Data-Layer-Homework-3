package com.ironhack.TheDataLayerHomework3.repository;

import com.ironhack.TheDataLayerHomework3.models.Lead;
import com.ironhack.TheDataLayerHomework3.models.SalesRep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadRepository  extends JpaRepository<Lead, Integer> {

    Integer countBySalesRep(SalesRep salesRep);
}
