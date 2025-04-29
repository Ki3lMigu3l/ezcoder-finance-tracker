package br.dev.ezcoder.ezfinancepro.exception;

public class BankAccountNotFoundException extends RuntimeException {
    public BankAccountNotFoundException(String id) {
        super("Bank account with id: " + id + " not found!");
    }
}
