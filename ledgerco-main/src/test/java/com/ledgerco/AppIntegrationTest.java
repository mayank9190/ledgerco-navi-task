package com.ledgerco;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class AppIntegrationTest {

    private PrintStream printStream;
    private ByteArrayOutputStream byteArrayOutputStream;

    @BeforeEach
    void setUp() {
        byteArrayOutputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(byteArrayOutputStream);
        System.setOut(printStream);
    }

    @AfterEach
    void tearDown() {
        System.setOut(System.out);
    }

    @Test
    void shouldProcessInputWithLoans() throws Exception {
        App.main(new String[]{
                Paths.get("src/test/data/test-integration-loans").toAbsolutePath().toString()
        });
        byteArrayOutputStream.flush();
        assertThat(byteArrayOutputStream.toString().split(System.lineSeparator()))
                .containsExactly(
                        "IDIDI Dale 1000 55",
                        "IDIDI Dale 8000 20",
                        "MBI Harry 1044 12",
                        "MBI Harry 0 24");
    }

    @Test
    void shouldProcessInputWithLoansAndPayments() throws Exception {
        App.main(new String[]{
                Paths.get("src/test/data/test-integration-loans-with-payments").toAbsolutePath().toString()
        });
        byteArrayOutputStream.flush();
        assertThat(byteArrayOutputStream.toString().split(System.lineSeparator()))
                .containsExactly(
                        "IDIDI Dale 1326 9",
                        "IDIDI Dale 3652 4",
                        "UON Shelly 15856 3",
                        "MBI Harry 9044 10");
    }
}