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
public class Contact extends Lead {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Contact(String name, Long phoneNumber, String email, String companyName, SalesRep salesRep) {
        super(name, phoneNumber, email, companyName, salesRep);
        this.id = shortUUID();
    }

    public Contact(Lead lead) {
        super(lead.getName(), lead.getPhoneNumber(), lead.getEmail(), lead.getCompanyName(), lead.getSalesRep());
        this.id = shortUUID();
    }

    @Override
    public String toString() {
        return ((char) 27 + "[36m" + "\n\tContact ID: %s " + (char) 27 + "[39m" + "\n\t  Name: %s,\n\t  Phone Number: %s,\n\t  Email: %s," +
                "\n\t  Company Name: %s").formatted(id, getName(), getPhoneNumber(), getEmail(), getCompanyName());
    }
}
