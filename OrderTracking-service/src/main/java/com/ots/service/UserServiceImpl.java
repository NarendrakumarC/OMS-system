package com.ots.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ots.domain.User;
import com.ots.dto.UserDTO;
import com.ots.repo.UserRepository;

@Service
public class UserServiceImpl {

	@Autowired
	private UserRepository repo;
	
	
	public UserDTO createUser(UserDTO userDTO) {
		User user=new User();
		BeanUtils.copyProperties(userDTO, user);
		repo.save(user);
		return userDTO;
	}
	
	public UserDTO getUser(String userName) {
		Optional<User>  user= repo.findByUserName(userName);
		UserDTO dto=new UserDTO();
		if(!user.isEmpty())
			BeanUtils.copyProperties(user.get(), dto);
		return dto;
	}
	
	public List<UserDTO> getAllUserData(){
		List<User> user = repo.findAll();
		List<UserDTO> userDTOLst = 
				 user.stream().map(u -> new UserDTO(u.getUserName(),u.getPasswordHash(),u.getEmail()))
				.collect(Collectors.toList());
		return userDTOLst;
	}
	
	public UserDTO findUserbyIgnoreCase(String name) {
		User user = repo.findByUserNameEqualsIgnoreCase(name).get();
		return new UserDTO(user);
	}
	
}
