package com.ledgerco;

import com.ledgerco.domain.Loan;
import com.ledgerco.io.output.OutputHandler;

import java.util.ArrayList;
import java.util.List;

public class LedgerApp {
    private final List<Loan> loans;
    private final OutputHandler outputHandler;

    public LedgerApp(OutputHandler outputHandler) {
        this.outputHandler = outputHandler;
        this.loans = new ArrayList<>();
    }

    public void addLoan(String bank, String borrower, double principal, double term, double rateOfInterest) {
        loans.add(new Loan(bank, borrower, principal, term, rateOfInterest));
    }

    public void addPayment(String bank, String borrower, int afterInstallment, int amount) throws Exception {
        Loan loan = findLoanFor(bank, borrower);
        loan.addPayment(afterInstallment, amount);
    }

    public void balance(String bank, String borrower, int installmentNumber) throws Exception {
        Loan loan = findLoanFor(bank, borrower);

        outputHandler.printBalance(bank, borrower,
                loan.amountPaidAfterInstallments(installmentNumber), loan.remainingInstallmentsAfter(installmentNumber));
    }

    private Loan findLoanFor(String bank, String borrower) throws Exception {
        return loans.stream()
                .filter(l -> l.isFor(bank, borrower))
                .findFirst()
                .orElseThrow(Exception::new);
    }
}
