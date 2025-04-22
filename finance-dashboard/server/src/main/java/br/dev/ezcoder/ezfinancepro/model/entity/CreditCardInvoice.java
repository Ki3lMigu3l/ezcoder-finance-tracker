package br.dev.ezcoder.ezfinancepro.model.entity;

import br.dev.ezcoder.ezfinancepro.model.enums.InvoiceStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "credit_card_invoices")
public class CreditCardInvoice {

  @Id
  private String id;

  @NotBlank
  private String userId;

  @NotBlank
  private String bankAccountId;

  @NotNull
  private LocalDate startDate;

  @NotNull
  private LocalDate endDate;

  @NotNull
  private LocalDate dueDate;

  private BigDecimal totalAmount;
  private BigDecimal paidAmount;
  private InvoiceStatus status;

  @DBRef
  private List<Transaction> transactions;
}
