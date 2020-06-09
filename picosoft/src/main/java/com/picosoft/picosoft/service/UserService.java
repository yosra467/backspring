package com.picosoft.picosoft.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.picosoft.picosoft.dao.UserRepository;
import com.picosoft.picosoft.module.User;
@Service
/**
 * 
 * @author X270
 *@see UserDetailsService
 */
public class UserService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository; 
	
	/*@Autowired
	private BCryptPasswordEncoder bcryptpasswordEncoder;*/
	/**
	 * @throws UsernameNotFoundException
	 * @param username
	 * @return  org.springframework.security.core.userdetails.User
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		User user=this.userRepository.findByEmail(username);
		if(user == null) throw new UsernameNotFoundException(username);
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority(user.getRole().getRole()));
			return new  org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
						
		
	}
	

	/* loaduserbyusername nda5Lelha email t5arajli userdetails */
	/* collection hethika fiha les authorities bech najmou nest3mlouha lezemna n7awlou 
	  el arraylist w b3d fi west list hethika nzidou el user mt3na nda5louh 
	  w fel return bech yraj3Lk el user mel userdetails avec les details mt3ou mail password */
	
	/*public User addUser(User user)
	{
		user.setPassword(bcryptpasswordEncoder.encode("12345678"));
		return userRepository.save(user);
	}*/
}