package com.ironhack.TheDataLayerHomework3.models;

import org.junit.jupiter.api.BeforeAll;

class OpportunityTest {
    private Lead lead;
    private Opportunity opportunity;

    @BeforeAll
    public void SetUp(){
        Lead lead = new Lead();
        Opportunity opportunity = new Opportunity();
    }

   /* @org.junit.jupiter.api.Test
    void createOpportunity(Lead lead) {
        assertEquals(opportunity, createOpportunity(this.lead));
    }*/


//    @org.junit.jupiter.api.Test
//    void createOpportunity() {
//        assertEquals(opportunity, lead.createOpportunity());
//    }

}