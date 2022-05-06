package com.ironhack.TheDataLayerHomework3.classes;

import com.ironhack.TheDataLayerHomework3.enums.Product;
import com.ironhack.TheDataLayerHomework3.enums.Status;

import lombok.*;

import static com.ironhack.TheDataLayerHomework3.utils.Utils.shortUUID;


@Getter
@Setter
@NoArgsConstructor
public class Opportunity {
    private String id;
    private Product product;
    private int quantity;
    private Contact decisionMaker;
    private Status status;

    public Opportunity(Product product, int quantity, Contact contact, Status status) {
        this.id = shortUUID();
        this.product = product;
        this.quantity = quantity;
        this.decisionMaker = contact ;
        this.status = status;
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
