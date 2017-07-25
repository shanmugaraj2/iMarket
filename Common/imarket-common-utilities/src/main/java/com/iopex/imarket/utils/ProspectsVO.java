package com.iopex.imarket.utils;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProspectsVO implements Serializable{

	
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String companyName;
	private String contactPerson1;
	private String contactJobTitle1;
	private String contactEmail1;
	private String contactPhone1;
	private String contactPerson2;
	private String contactJobTitle2;
	private String contactEmail2;
	private String contactPhone2;
	private String contactPerson3;
	private String contactJobTitle3;
	private String contactEmail3;
	private String contactPhone3;
	private String industry;
	private String employeeCount;
	private String website;
	private String country;
	private String companyEmail;
	private String revenue;
	private String leadSource;
	private int isDeleted;
	
	
}
