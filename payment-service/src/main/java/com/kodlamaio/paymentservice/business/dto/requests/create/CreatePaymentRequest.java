package com.kodlamaio.paymentservice.business.dto.requests.create;

import com.kodlamaio.commonpackage.utils.dto.PaymentRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentRequest extends PaymentRequest {
    private double balance;
}
