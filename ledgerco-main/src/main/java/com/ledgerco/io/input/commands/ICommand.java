package com.ledgerco.io.input.commands;

public interface ICommand {
    void execute(com.ledgerco.LedgerApp ledgerApp) throws Exception;
}
