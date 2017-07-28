package com.iopex.imarket.service.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.iopex.imarket.service.entity.Contact;


public interface ContactRepository extends JpaRepository<Contact, Integer> {


}
