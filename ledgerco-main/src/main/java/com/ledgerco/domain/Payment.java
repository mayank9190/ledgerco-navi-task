package com.ledgerco.domain;

public class Payment {
    private final int afterInstallment;
    private final int amount;

    public Payment(int afterInstallment, int amount) {
        this.afterInstallment = afterInstallment;
        this.amount = amount;
    }

    public int getAfterInstallment() {
        return afterInstallment;
    }

    public int getAmount() {
        return amount;
    }
}
