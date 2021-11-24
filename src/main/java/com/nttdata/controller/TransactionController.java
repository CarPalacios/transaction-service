package com.nttdata.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.model.Transaction;
import com.nttdata.service.ITransactionService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
	 
	@Autowired
	private ITransactionService service;
	
	@GetMapping
	public Mono<ResponseEntity<Flux<Transaction>>> findAll() {
		
		Flux<Transaction> transaction = service.findAll();
		
		return Mono.just(ResponseEntity
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(transaction));							
		
	}
	
	@PostMapping
	public Mono<ResponseEntity<Transaction>> create(@RequestBody Transaction transaction, final ServerHttpRequest request) {
		
		return service.create(transaction)
				.map(c -> ResponseEntity
						.created(URI.create(request.getURI().toString().concat("/").concat(c.getId())))
						.contentType(MediaType.APPLICATION_JSON)
						.body(c));
		
	}
		
}