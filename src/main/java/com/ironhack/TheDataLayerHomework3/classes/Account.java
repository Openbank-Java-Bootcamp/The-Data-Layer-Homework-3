package com.ironhack.TheDataLayerHomework3.classes;

import com.ironhack.TheDataLayerHomework3.enums.Industry;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static com.ironhack.TheDataLayerHomework3.utils.Utils.shortUUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    Industry industry;
    int employeeCount;
    String city;
    String country;
    List<Contact> contactList = new ArrayList<>();
    List<Opportunity> opportunityList = new ArrayList<>();
    private String id;

    public Account(Industry industry, int employeeCount, String city, String country) {
        this.industry = industry;
        this.employeeCount = employeeCount;
        this.city = city;
        this.country = country;
        this.id = shortUUID();
    }

    public Account(Industry industry, int employeeCount, String city, String country, List<Contact> contactList,
                   List<Opportunity> opportunityList) {
        this.industry = industry;
        this.employeeCount = employeeCount;
        this.city = city;
        this.country = country;
        this.contactList = contactList;
        this.opportunityList = opportunityList;
        this.id = shortUUID();
    }


    @Override
    public String toString() {
        return ((char) 27 + "[36m" + "\nAccount ID %s " + (char) 27 + "[39m" +
                "\nIndustry: %s, " +
                "\nNumber of employees: %s, " +
                "\nCountry %s, " +
                "\nCity %s " +
                "\nContact List: \t%s " +
                "\nOpportunity List: " +
                (char) 27 + "[36m" + "\n\tpportunity ID %s " + (char) 27 + "[39m"+
                "\n\tProduct: %s" +
                "\n\tQuantity: %s" +
                "\n\tDecision Maker Name:  %s " +
                "\n\tStatus: " ).formatted(id,
                industry.toString().toLowerCase(), employeeCount, country, city, contactList.get(0).toString(),
                opportunityList.get(0).getId(), opportunityList.get(0).getProduct().toString().toLowerCase(), opportunityList.get(0).getQuantity(),
                opportunityList.get(0).getDecisionMaker().getName(), opportunityList.get(0).getStatus().toString().toLowerCase());

    }
}
