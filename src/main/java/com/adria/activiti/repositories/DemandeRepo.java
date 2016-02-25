package com.adria.activiti.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adria.activiti.entities.Demande;
import com.adria.activiti.entities.Person;

public interface DemandeRepo extends JpaRepository<Demande, Long>{

	public List<Demande> findByPerson(Person person);
}
