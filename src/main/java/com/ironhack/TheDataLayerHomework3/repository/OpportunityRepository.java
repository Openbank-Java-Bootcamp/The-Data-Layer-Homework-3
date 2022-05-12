package com.ironhack.TheDataLayerHomework3.repository;

import com.ironhack.TheDataLayerHomework3.enums.Industry;
import com.ironhack.TheDataLayerHomework3.enums.Product;
import com.ironhack.TheDataLayerHomework3.enums.Status;
import com.ironhack.TheDataLayerHomework3.models.Opportunity;
import com.ironhack.TheDataLayerHomework3.models.SalesRep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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



    @Query(value = "SELECT MAX(quantity) FROM opportunity")
    Integer maxQuantity();
    @Query(value = "SELECT MIN(quantity) FROM opportunity")
    Integer minQuantity();

    @Query(value = "SELECT AVG(quantity) FROM opportunity")
    Integer avgQuantity();

    @Query(value = "SET @row_index := -1;\n" +
            "\n" +
            "    SELECT AVG(subq.quantity) as median_value\n" +
            "    FROM (\n" +
            "            SELECT @row_index:=@row_index + 1 AS row_index, quantity\n" +
            "            FROM opportunity\n" +
            "                    ORDER BY quantity\n" +
            "    ) AS subq\n" +
            "    WHERE subq.row_index\n" +
            "    IN (FLOOR(@row_index / 2) , CEIL(@row_index / 2))")
    Integer medianQuantity();



    @Query(value = "SELECT COUNT(account_id) as count FROM opportunity GROUP BY account_id ORDER BY count DESC LIMIT 1")
    Integer maxOpportunities();

    @Query(value = "SELECT COUNT(account_id) as count FROM opportunity GROUP BY account_id ORDER BY count ASC LIMIT 1")
    Integer minOpportunities();




}
