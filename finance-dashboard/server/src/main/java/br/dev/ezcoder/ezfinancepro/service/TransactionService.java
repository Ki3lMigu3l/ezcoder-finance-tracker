package br.dev.ezcoder.ezfinancepro.service;

import br.dev.ezcoder.ezfinancepro.model.entity.Transaction;
import br.dev.ezcoder.ezfinancepro.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public Transaction create(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Optional<Transaction> findById(String id) {
        return transactionRepository.findById(id);
    }

    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    public Transaction update(Transaction transactionFound) {
        return transactionRepository.save(transactionFound);
    }

    public void deleteTransactionById(String id) {

    }
}
