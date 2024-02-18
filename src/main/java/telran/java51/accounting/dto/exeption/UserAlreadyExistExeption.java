package telran.java51.accounting.dto.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyExistExeption extends RuntimeException {

	private static final long serialVersionUID = -1429868749242688988L;

}
