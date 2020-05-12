package com.cts.crm.model;

import java.sql.Date;

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
public class Subscription{
	private int id;
	private String name;
	private Date expiryDate;
	private boolean active;
	private Customer customerId;	
}
