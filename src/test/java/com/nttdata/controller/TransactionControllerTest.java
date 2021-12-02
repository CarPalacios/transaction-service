package com.nttdata.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.nttdata.model.Transaction;
import com.nttdata.repository.TransactionRepository;
import com.nttdata.service.impl.TransactionServiceImpl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Import(TransactionServiceImpl.class)
@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = TransactionController.class, excludeAutoConfiguration = {ReactiveSecurityAutoConfiguration.class})
class TransactionControllerTest {
  
  @Autowired
  private WebTestClient transaction;
  
  @MockBean
  private TransactionRepository repository;

  @Test
  void testFindAll() {
    
    Transaction transactions = new Transaction("1","retiro",20.5,"455180523524126","Primera Transaccion",LocalDateTime.now());
    List<Transaction> list = new ArrayList<>();
    list.add(transactions);
    Flux<Transaction> fluxtransaction = Flux.fromIterable(list);
    
    Mockito.when(repository.findAll()).thenReturn(fluxtransaction);
    transaction.get().uri("/transaction").accept(MediaType.APPLICATION_JSON)
    .exchange().expectStatus().isOk().expectHeader().contentType(MediaType.APPLICATION_JSON)
    .expectBodyList(Transaction.class).hasSize(1);
  }

  @Test
  void testCreate() {
    Transaction transactions = new Transaction("2","deposito",50.0,"4551805235241265","Segunda Transaccion",LocalDateTime.now());
    
    Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(Mono.just(transactions));
    transaction.post().uri("/transaction")
    .contentType(MediaType.APPLICATION_JSON)
    .accept(MediaType.APPLICATION_JSON)
    .body(Mono.just(transactions), Transaction.class)
    .exchange()
    .expectStatus().isCreated()
    .expectHeader().contentType(MediaType.APPLICATION_JSON)
    .expectBody()
    .jsonPath("$.id").isNotEmpty()
    .jsonPath("$.transactionType").isEqualTo("deposito")
    .jsonPath("$.transactionAmount").isEqualTo("50.0")
    .jsonPath("$.cardNumber").isEqualTo("4551805235241265")
    .jsonPath("$.description").isEqualTo("Segunda Transaccion");
  }

}
