package com.picosoft.picosoft.controller;

import java.sql.Time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.picosoft.picosoft.dao.HoraireRepository;
import com.picosoft.picosoft.dao.PointageRepository;
import com.picosoft.picosoft.dao.UserRepository;
import com.picosoft.picosoft.module.CountResponse;
import com.picosoft.picosoft.module.Horaire;
import com.picosoft.picosoft.module.Pointage;
import com.picosoft.picosoft.module.PointageResponse;
import com.picosoft.picosoft.module.User;
/**
 * 
 * @author X270
 *
 */
@Transactional
@RestController
@RequestMapping(value="api/user")
public class PointageController {
	@Autowired
	PointageRepository pointage;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	HoraireRepository horRepository;
	/**
	 * 
	 * @param email
	 * @return pointage.findAllByEmail(email)
	 */
	@PreAuthorize("hasAuthority('responsable_rh')")
	@GetMapping(value="/allPointage/{email}")
	public List<Pointage> getAllPointage(@PathVariable String email){
		return pointage.findAllByEmail(email);
	}
	/**
	 * 
	 * @return  pointage.findAll()
	 */
	@PreAuthorize("hasAnyAuthority('responsable_rh','admin', 'manager', 'employe')")
	@GetMapping(value="/allpointage")
	public List<Pointage> getAll(){
		return pointage.findAll();
	}
	/**
	 * 
	 * @param p
	 * @return new ResponseEntity<>(HttpStatus.NO_CONTENT)
	 * @return  new ResponseEntity<>(pointage.save(p),HttpStatus.CREATED)
	 */
	@PreAuthorize("hasAuthority('responsable_rh')")
	@PostMapping(value="/ajouterPointage")
	public ResponseEntity<?> AjouterPointage(@Valid @RequestBody Pointage p) {
		if(p.getCheckDate()==null||p.getCheckTime()==null||p.getCheckType()==null||p.getUser().getIdUser()==null || p.getVerifyCode()==0)
		{
			return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		User u=userRepo.getOne(p.getUser().getIdUser());
		p.setUser(u);
		return  new ResponseEntity<>(pointage.save(p),HttpStatus.CREATED);
	}
	
	/*@PreAuthorize("hasAuthority('responsable_rh')")
	@GetMapping(value="/countEmp")
	public int getNbEmp() {
		return pointage.countEmployee();
	}
	
	@PreAuthorize("hasAuthority('responsable_rh')")
	@GetMapping(value="/countMember")
	public int getNbMember() {
		return pointage.countAll();
	}
	
	@PreAuthorize("hasAuthority('responsable_rh')")
	@GetMapping(value="/countManager")
	public int getNbManager() {
		return pointage.countManager();
	}
	
	@PreAuthorize("hasAuthority('responsable_rh')")
	@GetMapping(value="/countRh")
	public int getNbRh() {
		return pointage.countRh();
	}*/
	/**
	 * 
	 * @param email
	 * @param datedeb
	 * @param datefin
	 * @return  pointage.findPointageByUser
	 */
	@PreAuthorize("hasAnyAuthority('responsable_rh', 'manager')")
	@GetMapping(value="/{email}/{datedeb}/{datefin}")
	public List<Pointage> getPointageByWeek(@PathVariable String email , @PathVariable String datedeb, @PathVariable String datefin){
		return pointage.findPointageByUser(email, datedeb, datefin);
	}
	/**
	 * 
	 * @param email
	 * @param dateDebut
	 * @param dateFin
	 * @return mp
	 */
	@PreAuthorize("hasAnyAuthority('responsable_rh', 'manager','employe')")
	@GetMapping(value="/nbheures/{email}/{dateDebut}/{dateFin}")
	public List<PointageResponse> getNbHeureByWeek(@PathVariable("email") String email , @PathVariable("dateDebut")  String dateDebut, @PathVariable("dateFin")  String dateFin){
		List<PointageResponse> mp = new ArrayList<>();
		List<Pointage> pointages = pointage.findPointageByUser(email, dateDebut, dateFin);
		String date =  pointages.get(pointages.size()-2).getCheckDate();
		Double sum =(double)0;
		  for(int i=pointages.size()-2;i>=0;i-=2) {
			  if(!date.equals(pointages.get(i).getCheckDate())) {
				 mp.add(new PointageResponse(date, sum/3600000));
				  sum =  (double)pointages.get(i).getCheckTime().getTime()-pointages.get(i+1).getCheckTime().getTime();
				  date = pointages.get(i).getCheckDate();continue;
			  }
			  if(date.equals(pointages.get(i).getCheckDate())) {
				  sum += (double)pointages.get(i).getCheckTime().getTime()-pointages.get(i+1).getCheckTime().getTime();
			  }
			  if(i<= 0) {
				  mp.add(new PointageResponse(date, sum/3600000));
			  }
		  }
		  return mp;
	}
	/**
	 * 
	 * @param email
	 * @param dateDebut
	 * @param dateFin
	 * @param horaire
	 * @return percentResp
	 */
	@PreAuthorize("hasAnyAuthority('manager', 'responsable_rh', 'employe')")
	@GetMapping(value="/percent/{email}/{dateDebut}/{dateFin}/{horaire}")
	public List<PointageResponse> getPercentByWeek(@PathVariable("email") String email , @PathVariable("dateDebut")  String dateDebut, @PathVariable("dateFin")  String dateFin , @PathVariable String horaire){
		List<PointageResponse> response= new ArrayList<>();
		List<PointageResponse> percentResp=new ArrayList<>();
		List <Pointage> pointages=pointage.findPointageByUser(email, dateDebut, dateFin);
		String date =  pointages.get(pointages.size()-2).getCheckDate();
		Double percent =(double)0;
		Double sum =(double)0;
		  for(int i=pointages.size()-2;i>=0;i-=2) {
			  if(!date.equals(pointages.get(i).getCheckDate())) {
				 response.add(new PointageResponse(date, sum/3600000));
				  sum =  (double)pointages.get(i).getCheckTime().getTime()-pointages.get(i+1).getCheckTime().getTime();
				  date = pointages.get(i).getCheckDate();continue;
			  }
			  if(date.equals(pointages.get(i).getCheckDate())) {
				  sum += (double)pointages.get(i).getCheckTime().getTime()-pointages.get(i+1).getCheckTime().getTime();
			  }
			  if(i<= 0) {
				  response.add(new PointageResponse(date, sum/3600000));
			  }
		  }  
		  if(horaire.toUpperCase().equals("NORMAL")) { 
			  for (int j=0; j<response.size(); j++) {
				  percentResp.add(new PointageResponse(response.get(j).getName(), response.get(j).getValue()*100/8.5));
			  }
		  }else  if(horaire.toUpperCase().equals("SEANCE UNIQUE")) {
			  for (int j=0; j<response.size(); j++) {
				  percentResp.add(new PointageResponse(response.get(j).getName(), response.get(j).getValue()*100/6.5));
			  }
		  }else { 
			  for (int j=0; j<response.size(); j++) { 
				  percentResp.add(new PointageResponse(response.get(j).getName(), response.get(j).getValue()*100/7));
			  }
		  }
		  return percentResp;
	}
	/**
	 * 
	 * @return mp
	 */
	@PreAuthorize("hasAuthority('responsable_rh')")
	@GetMapping(value="/count")
	public List<CountResponse> getNbTotal(){
        List<CountResponse> mp= new ArrayList<>();
        int nbMember = pointage.countAll();
        int nbEmp = pointage.countEmployee();
        int nbManager = pointage.countManager();
        int nbRh = pointage.countRh();
        mp.add(new CountResponse("member",nbMember));
        mp.add(new CountResponse("employe",nbEmp));
        mp.add(new CountResponse("manager",nbManager));
        mp.add(new CountResponse("responsable rh", nbRh));
        return mp;
    }
}
