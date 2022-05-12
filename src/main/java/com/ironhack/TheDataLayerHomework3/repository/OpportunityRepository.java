package com.ironhack.TheDataLayerHomework3.repository;

import com.ironhack.TheDataLayerHomework3.enums.Industry;
import com.ironhack.TheDataLayerHomework3.enums.Product;
import com.ironhack.TheDataLayerHomework3.enums.Status;
import com.ironhack.TheDataLayerHomework3.models.Opportunity;
import com.ironhack.TheDataLayerHomework3.models.SalesRep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpportunityRepository  extends JpaRepository<Opportunity, String> {

    Integer countBySalesRepAndStatus(SalesRep salesRep, Status status);

    Integer countBySalesRep(SalesRep salesRep);

    Integer countByProduct (Product product);

    Integer countByProductAndStatus(Product product, Status status);



    Integer countByAccount_Country (String accountId, String country);
    Integer countByStatusAndAccount_Country (String accountId, String country, Status status);



    Integer countByAccount_City (String accountId, String city);
    Integer countByStatusAndAccount_City(String accountId, String city, Status status);


    Integer countByAccount_Industry (String accountId, Industry industry);
    Integer countByStatusAndAccount_Industry(String accountId, Industry industry, Status status);




    //The mean number of Opportunities associated with an Account can be displayed by typing “Mean Opps per Account”








}
