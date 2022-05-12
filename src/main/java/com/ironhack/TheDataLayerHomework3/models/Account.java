package com.ironhack.TheDataLayerHomework3.models;

import com.ironhack.TheDataLayerHomework3.enums.Industry;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static com.ironhack.TheDataLayerHomework3.utils.Utils.shortUUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class Account {

    @Id
    private String accountId;

    @Column(name = "employee_count")
    int employeeCount;

    Industry industry;
    String city;
    String country;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    List<Contact> contactList = new ArrayList<>();

    @OneToMany(mappedBy = "account")
    List<Opportunity> opportunityList = new ArrayList<>();

    public Account(Industry industry, int employeeCount, String city, String country) {
        this.industry = industry;
        this.employeeCount = employeeCount;
        this.city = city;
        this.country = country;
        this.accountId = shortUUID();
    }

    public Account(Industry industry, int employeeCount, String city, String country, List<Contact> contactList,
                   List<Opportunity> opportunityList) {
        this.industry = industry;
        this.employeeCount = employeeCount;
        this.city = city;
        this.country = country;
        this.contactList = contactList;
        this.opportunityList = opportunityList;
        this.accountId = shortUUID();
    }


    @Override

    //todo list ??
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
                "\n\tStatus: %s" ).formatted(accountId,
                industry.toString().toLowerCase(), employeeCount, country, city, contactList.get(0).toString(),
                opportunityList.get(0).getOpportunityId(), opportunityList.get(0).getProduct().toString().toLowerCase(),
                opportunityList.get(0).getQuantity(), opportunityList.get(0).getDecisionMaker().getName(),
                opportunityList.get(0).getStatus().toString().toLowerCase());

    }
}
