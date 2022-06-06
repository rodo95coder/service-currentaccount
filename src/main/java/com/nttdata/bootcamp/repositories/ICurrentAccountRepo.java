package com.nttdata.bootcamp.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.nttdata.bootcamp.models.CurrentAccount;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICurrentAccountRepo extends ReactiveMongoRepository<CurrentAccount, String>{
	public Flux<CurrentAccount> findByIdCustomer(String idCustomer);
	public Mono<CurrentAccount> findByIdCustomerAndTypeCustomer(String idCustomer, String typeCustomer);
}