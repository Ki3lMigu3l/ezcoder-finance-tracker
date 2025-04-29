package br.dev.ezcoder.ezfinancepro.controller;

import br.dev.ezcoder.ezfinancepro.model.dto.request.TransactionRequest;
import br.dev.ezcoder.ezfinancepro.model.dto.response.TransactionResponse;
import br.dev.ezcoder.ezfinancepro.model.entity.Category;
import br.dev.ezcoder.ezfinancepro.model.entity.Transaction;
import br.dev.ezcoder.ezfinancepro.model.entity.UserModel;
import br.dev.ezcoder.ezfinancepro.service.CategoryService;
import br.dev.ezcoder.ezfinancepro.service.TransactionService;
import br.dev.ezcoder.ezfinancepro.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final CategoryService categoryService;
    private final TransactionService transactionService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<TransactionResponse> createTransaction (@RequestBody @Valid TransactionRequest request) {
        UserModel user = userService.findUserById(request.userId());

        Category category = null;
        if (request.category() != null) {
            category = categoryService.registerCategory(request);
        }

        Transaction transaction = transactionService.registerTransaction(category, new Transaction(request));

        URI location = buildTransactionUri(transaction.getId());
        return ResponseEntity.created(location).body(TransactionResponse.entityToResponse(transaction));
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponse>> getAllTransactions () {
        return ResponseEntity.ok(transactionService.findAllTransactions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponse> getTransaction (@PathVariable String id) {
        return ResponseEntity.ok(TransactionResponse.entityToResponse(transactionService.findTransactionById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionResponse> updateTransaction (@PathVariable String id,
                                                          @RequestBody @Valid TransactionRequest request) {
        return ResponseEntity.ok(TransactionResponse.entityToResponse(transactionService.updateTransaction(id, request)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTransaction (@PathVariable String id) {
        transactionService.deleteTransaction(id);
    }

    private URI buildTransactionUri(String transactionId) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(transactionId)
                .toUri();
    }
}
