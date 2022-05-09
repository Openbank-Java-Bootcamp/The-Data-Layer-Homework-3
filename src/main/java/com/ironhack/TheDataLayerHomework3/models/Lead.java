package com.ironhack.TheDataLayerHomework3.models;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lead_table")
public class Lead {
    @Id
    @Column(name = "id")
    //TODO @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int leadId;

    private String name;

    @Column(name = "phone_number")
    private Long phoneNumber;

    private String email;

    @Column(name = "company_name")
    private String companyName;

    @ManyToOne
    @JoinColumn(name = "sales_rep_id")
    private SalesRep salesRep;


    public Lead(String name, Long phoneNumber, String email, String companyName, SalesRep salesRep) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.companyName = companyName;
        this.salesRep = salesRep;
    }


    @Override
    public String toString() {
        return ((char) 27 + "[36m" + "Lead ID %s " + (char) 27 + "[39m" + "\n Name: %s, \n Phone Number: %s, " +
                "\n Email: %s, \n Company name: %s \n").formatted(leadId, name, phoneNumber, email, companyName);

    }
}
