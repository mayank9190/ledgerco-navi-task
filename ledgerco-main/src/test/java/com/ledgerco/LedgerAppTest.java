package com.ledgerco;

import com.ledgerco.io.output.OutputHandler;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class LedgerAppTest {

    @Test
    void shouldCreateLoansAndPrintBalance() throws Exception {
        OutputHandler outputHandler = mock(OutputHandler.class);
        LedgerApp ledgerApp = new LedgerApp(outputHandler);
        ledgerApp.addLoan("bank1", "borrower1", 100, 1, 20);
        ledgerApp.addLoan("bank2", "borrower2", 200, 1, 20);

        ledgerApp.balance("bank1", "borrower1", 6);
        ledgerApp.balance("bank2", "borrower2", 6);

        verify(outputHandler).printBalance("bank1", "borrower1", 60, 6);
        verify(outputHandler).printBalance("bank2", "borrower2", 120, 6);
    }

    @Test
    void shouldCreateLoansPaymentsAndPrintBalance() throws Exception {
        OutputHandler outputHandler = mock(OutputHandler.class);
        LedgerApp ledgerApp = new LedgerApp(outputHandler);
        ledgerApp.addLoan("bank1", "borrower1", 100, 1, 20);
        ledgerApp.addLoan("bank2", "borrower2", 200, 1, 20);
        ledgerApp.addPayment("bank1", "borrower1", 3, 50);
        ledgerApp.addPayment("bank2", "borrower2", 4, 60);

        ledgerApp.balance("bank1", "borrower1", 6);
        ledgerApp.balance("bank2", "borrower2", 6);

        verify(outputHandler).printBalance("bank1", "borrower1", 110, 1);
        verify(outputHandler).printBalance("bank2", "borrower2", 180, 3);
    }

    @Test
    void shouldThrowExceptionIfLoanNotFound() throws Exception {
        LedgerApp ledgerApp = new LedgerApp(null);
        assertThatThrownBy(() -> ledgerApp
                .addPayment("bank1", "borrower1", 3, 50))
                .isInstanceOf(Exception.class);
    }
}