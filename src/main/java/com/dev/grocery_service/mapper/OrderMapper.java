package com.dev.grocery_service.mapper;

import com.dev.grocery_service.dto.OrderDto;
import com.dev.grocery_service.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class OrderMapper {

    public abstract Order toOrder(OrderDto orderDto);

    public abstract OrderDto toOrderDto(Order order);
}
