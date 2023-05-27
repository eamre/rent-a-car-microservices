package com.kodlamaio.paymentservice.business.rules;

import com.kodlamaio.commonpackage.utils.dto.PaymentRequest;
import com.kodlamaio.commonpackage.utils.exceptions.BusinessException;
import com.kodlamaio.paymentservice.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class PaymentBusinessRules {
    private final PaymentRepository repository;

    public void checkIfPaymentExists(UUID id) {
        if (!repository.existsById(id)) {
            throw new BusinessException("PAYMENT_NOT_EXISTS");
        }
    }

    public void checkIfBalanceIfEnough(double balance, double price) {
        if (balance < price) {
            throw new BusinessException("PAYMENT_NOT_ENOUGH_MONEY");
        }
    }

    public void checkIfCardExists(String cardNumber) {
        if (repository.existsByCardNumber(cardNumber)) {
            throw new BusinessException("CARD_NUMBER_ALREADY_EXIST");
        }
    }

    public void checkIfPaymentIsValid(PaymentRequest request) {
        if (!repository.existsByCardNumberAndCardHolderAndCardExpirationYearAndCardExpirationMonthAndCardCvv(
                request.getCardNumber(),
                request.getCardHolder(),
                request.getCardExpirationYear(),
                request.getCardExpirationMonth(),
                request.getCardCvv()
        )) {
            throw new BusinessException("NOT_VALID_PAYMENT");
        }
    }
}
