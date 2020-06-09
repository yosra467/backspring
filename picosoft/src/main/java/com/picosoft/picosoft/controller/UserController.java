	package com.picosoft.picosoft.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.picosoft.picosoft.dao.DepartementRepository;
import com.picosoft.picosoft.dao.UserRepository;
import com.picosoft.picosoft.module.Departement;
import com.picosoft.picosoft.module.User;
/**
 * 
 * @author X270
 *
 */
@Transactional
@RestController
@RequestMapping(value="api/user")
public class UserController {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	DepartementRepository deptRepository;
	
	@Autowired
	private BCryptPasswordEncoder bcryptpasswordEncoder;
	/**
	 * 
	 * @param Id
	 * @return userRepository.findById(Id).get()
	 */
	@PreAuthorize("hasAuthority('responsable_rh')")
	@GetMapping(value="/id/{id}")
	public User getUserById(@PathVariable(value="id") Long Id){
		return userRepository.findById(Id).get(); 
	}
	/**
	 * le responsable_rh peut rechercher un utilisateur
	 * @param prenom
	 * @return ResponseEntity<>(user, HttpStatus.NO_CONTENT)
	 * @return ResponseEntity<>(HttpStatus.OK)
	 * @return ResponseEntity<>(HttpStatus.OK)
	 * @throws Exception  
	 */
	@PreAuthorize("hasAuthority('responsable_rh')")
	@GetMapping(value="/searchUser/{prenom}")
	public ResponseEntity<List<User>> SearchUser(@RequestParam(required = false)String prenom){
		try {
			List<User> user= userRepository.findByPrenom(prenom);
			if(user.isEmpty()) {
				return new ResponseEntity<>(user, HttpStatus.NO_CONTENT);
			}
		    return new ResponseEntity<>(HttpStatus.OK);
		}
		catch (Exception e){
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}
	/**
	 * 
	 * @param email
	 * @return userRepository.findByEmail(email)
	 */
	@PreAuthorize("hasAnyAuthority('responsable_rh','manager', 'admin', 'employe')")
	@GetMapping(value="/{email}")
	public User getUserByEmail(@PathVariable String email) {
		return userRepository.findByEmail(email);
	}
	/**
	 * 
	 * @return userRepository.findAll()
	 */
	@PreAuthorize("hasAnyAuthority('admin','responsable_rh','employe','manager')")
	@GetMapping(value="/all")
	public List<User> getAllUser(){
			return userRepository.findAll();
		}
	/**
	 * 
	 * @return deptRepository.findAll()
	 */
	@PreAuthorize("hasAnyAuthority('admin','responsable_rh','employe','manager')")
	@GetMapping(value="/allDepartement")
	public List<Departement> getAllDepartement(){
			return deptRepository.findAll();
		}
	/**
	 * 
	 * @return userRepository.findAllManager()
	 */
	@PreAuthorize("hasAnyAuthority('responsable_rh','employe','manager')")
	@GetMapping(value="/allManager")
	public List<User> getAllManager(){
		return userRepository.findAllManager();
		
	}
	/**
	 * 
	 * @param id
	 * @return userRepository.findEquipe(id)
	 */
	@PreAuthorize("hasAnyAuthority('responsable_rh','manager')")
	@GetMapping(value="/equipe/{id}")
	public List<User> getAllEquipe(@PathVariable Long id){
		return userRepository.findEquipe(id);
		
	}
	/**
	 * 
	 * @param user
	 * @return userRepository.save(user)
	 */
	@PreAuthorize("hasAuthority('responsable_rh')")
	@PostMapping(value="/ajouterUser")
	public User AjouterUser(@Valid @RequestBody User user) {
		user.setPassword(bcryptpasswordEncoder.encode("12345678"));
		return userRepository.save(user);
	}
	/**
	 * 
	 * @param id
	 * @param userUpdated
	 * @return ResponseEntity.ok(userModified)
	 * @throws Exception
	 */
	@PreAuthorize("hasAuthority('responsable_rh')")
	@PutMapping(value="/updateUser/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User userUpdated) throws Exception {
		User user= userRepository.findById(id).orElseThrow(()->new Exception("user not found"));
		user.setNom(userUpdated.getNom());
		user.setPrenom(userUpdated.getPrenom());
		user.setEmail(userUpdated.getEmail());
		user.setGender(userUpdated.getGender());
		user.setPassword(userUpdated.getPassword());
		user.setDepartement(userUpdated.getDepartement());
		user.setPolitique(userUpdated.getPolitique());
		user.setRole(userUpdated.getRole());
		user.setSuperior(userUpdated.getSuperior());
		User userModified=userRepository.save(user);
		return ResponseEntity.ok(userModified);
	}
	/**
	 * 
	 * @param id
	 * @param data
	 * @return user
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyAuthority('admin','responsable_rh','employe','manager')")
	@PutMapping(value="/updatePassword/{id}")
	public User updatePassword(@PathVariable Long id,@Valid  @RequestBody HashMap<String, String> data  ) throws Exception  {
		//user.setPassword(bcryptpasswordEncoder.encode(user.getPassword())) ;
	
	System.out.println(data.get("oldpassword"));

	 System.out.println(data.get("newPassword"));

	 User user= userRepository.findById(id).orElseThrow(()->new Exception("user not found"));
		if(bcryptpasswordEncoder.matches(data.get("oldpassword"), user.getPassword())) {
    //System.out.println("Password matching") ; 
    user.setPassword(bcryptpasswordEncoder.encode(data.get("newPassword")));
    // System.out.println("Password updated") ;
     userRepository.save(user);
     System.out.println("User saved") ;
		}	 
     System.out.println("Error: Password not matching") ;
		return (user);
		
 }
	/**
	 * 
	 * @param id 
	 * @return response
	 * @throws Exception
	 */
	@PreAuthorize("hasAuthority('responsable_rh')")
	@DeleteMapping(value="/deleteUser/{id}")
	public Map<String,Boolean> deleteUser(@PathVariable Long id) throws Exception{
		User user=userRepository.findById(id).orElseThrow(()->new Exception("user not found"));
		userRepository.delete(user);
		Map<String,Boolean> response= new HashMap<>();
		response.put("Deleted successfully", Boolean.TRUE);
		return response;
	}
}
