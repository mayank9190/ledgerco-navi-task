package com.ledgerco.domain;

public class Loan {
    private static final int MONTHS_IN_YEAR = 12;
    private final String bankName;
    private final String borrowerName;
    private final double principal;
    private final double term;
    private final double rateOfInterest;
    private final Payments payments;

    public Loan(String bankName, String borrowerName, double principal, double term, double rateOfInterest) {
        this.bankName = bankName;
        this.borrowerName = borrowerName;
        this.principal = principal;
        this.term = term;
        this.rateOfInterest = rateOfInterest;
        this.payments = new Payments();
    }

    public boolean isFor(String bank, String borrower) {
        return bankName.equalsIgnoreCase(bank) && borrowerName.equalsIgnoreCase(borrower);
    }

    public int remainingInstallmentsAfter(int installmentNumber) {
        double remainingAmount = totalDebt() - amountPaidAfterInstallments(installmentNumber);
        return ceilToInt(remainingAmount / installmentAmount());
    }

    public int amountPaidAfterInstallments(int installmentNumber) {
        int amountPaid = 0;
        for (int i = 0; i <= installmentNumber; i++) {
            if (i > 0) {
                amountPaid = amountPaid + installmentAmountAfter(amountPaid);
            }
            amountPaid = amountPaid + payments.paidAfter(i);
        }

        return amountPaid;
    }

    public void addPayment(int afterInstallment, int amount) {
        this.payments.add(new Payment(afterInstallment, amount));
    }

    private int installmentAmountAfter(int amountPaid) {
        int installmentAmount = installmentAmount();
        double totalDebt = totalDebt();
        if (totalDebt - amountPaid > installmentAmount) {
            return installmentAmount;
        }

        return ceilToInt(Math.max(totalDebt - amountPaid, 0));
    }

    private int termInMonths() {
        return ceilToInt(term * MONTHS_IN_YEAR);
    }

    private int installmentAmount() {
        return ceilToInt(totalDebt() / termInMonths());
    }

    private double totalDebt() {
        return principal + interest();
    }

    private double interest() {
        return principal * term * rateOfInterest / 100;
    }

    private int ceilToInt(double number) {
        return (int) Math.ceil(number);
    }
}
