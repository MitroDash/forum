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

@Component
@Order(20)
public class AuthorEqualsLoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		String[] arr = request.getServletPath().split("/");	
		String author = arr[arr.length - 1];
		Principal principal  = request.getUserPrincipal();
		if (!checkEndPoint(request.getMethod(), request.getServletPath())) {
			if (!principal.getName().equals(author)) {
				response.sendError(403, "Permision denied");
				return;
			}
		}
		chain.doFilter(request, response);

	}

	private boolean checkEndPoint(String method, String path) {
		return (HttpMethod.POST.matches(method) && path.matches("/post/\\w+")) || path.matches("/post/\\w+/comment/\\w+");
	}

}
