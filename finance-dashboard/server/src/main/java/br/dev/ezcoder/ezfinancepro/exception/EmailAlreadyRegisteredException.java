package br.dev.ezcoder.ezfinancepro.exception;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class EmailAlreadyRegisteredException extends RuntimeException {
    public EmailAlreadyRegisteredException(@Email @NotBlank String email) {
        super("E-mail " + email + " already Registered!");
    }
}
