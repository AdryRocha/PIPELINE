package com.example.demo.persistence;

import com.example.demo.model.Phonebook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhonebookPersistence extends JpaRepository<Phonebook, Integer>{
    public List<Phonebook> findByNameContaining(String name);
    
    public List<Phonebook> findByName(String name);
}
