package com.ethan.common.model.dto;

public class AddressDto {

    private String firstName;
    private String lastName;
    private String streetName;
    private String postalCode;
    private String town;
    private String country;

    public AddressDto(String firstName, String lastName, String streetName, String postalCode, String town, String country) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetName = streetName;
        this.postalCode = postalCode;
        this.town = town;
        this.country = country;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
