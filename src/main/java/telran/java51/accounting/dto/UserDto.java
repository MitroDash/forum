package telran.java51.accounting.dto;

import java.util.Set;

import lombok.Getter;
import lombok.Singular;

@Getter
public class UserDto {
	String login;
    String firstName;
    String lastName;
    @Singular
    Set<String> roles;

}
