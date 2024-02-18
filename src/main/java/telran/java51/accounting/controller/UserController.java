package telran.java51.accounting.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.java51.accounting.dto.NewUserDto;
import telran.java51.accounting.dto.UpdateUserDto;
import telran.java51.accounting.dto.UserDto;
import telran.java51.accounting.dto.UserRoleDto;
import telran.java51.accounting.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class UserController {
	
	final UserService userService;

	@PostMapping("/register")
	public UserDto addUser(@RequestBody NewUserDto newUserDto) {
		return userService.addUser(newUserDto);
	}
	
	@PostMapping("/login")
	public UserDto login(Principal principal) {
		return userService.getUser(principal.getName());
	}

//	@PostMapping("/login")
//	public UserDto loginUser(@PathVariable String lodin) {
//		return userService.loginUser(lodin);
//	}

	@DeleteMapping("/user/{user}")
	public UserDto removeUser(@PathVariable String user) {
		return userService.removeUser(user);
	}

	@PutMapping("/user/{user}")
	public UserDto updateUser(@PathVariable String user, @RequestBody UpdateUserDto updateUserDto) {
		return userService.updateUser(user, updateUserDto);
	}

	@PutMapping("/user/{user}/role/{role}")
	public UserRoleDto addRole(@PathVariable String user, @PathVariable String role) {
		return userService.addRole(user, role);
	}
	
	@DeleteMapping("/user/{user}/role/{role}")
	public UserRoleDto deleteRole(@PathVariable String user, @PathVariable String role) {
		return userService.deleteRole(user, role);
	}

	@PutMapping("/password")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void changePassword(Principal principal, @RequestHeader("X-Password") String newPassword) {
		userService.changePassword(principal.getName(), newPassword);
		
	}

	@GetMapping("/user/{user}")
	public UserDto getUser(@PathVariable String user) {
		return userService.getUser(user);
	}

}
