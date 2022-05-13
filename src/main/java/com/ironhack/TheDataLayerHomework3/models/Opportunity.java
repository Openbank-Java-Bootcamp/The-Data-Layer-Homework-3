package com.ironhack.TheDataLayerHomework3.models;

import com.ironhack.TheDataLayerHomework3.enums.Product;
import com.ironhack.TheDataLayerHomework3.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.ironhack.TheDataLayerHomework3.utils.Utils.shortUUID;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "opportunity")
public class Opportunity {

    @Id
    private String opportunityId;
    private Product product;
    private int quantity;
    private Status status;

    @OneToOne
    private Contact decisionMaker;

    @ManyToOne
    @JoinColumn(name = "sales_rep_id")
    private SalesRep salesRep;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Opportunity(Product product, int quantity, Contact contact, Status status) {
        this.opportunityId = shortUUID();
        this.product = product;
        this.quantity = quantity;
        this.decisionMaker = contact;
        this.status = status;
        this.salesRep = contact.getSalesRep();
        this.account = contact.getAccount();
    }

    public String getOpportunityId() {
        return opportunityId;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return ((char) 27 + "[36m" + "\n\tOpportunity ID: %s " + (char) 27 + "[39m" +
                "\n\t Product: %s, " +
                "\n\t Quantity : %s, " +
                "\n\t Status: %s, " +
                "\n\t Opportunity's Sales Rep:  \n%s" +
                "\n\t Decision Maker: %s").formatted(opportunityId, product.toString().toLowerCase(), quantity,
                status.toString().toLowerCase(), salesRep, decisionMaker);
    }

}
