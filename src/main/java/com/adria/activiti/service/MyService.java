package com.adria.activiti.service;
 
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adria.activiti.DTO.DemandesDTO;
import com.adria.activiti.entities.Demande;
import com.adria.activiti.entities.Person;
import com.adria.activiti.repositories.DemandeRepo;
import com.adria.activiti.repositories.PersonRepository;

@Service
public class MyService {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private PersonRepository personRepository;
    
    @Autowired
    private DemandeRepo demandeRepo;

    public void startProcess(String assignee) {

        Person person = personRepository.findOne(new Long("assignee"));

        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("id", person.getId_person());
        runtimeService.startProcessInstanceByKey("oneTaskProcess", variables);
    }

    public List<Task> getTasks(String assignee) {
        return taskService.createTaskQuery().taskAssignee(assignee).list();
    }

    public void createDemoUsers() {
        if (personRepository.findAll().size() == 0) {
            personRepository.save(new Person("jbarrez", "Joram", "Barrez", new Date()));
            personRepository.save(new Person("trademakers", "Tijs", "Rademakers", new Date()));
        }
    }

	public void createDemande(DemandesDTO demandeDTO) {
		
		Demande demande=new Demande();
		demande.setDure(new Integer(demandeDTO.getDure()));
		demande.setCreationDate(new Date());
		demande.setStatus("en cours");
		Person person=personRepository.findOne(new Long(demandeDTO.getUserId()));
		demande.setPerson(person);
		demandeRepo.saveAndFlush(demande);
		Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("id_superior", person.getId_person());
        runtimeService.startProcessInstanceByKey("oneTaskProcess", variables);
	}
	
	public List<Demande> getPersonDemandes(Person person){
		return demandeRepo.findByPerson(person);
	}
	
	public Person findPersonById(Long id){
		return personRepository.findOne(id);
	}

}
