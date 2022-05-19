package com.nttdata.bootcamp.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "currentaccounts")
@Builder
public class CurrentAccount {
	@Id
	private String id;
	private String idCustomer;
	private String accountingBalance;
	private String maintenance;
}
