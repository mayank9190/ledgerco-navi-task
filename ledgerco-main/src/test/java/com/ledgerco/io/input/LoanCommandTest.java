package com.ledgerco.io.input;

import com.ledgerco.LedgerApp;
import com.ledgerco.io.input.commands.LoanCommand;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class LoanCommandTest {
    @Test
    void shouldCreateLoan() {
        LedgerApp ledgerApp = mock(LedgerApp.class);
        LoanCommand loanCommand = new LoanCommand("x bank borrower 1 2 3");

        loanCommand.execute(ledgerApp);

        verify(ledgerApp).addLoan("bank", "borrower", 1, 2, 3);
    }
}