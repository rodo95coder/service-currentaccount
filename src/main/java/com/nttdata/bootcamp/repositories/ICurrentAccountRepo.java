package com.nttdata.bootcamp.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.nttdata.bootcamp.models.CurrentAccount;

public interface ICurrentAccountRepo extends ReactiveMongoRepository<CurrentAccount, String>{

}
