package com.ironhack.TheDataLayerHomework3.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static com.ironhack.TheDataLayerHomework3.utils.Utils.shortUUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sales_rep")
public class SalesRep {

    @Id
    private String saleRepId;

    private String name;

    @OneToMany(mappedBy = "salesRep")
    private List<Lead> leadList;

    @OneToMany(mappedBy = "salesRep")
    private List<Contact> contactList;

    @OneToMany(mappedBy = "salesRep")
    private List<Opportunity> opportunityList;

    public SalesRep(String name) {
        this.name = name;
        this.saleRepId = shortUUID();
    }


    @Override
    public String toString() {
        return ((char) 27 + "[36m" + "SalesRep ID %s " + (char) 27 + "[39m" +
                "\n Name: %s ").formatted(saleRepId, name);
    }
}

