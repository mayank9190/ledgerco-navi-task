package com.ledgerco.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LoanTest {

    @Test
    void shouldCalculateCorrectNumberOfInstallmentsWithoutLumpSumPayments() {
        Loan loan = new Loan("bankName", "borrowerName",
                2000, 2, 2);

        assertThat(loan.remainingInstallmentsAfter(0)).isEqualTo(24);
        assertThat(loan.remainingInstallmentsAfter(24)).isEqualTo(0);
        assertThat(loan.remainingInstallmentsAfter(25)).isEqualTo(0);
        assertThat(loan.remainingInstallmentsAfter(26)).isEqualTo(0);
        assertThat(loan.remainingInstallmentsAfter(12)).isEqualTo(12);
    }

    @Test
    void shouldCalculateCorrectNumberOfInstallmentsWithoutLumpSumPaymentsWhenTermIsLessThanAnYear() {
        Loan loan = new Loan("bankName", "borrowerName",
                2000, 0.5, 2);

        assertThat(loan.remainingInstallmentsAfter(0)).isEqualTo(6);
        assertThat(loan.remainingInstallmentsAfter(6)).isEqualTo(0);
        assertThat(loan.remainingInstallmentsAfter(3)).isEqualTo(3);
    }

    @Test
    void shouldCalculateCorrectAmountPaidAfterNumberOfInstallmentsWithoutLumSumPayments() {
        Loan loan = new Loan("bankName", "borrowerName",
                2000, 0.5, 2);

        assertThat(loan.amountPaidAfterInstallments(0)).isEqualTo(0);
        assertThat(loan.amountPaidAfterInstallments(1)).isEqualTo(337);
        assertThat(loan.amountPaidAfterInstallments(2)).isEqualTo(337 * 2);
        assertThat(loan.amountPaidAfterInstallments(3)).isEqualTo(337 * 3);
        assertThat(loan.amountPaidAfterInstallments(4)).isEqualTo(337 * 4);
        assertThat(loan.amountPaidAfterInstallments(5)).isEqualTo(337 * 5);
        assertThat(loan.amountPaidAfterInstallments(6)).isEqualTo(2020);
        assertThat(loan.amountPaidAfterInstallments(7)).isEqualTo(2020);
    }

    @Test
    void ShouldCalculateNumberOfInstallmentsAs0IfTotalDebtIs0() {
        Loan zeroPrincipalLoan = new Loan("bankName", "borrowerName",
                0, 2, 2);
        assertThat(zeroPrincipalLoan.remainingInstallmentsAfter(0)).isEqualTo(0);
    }

    @Test
    void shouldCalculateCorrectNumberOfInstallmentsWithLumpSumPaymentsWhenTermIsLessThanAnYear() {
        Loan loan = new Loan("bankName", "borrowerName",
                2000, 0.5, 2);

        loan.addPayment(2, 600);

        assertThat(loan.remainingInstallmentsAfter(0)).isEqualTo(6);
        assertThat(loan.remainingInstallmentsAfter(1)).isEqualTo(5);
        assertThat(loan.remainingInstallmentsAfter(2)).isEqualTo(3);
        assertThat(loan.remainingInstallmentsAfter(3)).isEqualTo(2);
        assertThat(loan.remainingInstallmentsAfter(4)).isEqualTo(1);
        assertThat(loan.remainingInstallmentsAfter(5)).isEqualTo(0);
        assertThat(loan.remainingInstallmentsAfter(6)).isEqualTo(0);
        assertThat(loan.remainingInstallmentsAfter(7)).isEqualTo(0);
    }

    @Test
    void shouldCalculateCorrectAmountPaidAfterNumberOfInstallmentsWithLumSumPayments() {
        Loan loan = new Loan("bankName", "borrowerName",
                2000, 0.5, 2);

        loan.addPayment(2, 600);

        assertThat(loan.amountPaidAfterInstallments(0)).isEqualTo(0);
        assertThat(loan.amountPaidAfterInstallments(1)).isEqualTo(337);
        assertThat(loan.amountPaidAfterInstallments(2)).isEqualTo(337 * 2 + 600);
        assertThat(loan.amountPaidAfterInstallments(3)).isEqualTo(337 * 3 + 600);
        assertThat(loan.amountPaidAfterInstallments(4)).isEqualTo(337 * 4 + 600);
        assertThat(loan.amountPaidAfterInstallments(5)).isEqualTo(2020);
        assertThat(loan.amountPaidAfterInstallments(6)).isEqualTo(2020);
        assertThat(loan.amountPaidAfterInstallments(7)).isEqualTo(2020);
    }

    @Test
    void shouldCalculateCorrectNumberOfInstallmentsIfPaidMoreInLumpSumBeforeLastInstallment() {
        Loan loan = new Loan("bankName", "borrowerName",
                2000, 0.5, 2);

        loan.addPayment(5, 400);

        assertThat(loan.remainingInstallmentsAfter(0)).isEqualTo(6);
        assertThat(loan.remainingInstallmentsAfter(1)).isEqualTo(5);
        assertThat(loan.remainingInstallmentsAfter(2)).isEqualTo(4);
        assertThat(loan.remainingInstallmentsAfter(3)).isEqualTo(3);
        assertThat(loan.remainingInstallmentsAfter(4)).isEqualTo(2);
        assertThat(loan.remainingInstallmentsAfter(5)).isEqualTo(0);
        assertThat(loan.remainingInstallmentsAfter(6)).isEqualTo(0);
        assertThat(loan.remainingInstallmentsAfter(7)).isEqualTo(0);
    }

    @Test
    void shouldCalculateCorrectAmountPaidIfPaidMoreInLumpSumBeforeLastInstallment() {
        Loan loan = new Loan("bankName", "borrowerName",
                2000, 0.5, 2);

        loan.addPayment(5, 400);

        assertThat(loan.amountPaidAfterInstallments(0)).isEqualTo(0);
        assertThat(loan.amountPaidAfterInstallments(1)).isEqualTo(337);
        assertThat(loan.amountPaidAfterInstallments(2)).isEqualTo(337 * 2);
        assertThat(loan.amountPaidAfterInstallments(3)).isEqualTo(337 * 3);
        assertThat(loan.amountPaidAfterInstallments(4)).isEqualTo(337 * 4);
        assertThat(loan.amountPaidAfterInstallments(5)).isEqualTo(337 * 5 + 400);
        assertThat(loan.amountPaidAfterInstallments(6)).isEqualTo(2085);
        assertThat(loan.amountPaidAfterInstallments(7)).isEqualTo(2085);
    }

    @Test
    void shouldCalculateCorrectAmountIfLumpSumPaymentMadeBeforeAnyInstallment() {
        Loan loan = new Loan("bankName", "borrowerName",
                2000, 0.5, 2);

        loan.addPayment(0, 500);

        assertThat(loan.amountPaidAfterInstallments(0)).isEqualTo(500);
        assertThat(loan.amountPaidAfterInstallments(1)).isEqualTo(337 + 500);
        assertThat(loan.amountPaidAfterInstallments(2)).isEqualTo(337 * 2 + 500);
        assertThat(loan.amountPaidAfterInstallments(3)).isEqualTo(337 * 3 + 500);
        assertThat(loan.amountPaidAfterInstallments(4)).isEqualTo(337 * 4 + 500);
        assertThat(loan.amountPaidAfterInstallments(5)).isEqualTo(2020);
        assertThat(loan.amountPaidAfterInstallments(6)).isEqualTo(2020);
        assertThat(loan.amountPaidAfterInstallments(7)).isEqualTo(2020);
    }

    @Test
    void shouldCalculateCorrectAmountAndInstallmentsForMultipleLumpSumPayments() {
        Loan loan = new Loan("bankName", "borrowerName",
                2000, 0.5, 2);

        loan.addPayment(0, 500);
        loan.addPayment(2, 400);

        assertThat(loan.amountPaidAfterInstallments(0)).isEqualTo(500);
        assertThat(loan.amountPaidAfterInstallments(1)).isEqualTo(337 + 500);
        assertThat(loan.amountPaidAfterInstallments(2)).isEqualTo(337 * 2 + 500 + 400);
        assertThat(loan.amountPaidAfterInstallments(3)).isEqualTo(337 * 3 + 500 + 400);
        assertThat(loan.amountPaidAfterInstallments(4)).isEqualTo(2020);
        assertThat(loan.amountPaidAfterInstallments(5)).isEqualTo(2020);

        assertThat(loan.remainingInstallmentsAfter(0)).isEqualTo(5);
        assertThat(loan.remainingInstallmentsAfter(1)).isEqualTo(4);
        assertThat(loan.remainingInstallmentsAfter(2)).isEqualTo(2);
        assertThat(loan.remainingInstallmentsAfter(3)).isEqualTo(1);
        assertThat(loan.remainingInstallmentsAfter(4)).isEqualTo(0);
        assertThat(loan.remainingInstallmentsAfter(5)).isEqualTo(0);
    }
    @Test
    void shouldCalculateCorrectAmountAndInstallmentsForAllPaymentInLumpSum() {
        Loan loan = new Loan("bankName", "borrowerName",
                200, 0.5, 1);

        loan.addPayment(0, 201);

        assertThat(loan.amountPaidAfterInstallments(0)).isEqualTo(201);
        assertThat(loan.amountPaidAfterInstallments(1)).isEqualTo(201);

        assertThat(loan.remainingInstallmentsAfter(0)).isEqualTo(0);
        assertThat(loan.remainingInstallmentsAfter(1)).isEqualTo(0);
    }

    @Test
    void shouldIdentifyLoanByBankAndBorrowerName() {
        Loan loan = new Loan("bank", "borrower", 1, 1, 1);

        assertThat(loan.isFor("bank1", "borrower")).isFalse();
        assertThat(loan.isFor("bank", "borrower1")).isFalse();
        assertThat(loan.isFor("bank1", "borrower1")).isFalse();
        assertThat(loan.isFor("bank", "borrower")).isTrue();
    }
}