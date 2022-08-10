package com.ledgerco.domain;

import java.util.ArrayList;

public class Payments extends ArrayList<Payment> {
    public int paidAfter(int installmentNumber) {
        return this.stream()
                .filter(p -> p.getAfterInstallment() == installmentNumber)
                .mapToInt(Payment::getAmount)
                .sum();
    }
}
