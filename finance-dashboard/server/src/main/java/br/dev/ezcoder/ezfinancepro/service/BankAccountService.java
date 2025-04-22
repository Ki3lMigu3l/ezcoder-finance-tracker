package br.dev.ezcoder.ezfinancepro.service;

import br.dev.ezcoder.ezfinancepro.model.entity.BankAccount;
import br.dev.ezcoder.ezfinancepro.repositories.BankAccountRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    public BankAccount registrer(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }

    public List<BankAccount> findAll() {
        return bankAccountRepository.findAll();
    }

    public Optional<BankAccount> findById(String id) {
        return bankAccountRepository.findById(id);
    }

    public BankAccount update(@Valid BankAccount request) {
        return bankAccountRepository.save(request);
    }

    public void delete(String id) {
        bankAccountRepository.deleteById(id);
    }
}
