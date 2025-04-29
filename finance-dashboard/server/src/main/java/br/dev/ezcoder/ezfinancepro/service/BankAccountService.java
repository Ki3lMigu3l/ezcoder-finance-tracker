package br.dev.ezcoder.ezfinancepro.service;

import br.dev.ezcoder.ezfinancepro.model.dto.request.BankAccountRequest;
import br.dev.ezcoder.ezfinancepro.model.dto.response.BankAccountResponse;
import br.dev.ezcoder.ezfinancepro.model.entity.BankAccount;

import java.util.List;

public interface BankAccountService {

    BankAccount registerBankAccount (BankAccountRequest request);
    List<BankAccountResponse> findAllBankAccount();
    BankAccount findBankAccount (String id);
    BankAccount updateBankAccount (String id, BankAccountRequest request);
    void deleteBankAccount (String id);
}
