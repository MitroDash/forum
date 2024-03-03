package telran.java51.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java51.accounting.dao.UserRepository;
import telran.java51.accounting.model.UserAccount;
import org.springframework.security.core.userdetails.User;

@Service
@RequiredArgsConstructor
public class UserDetailsServiseImpl implements UserDetailsService {
	
	final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserAccount user = userRepository.findById(username)
				.orElseThrow(() -> new UsernameNotFoundException(username));
		Collection<String> authorities = user.getRoles().stream()
				.map(r -> "ROLE_" + r.name())
				.collect(Collectors.toList());
		return new User(username, user.getPassword(), AuthorityUtils.createAuthorityList(authorities));
	}

}
