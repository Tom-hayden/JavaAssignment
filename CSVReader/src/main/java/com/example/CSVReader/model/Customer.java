package com.example.CSVReader.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Objects;
import java.util.UUID;

public class Customer {

    private UUID customerRef;
    private String customerName;
    private String addressLine1;
    private String addressLine2;
    private String town;
    private String county;
    private String country;
    private String postcode;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public Customer(UUID customerRef, String customerName, String addressLine1, String addressLine2, String town, String county, String country, String postcode) {
        this.customerRef = customerRef;
        this.customerName = customerName;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.town = town;
        this.county = county;
        this.country = country;
        this.postcode = postcode;
    }

    public UUID getCustomerRef() {
        return customerRef;
    }

    public void setCustomerRef(UUID customerRef) {
        this.customerRef = customerRef;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }


    public String toJson() {
        try {
            return objectMapper.writeValueAsString(this);
        } catch (Exception e) {
            throw new RuntimeException("Error converting Customer to JSON", e);
        }
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerRef=" + customerRef +
                ", customerName='" + customerName + '\'' +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", town='" + town + '\'' +
                ", county='" + county + '\'' +
                ", country='" + country + '\'' +
                ", postcode='" + postcode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(customerRef, customer.customerRef) && Objects.equals(customerName, customer.customerName) && Objects.equals(addressLine1, customer.addressLine1) && Objects.equals(addressLine2, customer.addressLine2) && Objects.equals(town, customer.town) && Objects.equals(county, customer.county) && Objects.equals(country, customer.country) && Objects.equals(postcode, customer.postcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerRef, customerName, addressLine1, addressLine2, town, county, country, postcode);
    }
}