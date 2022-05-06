package com.ironhack.TheDataLayerHomework3.classes;

import lombok.*;

import static com.ironhack.TheDataLayerHomework3.utils.Utils.shortUUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contact extends Lead {
    private String id;

    public Contact(String name, Long phoneNumber, String email, String companyName) {
        super(name, phoneNumber, email, companyName);
        this.id = shortUUID();
    }

    public Contact(Lead lead) {
        super(lead.getName(), lead.getPhoneNumber(), lead.getEmail(), lead.getCompanyName());
        this.id = shortUUID();
    }

    @Override
    public String toString() {
        return ((char) 27 + "[36m" + "\n\tContact ID: %s " + (char) 27 + "[39m" + "\n\t  Name: %s,\n\t  Phone Number: %s,\n\t  Email: %s," +
                "\n\t  Company Name: %s").formatted(id, getName(), getPhoneNumber(), getEmail(), getCompanyName());
    }
}
