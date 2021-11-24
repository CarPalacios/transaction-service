package com.nttdata.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nttdata.model.Transaction;
import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**Se crea la clase Transaction donde se definen las variables.*/
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "transaction")
@Data
public class Transaction {
  
  @Id
  private String id;

  @Field(name = "transactionType")
  private String transactionType;

  @Field(name = "transactionAmount")
  private Double transactionAmount;
  
  @Field(name = "cardNumber")
  private String cardNumber;

  @Field(name = "description")
  private String description;

  @Field(name = "transactionDate")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime transactionDate = LocalDateTime.now();
  
}
