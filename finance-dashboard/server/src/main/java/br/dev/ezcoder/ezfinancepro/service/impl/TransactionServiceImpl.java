package br.dev.ezcoder.ezfinancepro.service.impl;

import br.dev.ezcoder.ezfinancepro.exception.TransactionNotFoundException;
import br.dev.ezcoder.ezfinancepro.model.dto.request.TransactionRequest;
import br.dev.ezcoder.ezfinancepro.model.dto.response.TransactionResponse;
import br.dev.ezcoder.ezfinancepro.model.entity.Category;
import br.dev.ezcoder.ezfinancepro.model.entity.Transaction;
import br.dev.ezcoder.ezfinancepro.repository.TransactionRepository;
import br.dev.ezcoder.ezfinancepro.service.CategoryService;
import br.dev.ezcoder.ezfinancepro.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final CategoryService categoryService;

    @Override
    public Transaction registerTransaction(Category category, Transaction transaction) {
        transaction.setCategory(category);
        return transactionRepository.save(transaction);
    }

    @Override
    public List<TransactionResponse> findAllTransactions() {
        return mapTransactionToResponseDTOs(transactionRepository.findAll());
    }

    @Override
    public Transaction findTransactionById(String id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException(id));
    }

    @Override
    public Transaction updateTransaction(String id, TransactionRequest request) {
        Transaction transaction = findTransactionById(id);

        Category category = null;
        if (request.category() != null) {
            category = categoryService.registerCategory(request);
            transaction.setCategory(category);
        } else {
            category = new Category(request);
        }

        BeanUtils.copyProperties(request, transaction);
        return transactionRepository.save(transaction);
    }

    @Override
    public void deleteTransaction(String id) {
        Transaction transactionFound = findTransactionById(id);
        transactionRepository.delete(transactionFound);
    }

    public List<TransactionResponse> mapTransactionToResponseDTOs (List<Transaction> transactions) {
        Objects.requireNonNull(transactions, "Users List cannot be Null");

        return transactions.stream()
                .filter(Objects::nonNull)
                .map(this::entityToResponseDTO)
                .toList();
    }

    private TransactionResponse entityToResponseDTO(Transaction transaction) {
        return TransactionResponse.entityToResponse(transaction);
    }
}
