package br.dev.ezcoder.ezfinancepro.service;

import br.dev.ezcoder.ezfinancepro.model.dto.request.TransactionRequest;
import br.dev.ezcoder.ezfinancepro.model.dto.response.TransactionResponse;
import br.dev.ezcoder.ezfinancepro.model.entity.Category;
import br.dev.ezcoder.ezfinancepro.model.entity.Transaction;

import java.util.List;

public interface TransactionService {

    List<TransactionResponse> findAllTransactions();
    Transaction findTransactionById(String id);
    Transaction updateTransaction (String id, TransactionRequest request);
    void deleteTransaction (String id);
    Transaction registerTransaction(Category category, Transaction transaction);
}
