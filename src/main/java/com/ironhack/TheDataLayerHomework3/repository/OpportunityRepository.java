package com.ironhack.TheDataLayerHomework3.repository;

import com.ironhack.TheDataLayerHomework3.enums.Industry;
import com.ironhack.TheDataLayerHomework3.enums.Product;
import com.ironhack.TheDataLayerHomework3.enums.Status;
import com.ironhack.TheDataLayerHomework3.models.Opportunity;
import com.ironhack.TheDataLayerHomework3.models.SalesRep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpportunityRepository  extends JpaRepository<Opportunity, String> {

    Integer countBySalesRepAndStatus(SalesRep salesRep, Status status);

    Integer countBySalesRep(SalesRep salesRep);

    Integer countByProduct (Product product);

    Integer countByProductAndStatus(Product product, Status status);



    Integer countByAccount_Country (String country);
    Integer countByStatusAndAccount_Country (Status status, String country);



    Integer countByAccount_City (String city);
    Integer countByStatusAndAccount_City(Status status, String city);


    Integer countByAccount_Industry (Industry industry);
    Integer countByStatusAndAccount_Industry(Status status, Industry industry);



    @Query(value = "SELECT MAX(quantity) FROM opportunity", nativeQuery = true)
    Integer maxQuantity();
    @Query(value = "SELECT MIN(quantity) FROM opportunity", nativeQuery = true)
    Integer minQuantity();

    @Query(value = "SELECT AVG(quantity) FROM opportunity", nativeQuery = true)
    double avgQuantity();


    //   **  bonus!! a crazy median query which works on MySQL but not here yay :) **

//    @Query(value = "SET @row_index := -1;SELECT AVG(subq.quantity) as median_value FROM (SELECT @row_index:=@row_index + 1 AS row_index, quantity\n" +
//            "            FROM opportunity\n" +
//            "                    ORDER BY quantity\n" +
//            "    ) AS subq\n" +
//            "    WHERE subq.row_index\n" +
//            "    IN (FLOOR(@row_index / 2) , CEIL(@row_index / 2))", nativeQuery = true)

    @Query(value ="SELECT quantity FROM opportunity ORDER by quantity" , nativeQuery = true)
    List<Integer> medianQuantity();




    @Query(value = "SELECT COUNT(account_id) as count FROM opportunity GROUP BY account_id ORDER BY count DESC LIMIT 1"
            , nativeQuery = true)
    Integer maxOpportunities();

    @Query(value = "SELECT COUNT(account_id) as count FROM opportunity GROUP BY account_id ORDER BY count ASC LIMIT 1"
            , nativeQuery = true)
    Integer minOpportunities();



    @Query(value = "SELECT COUNT(account_id) as count FROM opportunity GROUP BY account_id ORDER BY count DESC;"
            , nativeQuery = true)
    List<Integer> avgOpportunitiesByAccount();

    @Query(value = "SELECT COUNT(account_id) FROM opportunity GROUP BY account_id ORDER BY account_id;"
            , nativeQuery = true)
    List<Integer> medianOpportunitiesByAccount();



}
