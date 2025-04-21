package br.dev.ezcoder.ezfinancepro.controller;

import br.dev.ezcoder.ezfinancepro.model.dto.request.CategoryRequestDTO;
import br.dev.ezcoder.ezfinancepro.model.dto.request.TransactionRequestDTO;
import br.dev.ezcoder.ezfinancepro.model.entity.Category;
import br.dev.ezcoder.ezfinancepro.model.entity.Transaction;
import br.dev.ezcoder.ezfinancepro.model.entity.User;
import br.dev.ezcoder.ezfinancepro.service.CategoryService;
import br.dev.ezcoder.ezfinancepro.service.TransactionService;
import br.dev.ezcoder.ezfinancepro.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;
    private final UserService userService;
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction (@RequestBody @Valid TransactionRequestDTO request) {
        User userFound = userService.findUser(request.userId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));

        Category category = new Category();
        if (request.category() != null) {
            Category newCategory = new Category();
            newCategory.setUserId(request.userId());
            newCategory.setName(request.category().name());
            newCategory.setIcon(request.category().icon());
            newCategory.setColorHex(request.category().colorHex());
            newCategory.setType(request.category().type());
            newCategory.setDefault(request.category().isDefault());

            category = categoryService.create(newCategory);
        }

        Transaction transaction = new Transaction(request);
        transaction.setCategory(category);
        Transaction createdTransaction = transactionService.create(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTransaction);
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions () {
        return ResponseEntity.status(HttpStatus.OK).body(transactionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransaction (@PathVariable String id) {
        Transaction transactionFound = transactionService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction Not found!"));

        return ResponseEntity.status(HttpStatus.OK).body(transactionFound);
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
