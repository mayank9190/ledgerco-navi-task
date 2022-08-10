package com.ledgerco.io.input;

import com.ledgerco.LedgerApp;
import com.ledgerco.io.input.commands.PaymentCommand;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class PaymentCommandTest {
    @Test
    void shouldAddPayment() throws Exception {
        LedgerApp ledgerApp = mock(LedgerApp.class);
        PaymentCommand paymentCommand = new PaymentCommand("x bank borrower 100 3");

        paymentCommand.execute(ledgerApp);

        verify(ledgerApp).addPayment("bank", "borrower", 3, 100);
    }
}