package com.ironhack.TheDataLayerHomework3.models;

import jakarta.persistence.*;
import lombok.*;

import static com.ironhack.TheDataLayerHomework3.utils.Utils.shortUUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contact")
public class Contact {

    @Id
    private String contactId;

    private String name;

    @Column(name = "phone_number")
    private Long phoneNumber;

    private String email;

    @Column(name = "company_name")
    private String companyName;

    @ManyToOne
    @JoinColumn(name = "sales_rep_id")
    private SalesRep salesRep;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Contact(String name, Long phoneNumber, String email, String companyName, SalesRep salesRep) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.companyName = companyName;
        this.salesRep = salesRep;
        this.contactId = shortUUID();
    }

    public Contact(Lead lead) {
        this.name = lead.getName();
        this.phoneNumber = lead.getPhoneNumber();
        this.email = lead.getEmail();
        this.companyName = lead.getCompanyName();
        this.salesRep = lead.getSalesRep();
        this.contactId = shortUUID();
    }

    @Override
    public String toString() {
        return ((char) 27 + "[36m" + "\n\tContact ID: %s " + (char) 27 + "[39m" +
                "\n\t  Name: %s," +
                "\n\t  Phone Number: %s," +
                "\n\t  Email: %s," +
                "\n\t  Company Name: %s" +
                "\n\t  %s").formatted(contactId, name, phoneNumber, email, companyName, salesRep);
    }
}
