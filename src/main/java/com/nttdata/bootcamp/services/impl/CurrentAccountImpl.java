package com.nttdata.bootcamp.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.bootcamp.models.CurrentAccount;
import com.nttdata.bootcamp.repositories.ICurrentAccountRepo;
import com.nttdata.bootcamp.services.ICurrentAccountService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CurrentAccountImpl implements ICurrentAccountService {

	@Autowired
	ICurrentAccountRepo carepo;

	@Override
	public Flux<CurrentAccount> findAll() {
		return carepo.findAll();
	}

	@Override
	public Mono<CurrentAccount> findById(String id) {
		return carepo.findById(id);
	}

	@Override
	public Mono<CurrentAccount> save(CurrentAccount currentAccount) {
		return carepo.save(currentAccount);
	}

	@Override
	public Mono<Void> delete(CurrentAccount currentAccount) {
		return carepo.delete(currentAccount);
	}
	
}
