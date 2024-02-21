package telran.java51.accounting.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserDto {
	String login;
    String firstName;
    String lastName;
    @Singular
    Set<String> roles;

}
