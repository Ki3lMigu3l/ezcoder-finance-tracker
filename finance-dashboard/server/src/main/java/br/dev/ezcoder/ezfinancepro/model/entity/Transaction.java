package br.dev.ezcoder.ezfinancepro.model.entity;

import br.dev.ezcoder.ezfinancepro.model.dto.request.TransactionRequestDTO;
import br.dev.ezcoder.ezfinancepro.model.enums.PaymentMethod;
import br.dev.ezcoder.ezfinancepro.model.enums.TransactionType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Document(collection = "transactions")
public class Transaction {

  @Id
  private String id;

  @NotBlank
  private String userId;

  @NotNull
  @PositiveOrZero
  private BigDecimal amount;

  @NotNull
  private LocalDate date;

  private String description;

  private String notes;

  @NotNull
  private TransactionType type;

  @DBRef
  private Category category;

  private boolean isRecurring;

  private boolean isCreditCard;

  private String cardInvoiceId;

  @NotNull
  private PaymentMethod paymentMethod;

  public Transaction(@Valid TransactionRequestDTO request) {
    this.userId = request.userId();
    this.amount = request.amount();
    this.date = request.date();
    this.description = request.description();
    this.notes = request.notes();
    this.type = request.type();
    this.isRecurring = request.isRecurring();
    this.isCreditCard = request.isCreditCard();
    this.cardInvoiceId = request.cardInvoiceId();
    this.paymentMethod = request.paymentMethod();
  }
}
