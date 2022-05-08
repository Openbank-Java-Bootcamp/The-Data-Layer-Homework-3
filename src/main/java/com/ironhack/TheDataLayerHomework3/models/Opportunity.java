package com.ironhack.TheDataLayerHomework3.models;

import com.ironhack.TheDataLayerHomework3.enums.Product;
import com.ironhack.TheDataLayerHomework3.enums.Status;

import jakarta.persistence.*;
import lombok.*;

import static com.ironhack.TheDataLayerHomework3.utils.Utils.shortUUID;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "opportunity")
public class Opportunity {

    @Id
    private String id;
    private Product product;
    private int quantity;
    private Status status;

    @OneToOne
    @Column(name = "decision_maker")
    private Contact decisionMaker;

    @ManyToOne
    @JoinColumn(name = "sales_rep_id")
    private SalesRep salesRep;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Opportunity(Product product, int quantity, Contact contact, Status status, SalesRep salesRep) {
        this.id = shortUUID();
        this.product = product;
        this.quantity = quantity;
        this.decisionMaker = contact ;
        this.status = status;
        this.salesRep = salesRep;
    }

    public String getId() {
        return id;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return ((char) 27 + "[36m" + "\n\tOpportunity ID: %s " + (char) 27 + "[39m" + "\n Product: %s, \n Quantity : %s, " +
                "\n Decision Maker: %s, \n Status: %s").formatted(id, product.toString().toLowerCase(), quantity, decisionMaker, status.toString().toLowerCase());
    }

}
