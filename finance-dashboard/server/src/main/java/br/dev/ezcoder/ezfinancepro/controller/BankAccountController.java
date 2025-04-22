package br.dev.ezcoder.ezfinancepro.controller;

import br.dev.ezcoder.ezfinancepro.model.entity.BankAccount;
import br.dev.ezcoder.ezfinancepro.service.BankAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bankaccount")
@RequiredArgsConstructor
public class BankAccountController {

    private final BankAccountService bankAccountService;

    @PostMapping
    public ResponseEntity<BankAccount> registrerBankAccount (@RequestBody BankAccount request) {
        var bankAccount = new BankAccount();
        BeanUtils.copyProperties(request, bankAccount);
        return ResponseEntity.status(HttpStatus.CREATED).body(bankAccountService.registrer(bankAccount));
    }

    @GetMapping
    public ResponseEntity<List<BankAccount>> getAllBankAccounts () {
        return ResponseEntity.status(HttpStatus.OK).body(bankAccountService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankAccount> getBankAccount (@PathVariable String id) {
        BankAccount bankAccountFound = bankAccountService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bank Account not found!"));

        return ResponseEntity.status(HttpStatus.OK).body(bankAccountFound);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BankAccount> updateBankAccount (@PathVariable String id,
                                                       @RequestBody @Valid BankAccount request) {

        BankAccount bankAccountFound = bankAccountService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bank Account not found!"));

        request.setId(bankAccountFound.getId());
        return ResponseEntity.status(HttpStatus.OK).body(bankAccountService.update(request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBankAccount (@PathVariable String id) {

        BankAccount bankAccountFound = bankAccountService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bank Account not found!"));

        bankAccountService.delete(id);
    }
}
