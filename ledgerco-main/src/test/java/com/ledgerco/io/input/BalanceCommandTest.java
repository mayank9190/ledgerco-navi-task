package com.ledgerco.io.input;

import com.ledgerco.LedgerApp;
import com.ledgerco.io.input.commands.BalanceCommand;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class BalanceCommandTest {
    @Test
    void shouldCreateLoan() throws Exception {
        LedgerApp ledgerApp = mock(LedgerApp.class);
        BalanceCommand balanceCommand = new BalanceCommand("x bank borrower 1");

        balanceCommand.execute(ledgerApp);

        verify(ledgerApp).balance("bank", "borrower", 1);
    }

}