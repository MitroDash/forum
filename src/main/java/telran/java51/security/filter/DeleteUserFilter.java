package telran.java51.security.filter;

import java.io.IOException;

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
import telran.java51.security.model.UserPrincipal;

@Component
@Order(40)
public class DeleteUserFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		if (checkEndPoint(request.getMethod(), request.getServletPath())) {
			UserPrincipal user = (UserPrincipal) request.getUserPrincipal();
			String[] arr = request.getServletPath().split("/");	
			String login = arr[arr.length - 1];
			if (!(user.getName().equals(login) || user.getRoles().contains("ADMINISTRATOR"))) {
				response.sendError(403, "Permision denied");
				return;
			}
		}
		chain.doFilter(request, response);
	}

	private boolean checkEndPoint(String method, String path) {
		return HttpMethod.DELETE.matches(method) && path.matches("/account/user/\\w+");
	}
}
