package br.dev.ezcoder.ezfinancepro.controller;

import br.dev.ezcoder.ezfinancepro.model.dto.request.TransactionRequestDTO;
import br.dev.ezcoder.ezfinancepro.model.entity.Category;
import br.dev.ezcoder.ezfinancepro.model.entity.Transaction;
import br.dev.ezcoder.ezfinancepro.model.enums.CategoryType;
import br.dev.ezcoder.ezfinancepro.service.TransactionService;
import br.dev.ezcoder.ezfinancepro.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction (@RequestBody @Valid TransactionRequestDTO request) {
        Category category = new Category(
                "1234",
                "68055b0993849e00269d04f7",
                "Alimentação",
                CategoryType.EXPENSE);

        Transaction transaction = new Transaction();

        transaction.setCategory(category);
        BeanUtils.copyProperties(request, transaction);
        return ResponseEntity.status(HttpStatus.OK).body(transactionService.create(transaction));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransaction (@PathVariable String id) {
        Transaction transactionFound = transactionService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction Not found!"));

        return ResponseEntity.status(HttpStatus.OK).body(transactionFound);
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions () {
        return ResponseEntity.status(HttpStatus.OK).body(transactionService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction (@PathVariable String id,
                                                          @RequestBody @Valid Transaction transaction) {

        Transaction transactionFound = transactionService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction Not found!"));

        transaction.setId(transactionFound.getId());
        return ResponseEntity.status(HttpStatus.OK).body(transactionService.update(transaction));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTransaction (@PathVariable String id) {

        Transaction transactionFound = transactionService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction Not found!"));

        transactionService.deleteTransactionById(id);
    }
}
