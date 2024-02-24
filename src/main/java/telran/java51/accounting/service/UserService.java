package telran.java51.accounting.service;

import telran.java51.accounting.dto.NewUserDto;
import telran.java51.accounting.dto.UpdateUserDto;
import telran.java51.accounting.dto.UserDto;
import telran.java51.accounting.dto.UserRoleDto;

public interface UserService {
	UserDto addUser(NewUserDto newUserDto);
	
	UserDto removeUser(String lodin);
	
	UserDto updateUser(String lodin, UpdateUserDto updateUserDto);
	
	UserRoleDto addRole(String lodin, String role);
	
	UserRoleDto deleteRole(String lodin, String role);
	
	void changePassword(String login, String newPassword);
	
	UserDto getUser(String lodin);

}
