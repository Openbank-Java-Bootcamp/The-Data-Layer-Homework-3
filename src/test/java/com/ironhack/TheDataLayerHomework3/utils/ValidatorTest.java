package com.ironhack.TheDataLayerHomework3.utils;


import com.ironhack.TheDataLayerHomework3.models.Contact;
import com.ironhack.TheDataLayerHomework3.models.Opportunity;
import com.ironhack.TheDataLayerHomework3.enums.Product;
import com.ironhack.TheDataLayerHomework3.enums.Status;
import com.ironhack.TheDataLayerHomework3.models.SalesRep;
import com.ironhack.TheDataLayerHomework3.navigation.OpportunityNavigation;
import com.ironhack.TheDataLayerHomework3.navigation.SalesRepNavigation;
import org.junit.jupiter.api.Test;

import static com.ironhack.TheDataLayerHomework3.enums.Validation.PHONE;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidatorTest {


    @Test
    public void applyValidation_ValidationEnumAndStringInput_Boolean() {
        assertTrue(Validator.applyValidation(PHONE, "(202) 555-0125"));
        assertFalse(Validator.applyValidation(PHONE, "(hola) 555-0125"));
    }

    @Test
    public void isValidCountryName_String_Boolean() {
        assertTrue(Validator.isValidCountryName("Argentina"));
        assertFalse(Validator.isValidCountryName("Argentonia"));
    }

    @Test
    public void isValidName_String_Boolean() {
        assertTrue(Validator.isValidName("Maria Antonia"));
        assertFalse(Validator.isValidName("M@riantonia"));
    }

    @Test
    public void isValidOpportunityId_String_Boolean() {
        SalesRep salesRep = new SalesRep("Sarita Wilson");
        OpportunityNavigation.opportunityList.add(new Opportunity(Product.FLATBED, 21, new Contact("Mike",
                956211567L, "MikeMichael@EmersonProduceCo.com", "Emerson Produce Co.",
                salesRep),
                Status.OPEN));
        assertTrue(Validator.isValidOpportunityId(OpportunityNavigation.opportunityList.get(0).getId()));
        assertFalse(Validator.isValidOpportunityId("12133"));
        OpportunityNavigation.opportunityList.clear();
    }

    @Test
    public void isValidSalesRepId_String_Boolean() {
        SalesRepNavigation.salesRepList.add(new SalesRep("Sarita, Wilson"));

        assertTrue(Validator.isValidSalesRepId(SalesRepNavigation.salesRepList.get(0).getId()));
        assertFalse(Validator.isValidSalesRepId("12133"));
        SalesRepNavigation.salesRepList.clear();
    }


    @Test
    public void isValidEmail_String_Boolean() {
        assertTrue(Validator.isValidEmail("Maria@Antonia.es"));
        assertFalse(Validator.isValidEmail("miariantonia@"));
        assertFalse(Validator.isValidEmail("miariantonia.es"));
        assertFalse(Validator.isValidEmail("@miariantonia.es"));
    }

    @Test
    public void isValidCommand_String_Boolean() {
        assertTrue(Validator.isValidCommand(" new LEAD"));
        assertFalse(Validator.isValidCommand("niu lid"));
    }

    @Test
    public void isValidPhoneNumber_String_Boolean() {
        assertTrue(Validator.applyValidation(PHONE, "(202) 555-0125"));
        assertFalse(Validator.applyValidation(PHONE, "(adios) 555-0125"));
    }

}