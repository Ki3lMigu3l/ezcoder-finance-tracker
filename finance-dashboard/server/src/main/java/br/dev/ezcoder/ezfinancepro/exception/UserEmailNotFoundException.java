package br.dev.ezcoder.ezfinancepro.exception;

public class UserEmailNotFoundException extends RuntimeException {
    public UserEmailNotFoundException (String email) {
        super("Bank account with email: " + email + " not found!");
    }
}
