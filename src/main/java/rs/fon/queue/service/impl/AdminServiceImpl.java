package rs.fon.queue.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

import rs.fon.queue.blogic.exception.AdminAlreadyExistException;
import rs.fon.queue.blogic.exception.EntityDoesNotExistException;
import rs.fon.queue.blogic.exception.UserAlreadyExistException;
import rs.fon.queue.blogic.util.Constants;
import rs.fon.queue.domain.Admin;
import rs.fon.queue.domain.Stand;
import rs.fon.queue.domain.User;
import rs.fon.queue.model.AdminModel;
import rs.fon.queue.model.CRUDModel;
import rs.fon.queue.model.UserModel;
import rs.fon.queue.repository.AdminRepository;
import rs.fon.queue.repository.UserRepository;
import rs.fon.queue.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ShaPasswordEncoder passwordEncoder;
	
	@Override
	public List<UserModel> getUsers() {
		List<User> listUser = getAdminRepository().getUsers();
		List<UserModel> listUserModel = new ArrayList<UserModel>();
		for (User user : listUser) {
			UserModel um = new UserModel();
			um.setUsername(user.getUsername());
			um.setStandNumber(user.getStand().getStand_number());
			listUserModel.add(um);
		}
		return listUserModel;
	}

	@Override
	public List<AdminModel> getAdmins() {
		List<Admin> listAdmin = getAdminRepository().getAdmins();
		List<AdminModel> listAdminModel = new ArrayList<AdminModel>();
		for (Admin admin : listAdmin) {
			AdminModel am = new AdminModel();
			am.setUsername(admin.getUsername());
			am.setFirstName(admin.getFirstName());
			am.setLastName(admin.getLastName());
			am.setEmail(admin.getEmail());
			am.setTelephone(admin.getTelephone());
			listAdminModel.add(am);
		}
		return listAdminModel;
	}

	@Override
	public AdminModel findByUsername(String username) {
		Admin admin = getAdminRepository().findByUsername(username);
		if (admin == null)
			return null;
		AdminModel am = new AdminModel();
		am.setUsername(username);
		am.setFirstName(admin.getFirstName());
		am.setLastName(admin.getLastName());
		return am;
	}
	
	@Override
	public boolean deleteUser(User user) {
		return getAdminRepository().delete(user);
		
	}
	
	@Override
	public void insertUser(CRUDModel crudUser) {
		String username = crudUser.getUsername();
		User user = getUserRepository().findByUsername(username);
		if (user != null)
			throw new UserAlreadyExistException();
		String password = getPasswordEncoder().encodePassword(crudUser.getPassword(), null);
		int standNumber = Integer.parseInt(crudUser.getStandNumber());
		Stand stand = new Stand((long) standNumber,standNumber);
		
		user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setStand(stand);
		getAdminRepository().insertUser(user);
	
	}
	
	@Override
	public void insertAdmin(CRUDModel crudAdmin) {
		String username = crudAdmin.getUsername();
		Admin admin = getAdminRepository().findByUsername(username);
		if (admin != null)
			throw new AdminAlreadyExistException();
		admin = new Admin();
		admin.setUsername(crudAdmin.getUsername());
		admin.setPassword(passwordEncoder.encodePassword(crudAdmin.getPassword(), null));
		admin.setFirstName(crudAdmin.getFirstName());
		admin.setLastName(crudAdmin.getLastName());
		admin.setTelephone(crudAdmin.getTelephone());
		admin.setEmail(crudAdmin.getEmail());
		getAdminRepository().insertAdmin(admin);
	}
	 
	@Override
	public boolean updateAdmin(CRUDModel crudAdmin) {
		String username = crudAdmin.getUsername();
		String password = passwordEncoder.encodePassword(crudAdmin.getPassword(), null);
		Admin admin = getAdminRepository().findByUsernameAndPassword(username, password);
		if (admin == null)
			throw new EntityDoesNotExistException();
		if (crudAdmin.getFirstName() != null && !crudAdmin.getFirstName().isEmpty())
			admin.setFirstName(crudAdmin.getFirstName());
		if (crudAdmin.getLastName() != null && !crudAdmin.getLastName().isEmpty())
			admin.setLastName(crudAdmin.getLastName());
		if (crudAdmin.getEmail() != null && !crudAdmin.getEmail().isEmpty())
			admin.setEmail(crudAdmin.getFirstName());
		if (crudAdmin.getTelephone() != null && !crudAdmin.getTelephone().isEmpty())
			admin.setTelephone(crudAdmin.getTelephone());
		return getAdminRepository().update(admin);
	}
	
	
	@Override
	public boolean updateUser(CRUDModel crudUser) {
		String username = crudUser.getUsername();
		String password = passwordEncoder.encodePassword(crudUser.getPassword(), null);
		User user = getUserRepository().findByUsernameandPassword(username, password);
		if (user == null)
			throw new EntityDoesNotExistException();
		String standString = crudUser.getStandNumber();
		if (standString == null || standString.isEmpty())
			return false;
		int standNumber;
		try {
			standNumber = Integer.parseInt(standString);
		} catch (NumberFormatException e) {
			return false;
		}
		if (standNumber <= 0 || standNumber > Constants.NUMBER_OF_STANDS)
			return false;
		Stand stand = new Stand((long) standNumber,standNumber);
		user.setStand(stand);
		return getAdminRepository().update(user);
	}
	
	
	public void update(CRUDModel crudModel) {
		try {
			boolean result = updateUser(crudModel);
			if (result == false) {
				throw new IllegalArgumentException();
			}
			return;
		} catch (EntityDoesNotExistException e) {
			boolean result = updateAdmin(crudModel);
			if (result == false)
				throw new IllegalArgumentException();
		}
		
		
	}
	
	@Override
	public boolean deleteAdmin(CRUDModel crudAdmin) {
		String username = crudAdmin.getUsername();
		String password = passwordEncoder.encodePassword(crudAdmin.getPassword(), null);
		Admin admin = getAdminRepository().findByUsernameAndPassword(username, password);
		if (admin == null)
			throw new EntityDoesNotExistException();
		return getAdminRepository().delete(admin);
		
	}
	
	@Override
	public boolean deleteUser(CRUDModel crudUser) {
		String username = crudUser.getUsername();
		String password = passwordEncoder.encodePassword(crudUser.getPassword(), null);
		User user = getUserRepository().findByUsernameandPassword(username, password);
		if (user == null)
			throw new EntityDoesNotExistException();
		return getAdminRepository().delete(user);
		
	}
	
	@Override
	public void delete(CRUDModel crudModel) {
		try {
			boolean result = deleteUser(crudModel);
			if (result == false) {
				throw new IllegalArgumentException();
			}
			return;
		} catch (EntityDoesNotExistException e) {
			boolean result = deleteAdmin(crudModel);
			if (result == false)
				throw new IllegalArgumentException();
		}
		
	}
	
	
	public AdminRepository getAdminRepository() {
		return adminRepository;
	}
	public void setAdminRepository(AdminRepository adminRepository) {
		this.adminRepository = adminRepository;
	}
	public ShaPasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}
	public void setPasswordEncoder(ShaPasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	public UserRepository getUserRepository() {
		return userRepository;
	}
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

}
