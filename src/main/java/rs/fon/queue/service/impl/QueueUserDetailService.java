package rs.fon.queue.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rs.fon.queue.blogic.util.Role;
import rs.fon.queue.domain.Admin;
import rs.fon.queue.domain.User;
import rs.fon.queue.repository.AdminRepository;
import rs.fon.queue.repository.UserRepository;

@Service(value = "queueUserDetailService")
public class QueueUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AdminRepository adminRepository;


	
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		if (username == null || username.isEmpty())
			throw new UsernameNotFoundException("Username is null or empty.");
		User user = getUserRepository().findByUsername(username);
		if (user != null) {
			List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
			grantedAuthorities.add(new SimpleGrantedAuthority(Role.USER));
			UserDetails userDetails = new org.springframework.security.core.userdetails.User(username,
					user.getPassword(), grantedAuthorities);
			return userDetails;
		}
		Admin admin = getAdminRepository().findByUsername(username);
		if (admin != null) {
			List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
			grantedAuthorities.add(new SimpleGrantedAuthority(Role.ADMIN));
			UserDetails adminDetails = new org.springframework.security.core.userdetails.User(username,
					admin.getPassword(), grantedAuthorities);
			return adminDetails;
		}
		throw new UsernameNotFoundException("User: " + username + " does not exist.");
	}

	
	
	
	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public AdminRepository getAdminRepository() {
		return adminRepository;
	}

	public void setAdminRepository(AdminRepository adminRepository) {
		this.adminRepository = adminRepository;
	}

}
