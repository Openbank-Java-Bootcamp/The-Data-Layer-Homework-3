package com.ironhack.TheDataLayerHomework3.repository;

import com.ironhack.TheDataLayerHomework3.models.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpportunityRepository  extends JpaRepository<Opportunity, String> {
    /*By SalesRep*/

    //A count of all Opportunities by SalesRep can be displayed by typing “Report Opportunity by SalesRep”

    //A count of all CLOSED_WON Opportunities by SalesRep can be displayed by typing “Report CLOSED-WON by SalesRep”

    //A count of all CLOSED_LOST Opportunities by SalesRep can be displayed by typing “Report CLOSED-LOST by SalesRep”

    //A count of all OPEN Opportunities by SalesRep can be displayed by typing “Report OPEN by SalesRep”

    /*           */

    /*By Product*/
    //A count of all Opportunities by the product can be displayed by typing “Report Opportunity by the product”

    //A count of all CLOSED_WON Opportunities by the product can be displayed by typing “Report CLOSED-WON by the product”

    //A count of all CLOSED_LOST Opportunities by the product can be displayed by typing “Report CLOSED-LOST by the product”

    //A count of all OPEN Opportunities by the product can be displayed by typing “Report OPEN by the product”

    /*           */

    /*By Country*/
    //A count of all Opportunities by country can be displayed by typing “Report Opportunity by Country”

    //A count of all CLOSED_WON Opportunities by country can be displayed by typing “Report CLOSED-WON by Country”

    //A count of all CLOSED_LOST Opportunities by country can be displayed by typing “Report CLOSED-LOST by Country”

    //A count of all OPEN Opportunities by country can be displayed by typing “Report OPEN by Country”

    /*           */

    /*By City*/
    //A count of all Opportunities by the city can be displayed by typing “Report Opportunity by City”
    //A count of all CLOSED_WON Opportunities by the city can be displayed by typing “Report CLOSED-WON by City”
    //A count of all CLOSED_LOST Opportunities by the city can be displayed by typing “Report CLOSED-LOST by City”
    //A count of all OPEN Opportunities by the city can be displayed by typing “Report OPEN by City”

    /*           */


    /*By Industry*/

    //A count of all Opportunities by industry can be displayed by typing “Report Opportunity by Industry”
    //A count of all CLOSED_WON Opportunities by industry can be displayed by typing “Report CLOSED-WON by Industry”
    //A count of all CLOSED_LOST Opportunities by industry can be displayed by typing “Report CLOSED-LOST by Industry”
    //A count of all OPEN Opportunities by industry can be displayed by typing “Report OPEN by Industry”

    /*           */


    //The mean number of Opportunities associated with an Account can be displayed by typing “Mean Opps per Account”
    //The median number of Opportunities associated with an Account can be displayed by typing “Median Opps per Account”
    //The maximum number of Opportunities associated with an Account can be displayed by typing “Max Opps per Account”
    //The minimum number of Opportunities associated with an Account can be displayed by typing “Min Opps per Account”






}
