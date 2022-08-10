package com.ledgerco.io.input.commands;

import com.ledgerco.LedgerApp;

public class LoanCommand implements ICommand {
    private final String input;

    public LoanCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(LedgerApp ledgerApp) {
        String[] strings = input.split(" ");
        ledgerApp.addLoan(strings[1], strings[2],
                Double.parseDouble(strings[3]), Double.parseDouble(strings[4]), Double.parseDouble(strings[5]));
    }
}
