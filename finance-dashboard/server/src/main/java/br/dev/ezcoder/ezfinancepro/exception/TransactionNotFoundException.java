package br.dev.ezcoder.ezfinancepro.exception;

public class TransactionNotFoundException extends RuntimeException {

    public TransactionNotFoundException(String id) {
        super("Transaction with id: " + id + " not found");
    }
}
