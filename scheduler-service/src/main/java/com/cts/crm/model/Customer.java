package com.cts.crm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer {
	private int customerId;
	private String name;
	private String mobileNumber;
	private String email;
	private String circle;
	private String dp;	
}
