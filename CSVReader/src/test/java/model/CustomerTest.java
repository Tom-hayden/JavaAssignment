package model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void ToJson() {
        Customer customer = new Customer(
                UUID.fromString("b7fef628-b0ed-4bab-8e27-61eb85ab9553"),
                "CoolGuy",
                "add1",
                "add2",
                "town",
                "county",
                "Country",
                "12345");

        String json = customer.toJson();

        String expectedJson = "{\"customerRef\":\"b7fef628-b0ed-4bab-8e27-61eb85ab9553\",\"customerName\":\"CoolGuy\"," +
                "\"addressLine1\":\"add1\",\"addressLine2\":\"add2\",\"town\":\"town\",\"county\":\"county\",\"country\"" +
                ":\"Country\",\"postcode\":\"12345\"}";

        assertEquals(expectedJson, json);
    }
}