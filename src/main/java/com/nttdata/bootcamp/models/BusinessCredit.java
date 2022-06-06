package com.nttdata.bootcamp.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BusinessCredit {
	private String id;
	private String idCustomerEnterprise;
	private String accountingBalance;
	private String availableBalance;
}