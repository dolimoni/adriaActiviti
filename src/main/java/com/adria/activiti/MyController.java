package com.adria.activiti;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.adria.activiti.DTO.DemandesDTO;
import com.adria.activiti.DTO.TaskDTO;
import com.adria.activiti.entities.Demande;
import com.adria.activiti.entities.Person;
import com.adria.activiti.service.MyService;
import com.adria.activiti.util.GenericResponse;
import com.google.gson.Gson;

@Controller
@RequestMapping(value="/")
public class MyController {

    @Autowired
    private MyService myService;

    @RequestMapping(value="/index", method= RequestMethod.GET)
    public String startProcessInstance(HttpServletRequest request) {
    	return "index";
    }

    @RequestMapping(value="/process", method= RequestMethod.POST)
    public void startProcessInstance(@RequestBody StartProcessRepresentation startProcessRepresentation) {
        myService.startProcess(startProcessRepresentation.getAssignee());
    }

//    @RequestMapping(value="/taskss", method= RequestMethod.GET)
//    public List<TaskRepresentation> getTasks(@RequestParam String assignee) {
//        List<Task> tasks = myService.getTasks(assignee);
//        List<TaskRepresentation> dtos = new ArrayList<TaskRepresentation>();
//        for (Task task : tasks) {
//            dtos.add(new TaskRepresentation(task.getId(), task.getName()));
//        }
//        return dtos;
//    }
    
//    					Demandes de congé
    
    @RequestMapping(value="/create", method= RequestMethod.GET)
    public String create(HttpServletRequest request) {
    	return "Request";
    }
    
    @RequestMapping(value="/add", method= RequestMethod.POST)
    public @ResponseBody String add(@ModelAttribute("demandeDTO") final DemandesDTO demandeDTO) {
    	
    	myService.createDemande(demandeDTO);
    	
    	//myService.startProcess(demandeDTO.getUserId());
    	
    	
    	GenericResponse response=new GenericResponse();
    	response.setMsg("index");
    	response.setSuccess(true);
    	Gson gson=new Gson();
    	
    	return gson.toJson(response);
    }
    
    @RequestMapping(value="/getPersonDemandes",method=RequestMethod.GET)
    public @ResponseBody List<DemandesDTO> getPersonDemandes(HttpServletRequest request){
    	List<DemandesDTO> demandesDTOs=new ArrayList<DemandesDTO>();
    	
    	Person person=myService.findPersonById(new Long(2));// STATIC
    	
    	List<Demande> demandes=myService.getPersonDemandes(person);
    	
    	for (Demande demande : demandes) {
			DemandesDTO demandesDTO=new DemandesDTO();
			demandesDTO.setCreationDate(demande.getCreationDate().toString());
			demandesDTO.setDure(demande.getDure().toString());
			demandesDTO.setStatus(demande.getStatus().toString());
			demandesDTO.setUserId(demande.getId().toString());
			demandesDTOs.add(demandesDTO);
		}
    	return demandesDTOs;
    }
    
//    		Les tâches des supérieurs 
    
    @RequestMapping(value="/tasks", method= RequestMethod.GET)
    public String getTasks(HttpServletRequest request) {
    	return "tasks";
    }
    
    @RequestMapping(value="/getMyTasks",method=RequestMethod.GET)
    public @ResponseBody List<TaskDTO> getMyTasks(HttpServletRequest request){
//    	List<TaskDTO> taskDTOs=new ArrayList<TaskDTO>();
//    	List<TaskDTO> tasks=myService.getTasks("2");
//    	for (Task task : tasks) {
//			TaskDTO dto=new TaskDTO();
//			System.out.println(task.getName());
//		}
    	return myService.getTasks("2");
    }

    
    static class TaskRepresentation {

        private String id;
        private String name;

        public TaskRepresentation(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    static class StartProcessRepresentation {

        private String assignee;

        public String getAssignee() {
            return assignee;
        }

        public void setAssignee(String assignee) {
            this.assignee = assignee;
        }
    }



}