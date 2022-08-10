package com.ledgerco.io.output;

import org.junit.jupiter.api.Test;

import java.io.PrintStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class OutputHandlerTest {
    @Test
    void shouldPrintBalance() {
        PrintStream out = mock(PrintStream.class);
        new OutputHandler(out)
                .printBalance("bank", "borrower", 100, 3);

        verify(out).println("bank borrower 100 3");
    }
}