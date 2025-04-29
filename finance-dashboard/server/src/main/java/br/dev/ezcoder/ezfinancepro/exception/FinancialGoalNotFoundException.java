package br.dev.ezcoder.ezfinancepro.exception;

public class FinancialGoalNotFoundException extends RuntimeException{

    public FinancialGoalNotFoundException(String id) {
        super("Financial Goal with id: " + id + " not found");
    }
}
