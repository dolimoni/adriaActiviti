package com.adria.activiti.service;
 
import java.util.ArrayList;
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
import com.adria.activiti.DTO.TaskDTO;
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

        Person superier = personRepository.findOne(new Long("assignee"));
        Person person = personRepository.findOne(new Long(2));

        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("id", superier.getId_person());
        variables.put("firstName", person.getFirstName());
        runtimeService.startProcessInstanceByKey("oneTaskProcess", variables);
    }

    public List<TaskDTO> getTasks(String assignee) {
    	List<TaskDTO> dtos=new ArrayList<TaskDTO>();
    	List<Task> tasks=taskService.createTaskQuery().taskAssignee(assignee).list();
    	
    	for (Task task : tasks) {
    		String dure=runtimeService.getVariables(task.getProcessInstanceId()).get("dure").toString();
    		String firstName=runtimeService.getVariables(task.getProcessInstanceId()).get("firstName").toString();
    		String id =task.getId();
    		dtos.add(new TaskDTO(dure,firstName,id));
		}
    	
        return dtos;
    }

//    public void createDemoUsers() {
//        if (personRepository.findAll().size() == 0) {
//            personRepository.save(new Person("jbarrez", "Joram", "Barrez", new Date()));
//            personRepository.save(new Person("trademakers", "Tijs", "Rademakers", new Date()));
//        }
//    }

	public void createDemande(DemandesDTO demandeDTO) {
		
		Demande demande=new Demande();
		demande.setDure(new Integer(demandeDTO.getDure()));
		demande.setCreationDate(new Date());
		demande.setStatus("en cours");
		Person person=personRepository.findOne(new Long(demandeDTO.getUserId()));
		demande.setPerson(person);
		demandeRepo.saveAndFlush(demande);
		Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("id_superior", person.getSuperior().getId_superior());
        variables.put("dure", demandeDTO.getDure());
        variables.put("firstName", person.getFirstName());
        variables.put("id_demande", demande.getId());
        runtimeService.startProcessInstanceByKey("oneTaskProcess", variables);
       
	}
	
	public void accepte(String id){
		
        
        Task task=taskService.createTaskQuery().taskId(id).singleResult();
        String processInstanceId=task.getProcessInstanceId(); 
        System.out.println(task+" task info");
        System.out.println(runtimeService.getVariables(processInstanceId));
        Demande demande = demandeRepo.findOne(new Long(runtimeService.getVariables(processInstanceId).get("id_demande").toString()));//STATIC
        System.out.println(demande);
        demande.setStatus("accepted");
        demandeRepo.saveAndFlush(demande);
        
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("accepted", true);
        System.out.println("completer la tache "+processInstanceId);
        taskService.complete(task.getId(), variables);
       
        //        taskService.complete(id, variables);
//        Task task=taskService.createTaskQuery().taskId(id).singleResult();
//        System.out.println(runtimeService.getVariables(task.getProcessInstanceId()));
//        Demande demande = demandeRepo.findOne(new Long(runtimeService.getVariables(task.getProcessInstanceId()).get("id_demande").toString()));

	}
	public void refuse(){
		Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("accepted", false);
        taskService.complete("14", variables);
	}
	
	public List<Demande> getPersonDemandes(Person person){
		return demandeRepo.findByPerson(person);
	}
	
	public Person findPersonById(Long id){
		return personRepository.findOne(id);
	}

}
