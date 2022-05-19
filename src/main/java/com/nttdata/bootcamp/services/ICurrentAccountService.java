package com.nttdata.bootcamp.services;

import com.nttdata.bootcamp.models.CurrentAccount;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICurrentAccountService {
	public Flux<CurrentAccount> findAll();
	public Mono<CurrentAccount> findById(String id);
	public Mono<CurrentAccount> save(CurrentAccount currentAccount);
	public Mono<Void> delete(CurrentAccount currentAccount);
}
