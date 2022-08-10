package com.ledgerco.io.input;

import com.ledgerco.io.input.commands.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CommandFactoryTest {

    @Test
    void shouldCreateCorrectCommands() throws Exception {
        List<ICommand> commands = new CommandFactory().create(Arrays.asList("loan", "payment", "balance"));
        assertThat(commands.get(0)).isInstanceOf(LoanCommand.class);
        assertThat(commands.get(1)).isInstanceOf(PaymentCommand.class);
        assertThat(commands.get(2)).isInstanceOf(BalanceCommand.class);

        assertThatThrownBy(() -> new CommandFactory()
                .create(Arrays.asList("loan", "invalid")))
                .isInstanceOf(Exception.class);
    }
}