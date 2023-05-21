package com.kodlamaio.paymentservice.business.dto.requests.update;

import com.kodlamaio.commonpackage.utils.dto.PaymentRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePaymentRequest extends PaymentRequest {
    private double balance;
}
