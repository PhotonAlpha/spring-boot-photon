package com.ethan.common.model.dto;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderService {

    public OrderDto getOrderByCode(final String code) {

        return order(code);

    }

    private OrderDto order(String code) {
        return new OrderDto(code, address(), entries());
    }

    private AddressDto address() {
        return new AddressDto("Mouad",
                "EL Fakir",
                "Champs-Élysées",
                "75000",
                "Paris",
                "France");
    }

    private List<OrderEntryDto> entries() {
        return new ArrayList<OrderEntryDto>() {
            {
                add(new OrderEntryDto("Apple IPhone X", 1, 500d));
                add(new OrderEntryDto("Samsung Galaxy s8", 2, 400d));
            }
        };
    }
}
