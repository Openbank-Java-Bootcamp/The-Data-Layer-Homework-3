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
    /*By SalesRep*/
    //A count of all Opportunities by SalesRep can be displayed by typing “Report Opportunity by SalesRep”

    //A count of all CLOSED_WON Opportunities by SalesRep can be displayed by typing “Report CLOSED-WON by SalesRep”
    //A count of all CLOSED_LOST Opportunities by SalesRep can be displayed by typing “Report CLOSED-LOST by SalesRep”
    // A count of all OPEN Opportunities by SalesRep can be displayed by typing “Report OPEN by SalesRep”

     Integer countBySalesRepAndStatus(SalesRep salesRep, Status status);


    /*By Product*/
    //A count of all Opportunities by the product can be displayed by typing “Report Opportunity by the product”

    //A count of all CLOSED_WON Opportunities by the product can be displayed by typing “Report CLOSED-WON by the product”
    //A count of all CLOSED_LOST Opportunities by the product can be displayed by typing “Report CLOSED-LOST by the product”
    //A count of all OPEN Opportunities by the product can be displayed by typing “Report OPEN by the product”
 //    Integer countByProductAndStatus(Product product, Status status);

    /*By Country*/
    //A count of all Opportunities by country can be displayed by typing “Report Opportunity by Country”
    //   Integer countOpportunityByAccount_City (String accountId, String city);

    //A count of all CLOSED_WON Opportunities by country can be displayed by typing “Report CLOSED-WON by Country”
    //A count of all CLOSED_LOST Opportunities by country can be displayed by typing “Report CLOSED-LOST by Country”
    //A count of all OPEN Opportunities by country can be displayed by typing “Report OPEN by Country”

    // Integer countByCountryAndStatus(String country, Status status);

    /*           */

    /*By City*/
    //A count of all Opportunities by the city can be displayed by typing “Report Opportunity by City”
    //   Integer countBySalesRep(SalesRep salesRep);
    //A count of all CLOSED_WON Opportunities by the city can be displayed by typing “Report CLOSED-WON by City”
    //A count of all CLOSED_LOST Opportunities by the city can be displayed by typing “Report CLOSED-LOST by City”
    //A count of all OPEN Opportunities by the city can be displayed by typing “Report OPEN by City”
      Integer countByStatusAndAccount_City(Status status, String city);


    /*By Industry*/
    //A count of all Opportunities by industry can be displayed by typing “Report Opportunity by Industry”
    //A count of all CLOSED_WON Opportunities by industry can be displayed by typing “Report CLOSED-WON by Industry”
    //A count of all CLOSED_LOST Opportunities by industry can be displayed by typing “Report CLOSED-LOST by Industry”
    //A count of all OPEN Opportunities by industry can be displayed by typing “Report OPEN by Industry”
    //  Integer countByIndustryAndStatus(Industry industry, Status status);









}
