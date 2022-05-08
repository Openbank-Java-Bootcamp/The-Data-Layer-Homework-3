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
    private String id;

    private String name;

    @OneToMany(mappedBy = "salesRep")
    private List<Lead> leadList;

    @OneToMany(mappedBy = "salesRep")
    private List<Opportunity> opportunityList;

    public SalesRep(String name) {
        this.name = name;
        this.id = shortUUID();
    }

    //TODO toString
}

