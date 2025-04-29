package br.dev.ezcoder.ezfinancepro.service.impl;

import br.dev.ezcoder.ezfinancepro.exception.BankAccountNotFoundException;
import br.dev.ezcoder.ezfinancepro.model.dto.request.BankAccountRequest;
import br.dev.ezcoder.ezfinancepro.model.dto.response.BankAccountResponse;
import br.dev.ezcoder.ezfinancepro.model.entity.BankAccount;
import br.dev.ezcoder.ezfinancepro.repository.BankAccountRepository;
import br.dev.ezcoder.ezfinancepro.service.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    @Override
    public BankAccount registerBankAccount(BankAccountRequest request) {
        return bankAccountRepository.save(new BankAccount(request));
    }

    @Override
    public List<BankAccountResponse> findAllBankAccount() {
        return mapBankAccountToResponseDTOs(bankAccountRepository.findAll());
    }

    @Override
    public BankAccount findBankAccount(String id) {
        return bankAccountRepository.findById(id)
                .orElseThrow(() -> new BankAccountNotFoundException(id));
    }

    @Override
    public BankAccount updateBankAccount(String id, BankAccountRequest request) {
        BankAccount bankAccount = findBankAccount(id);
        BeanUtils.copyProperties(request, bankAccount);
        return bankAccountRepository.save(bankAccount);
    }

    @Override
    public void deleteBankAccount(String id) {
        BankAccount bankAccount = findBankAccount(id);
        bankAccountRepository.delete(bankAccount);
    }

    public List<BankAccountResponse> mapBankAccountToResponseDTOs (List<BankAccount> bankAccounts) {
        Objects.requireNonNull(bankAccounts, "Bank account cannot be Null");

        return bankAccounts.stream()
                .filter(Objects::nonNull)
                .map(this::entityToResponseDTO)
                .toList();
    }

    private BankAccountResponse entityToResponseDTO(BankAccount bankAccount) {
        return BankAccountResponse.entityToResponse(bankAccount);
    }

}
