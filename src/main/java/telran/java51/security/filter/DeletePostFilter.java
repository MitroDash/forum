package telran.java51.security.filter;

import java.io.IOException;
import java.security.Principal;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import telran.java51.accounting.dao.UserRepository;
import telran.java51.accounting.model.User;
import telran.java51.accounting.model.UserRole;
import telran.java51.post.dao.PostRepository;
import telran.java51.post.model.Post;

@Component
@Order(20)
@RequiredArgsConstructor
public class DeletePostFilter implements Filter {
	final UserRepository userRepository;
	final PostRepository postRepository;

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		if (checkEndPoint(request.getMethod(), request.getServletPath())) {
			Principal principal  = request.getUserPrincipal();
			User user = userRepository.findById(request.getUserPrincipal().getName()).get();
			String[] arr = request.getServletPath().split("/");	
			String id = arr[arr.length - 1];
			Post post = postRepository.findById(id).get();
			if (!(principal.getName().equals(post.getAuthor()) || user.getRoles().contains(UserRole.MODERATOR))) {
				response.sendError(403, "Permision denied");
				return;
			}
		}
		chain.doFilter(request, response);

	}

	private boolean checkEndPoint(String method, String path) {
		return HttpMethod.DELETE.matches(method) && path.matches("/post/\\w+");
	}

}
