package com.ethan.common.model.dto;

import java.util.List;

public class OrderDto {

    private String code;
    private AddressDto address;
    private List<OrderEntryDto> entries;

    public OrderDto(String code, AddressDto address, List<OrderEntryDto> entries) {
        this.code = code;
        this.address = address;
        this.entries = entries;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getTotalPrice() {
        return getEntries().stream().mapToDouble(entry -> entry.getPrice()).sum();
    }

    public Integer getTotalQuantity() {
        return getEntries().stream().mapToInt(entry -> entry.getQuantity()).sum();
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    public List<OrderEntryDto> getEntries() {
        return entries;
    }

    public void setEntries(List<OrderEntryDto> entries) {
        this.entries = entries;
    }
}
