package com.iopex.imarket.service.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "company_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prospects implements Serializable{

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name="company_name")
	private String companyName;
	
	@Column(name="contact_person_1")
	private String contactPerson1;
	
	@Column(name="contact_job_title_1")
	private String contactJobTitle1;
	
	@Column(name="contact_email_1")
	private String contactEmail1;
	
	@Column(name="contact_phone_1")
	private String contactPhone1;
	
	@Column(name="contact_person_2")
	private String contactPerson2;
	
	@Column(name="contact_job_title_2")
	private String contactJobTitle2;

	@Column(name="contact_email_2")
	private String contactEmail2;
	
	@Column(name="contact_phone_2")
	private String contactPhone2;
	
	@Column(name="contact_person_3")
	private String contactPerson3;
	
	@Column(name="contact_job_title_3")
	private String contactJobTitle3;
	
	@Column(name="contact_email_3")
	private String contactEmail3;
	
	@Column(name="contact_phone_3")
	private String contactPhone3;
	
	@Column(name="industry")
	private String industry;
	
	@Column(name="employee_count")
	private String employeeCount;
	
	@Column(name="website")
	private String website;
	
	@Column(name = "country")
	private String country;
	
	@Column(name="company_email")
	private String companyEmail;
	
	@Column(name="revenue")
	private String revenue;
	
	@Column(name="lead_source")
	private String leadSource;
	
	@Column(name="is_deleted")
	private int isDeleted;

}
