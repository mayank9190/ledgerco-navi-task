package com.ledgerco.io.input.commands;

import java.util.ArrayList;
import java.util.List;

public class CommandFactory {
    public List<ICommand> create(List<String> inputLines) throws Exception {
        ArrayList<ICommand> commands = new ArrayList<>();
        for (String input : inputLines) {
            commands.add(createFor(input));
        }
        return commands;
    }

    private ICommand createFor(String input) throws Exception {
        String commandString = input.split(" ")[0];

        if (commandString.equalsIgnoreCase("loan")) {
            return new LoanCommand(input);
        }
        if (commandString.equalsIgnoreCase("payment")) {
            return new PaymentCommand(input);
        }
        if (commandString.equalsIgnoreCase("balance")) {
            return new BalanceCommand(input);
        }

        throw new Exception();
    }
}
