package com.ledgerco.io.output;

import java.io.PrintStream;

public class OutputHandler {
    private final PrintStream out;

    public OutputHandler() {
        this(System.out);
    }

    public OutputHandler(PrintStream out) {
        this.out = out;
    }

    public void printBalance(String bank, String borrower, int amountPaid, int remainingInstallments) {
        out.println(bank + " " + borrower + " " + amountPaid + " " + remainingInstallments);
    }
}
