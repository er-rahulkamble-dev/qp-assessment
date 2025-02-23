package com.dev.grocery_service.mapper;

import com.dev.grocery_service.dto.PaymentDto;
import com.dev.grocery_service.entity.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class PaymentMapper {

    public abstract Payment  toPayment(PaymentDto paymentDto);

    public abstract PaymentDto toPaymentDto(Payment payment);

}
