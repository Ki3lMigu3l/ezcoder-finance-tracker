package br.dev.ezcoder.ezfinancepro.controller;

import br.dev.ezcoder.ezfinancepro.model.dto.request.BankAccountRequest;
import br.dev.ezcoder.ezfinancepro.model.dto.response.BankAccountResponse;
import br.dev.ezcoder.ezfinancepro.model.entity.BankAccount;
import br.dev.ezcoder.ezfinancepro.model.entity.UserModel;
import br.dev.ezcoder.ezfinancepro.service.BankAccountService;
import br.dev.ezcoder.ezfinancepro.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/bank-accounts")
@RequiredArgsConstructor
public class BankAccountController {

    private final BankAccountService bankAccountService;
    private final UserServiceImpl userService;

    @PostMapping
    public ResponseEntity<BankAccountResponse> registerBankAccount (@RequestBody @Valid BankAccountRequest request) {
        UserModel user = userService.findUserById(request.userId());

        BankAccount bankAccount = bankAccountService.registerBankAccount(request);
        URI location = buildBankAccountUri(bankAccount.getId());
        return ResponseEntity.created(location).body(BankAccountResponse.entityToResponse(bankAccount));
    }

    @GetMapping
    public ResponseEntity<List<BankAccountResponse>> findAllBankAccounts () {
        return ResponseEntity.ok(bankAccountService.findAllBankAccount());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankAccountResponse> getBankAccount (@PathVariable String id) {
        return ResponseEntity
                .ok(BankAccountResponse
                        .entityToResponse(bankAccountService.findBankAccount(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BankAccountResponse> updateBankAccount (@PathVariable String id, @RequestBody @Valid BankAccountRequest request) {
        return ResponseEntity
                .ok(BankAccountResponse
                        .entityToResponse(bankAccountService.updateBankAccount(id, request)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser (@PathVariable String id) {
        bankAccountService.deleteBankAccount(id);
    }

    private URI buildBankAccountUri(String id) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }

}
