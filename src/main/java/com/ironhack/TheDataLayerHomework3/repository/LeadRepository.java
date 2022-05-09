package com.ironhack.TheDataLayerHomework3.repository;

import com.ironhack.TheDataLayerHomework3.models.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadRepository  extends JpaRepository<Lead, Integer> {

    /*By SalesRep*/
    //A count of Leads by SalesRep can be displayed by typing “Report Lead by SalesRep”
    Integer


}
