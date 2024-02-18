package telran.java51.accounting.service;

import java.security.Principal;

import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import lombok.RequiredArgsConstructor;
import telran.java51.accounting.dao.UserRepository;
import telran.java51.accounting.dto.NewUserDto;
import telran.java51.accounting.dto.UpdateUserDto;
import telran.java51.accounting.dto.UserDto;
import telran.java51.accounting.dto.UserRoleDto;
import telran.java51.accounting.dto.exeption.UserAlreadyExistExeption;
import telran.java51.accounting.dto.exeption.UserNotFoundExeption;
import telran.java51.accounting.model.User;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	final UserRepository userRepository;
	final ModelMapper modelMapper;

	@Override
	public UserDto addUser(NewUserDto newUserDto) {
		User user = modelMapper.map(newUserDto, User.class);
		if (userRepository.existsById(user.getLogin())) {
			throw new UserAlreadyExistExeption();
		} else {
			String password = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
			user.setPassword(password);
			user = userRepository.save(user);
		}
		
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto removeUser(String user) {
		User user1 = userRepository.findById(user).orElseThrow(() -> new UserNotFoundExeption());
		userRepository.delete(user1);
		return modelMapper.map(user1, UserDto.class);
	}

	@Override
	public UserDto updateUser(String user, UpdateUserDto updateUserDto) {
		User user1 = userRepository.findById(user).orElseThrow(() -> new UserNotFoundExeption());
		String firstName = updateUserDto.getFirstName();
		if (firstName != null) {
			user1.setFirstName(firstName);
		}
		String lastName = updateUserDto.getLastName();
		if (lastName != null) {
			user1.setLastName(lastName);
		}
		userRepository.save(user1);
		return modelMapper.map(user1, UserDto.class);
	}

	@Override
	public UserRoleDto addRole(String login, String role) {
		User user1 = userRepository.findById(login).orElseThrow(() -> new UserNotFoundExeption());
		user1.addRole(role);
		userRepository.save(user1);
		return modelMapper.map(user1, UserRoleDto.class);
	}

	@Override
	public UserRoleDto deleteRole(String login, String role) {
		User user1 = userRepository.findById(login).orElseThrow(() -> new UserNotFoundExeption());
		userRepository.delete(user1);
		return modelMapper.map(user1, UserRoleDto.class);
	}

	@Override
	public void changePassword(String login, String newPassword) {
		User user = userRepository.findById(login).orElseThrow(() -> new UserNotFoundExeption());
		user.setPassword(newPassword);
		userRepository.save(user);
	}

	@Override
	public UserDto getUser(String login) {
		User user1 = userRepository.findById(login).orElseThrow(() -> new UserNotFoundExeption());
		return modelMapper.map(user1, UserDto.class);
	}

}
