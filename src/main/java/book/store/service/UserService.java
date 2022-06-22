package book.store.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import book.store.model.Users;
import book.store.repos.UserRepo;

@Service
public class UserService {
	@Autowired
private UserRepo userRepo;

public List<Users> showAllUsers(String keyword){
	if(keyword !=null) {
		return userRepo.findAll(keyword);
	}
	return userRepo.findAll();
}
public void addUser(Users user) {
	userRepo.save(user);
}
public void removeUser(Long id) {
	userRepo.deleteById(id);
}
public void updateUser(Long id,Users user) {
	userRepo.findById(id);
	userRepo.deleteById(id);
	 BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    String encodedPassword = passwordEncoder.encode(user.getPassword());
	    user.setPassword(encodedPassword);
	userRepo.save(user);
}
public Optional<Users> getUsersId(Long id) {
    return userRepo.findById(id);
}

public Users get(Long id) {
	return userRepo.findById(id).get();
}


}
