package telran.java51.accounting.service;

import telran.java51.accounting.dto.NewUserDto;
import telran.java51.accounting.dto.UpdateUserDto;
import telran.java51.accounting.dto.UserDto;
import telran.java51.accounting.dto.UserRoleDto;

public interface UserService {
	UserDto addUser(NewUserDto newUserDto);
	
	UserDto loginUser(String lodin);
	
	UserDto removeUser(String user);
	
	UserDto updateUser(String user, UpdateUserDto updateUserDto);
	
	UserRoleDto addRole(String user, String role);
	
	UserRoleDto deleteRole(String user, String role);
	
	void changePassword(String password);
	
	UserDto getUser(String user);

}
