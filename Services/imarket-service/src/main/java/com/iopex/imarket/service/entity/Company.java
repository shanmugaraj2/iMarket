package com.iopex.imarket.service.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "company_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company implements Serializable{

	
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Access(AccessType.PROPERTY)
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="industry")
	private String industry;
	
	@Column(name="emp_count")
	private String empCount;
	
	@Column(name="website")
	private String website;
	
	@Column(name = "country")
	private String country;
	
	@Column(name="email")
	private String email;
	
	@Column(name="revenue")
	private String revenue;
	
	@Column(name="lead_source")
	private String leadSource;
	
	@Column(name="status")
	private int status;
	
	@OneToMany(mappedBy="company", cascade = CascadeType.ALL)
	private Set<Contact> contacts;
	
	
/*	@Override
    public String toString() {
        String result = String.format(
                "Company[id=%d, name='%s']%n",
                id, name);
        if (contacts != null) {
            for(Contact ct : contacts) {
                result += String.format(
                        "Contact[id=%d, name='%s']%n",
                        ct.getId(), ct.getName());
            }
        }

        return result;
    }*/
	
}
