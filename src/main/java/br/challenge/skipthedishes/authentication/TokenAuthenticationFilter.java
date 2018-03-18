package br.challenge.skipthedishes.authentication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.challenge.skipthedishes.model.User;
import br.challenge.skipthedishes.repository.UserRepository;

@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {

	private static final String ROLE_AUTHENTICATED = "ROLE_AUTHENTICATED";

	@Autowired
	private UserRepository userRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		Authentication auth = null;
		SecurityContextHolder.getContext().setAuthentication(null);

		String authorization = request.getHeader("Authorization");
		if (StringUtils.isNotBlank(authorization)) {
			auth = validateToken(authorization);
		}

		SecurityContextHolder.getContext().setAuthentication(auth);
		filterChain.doFilter(request, response);
	}

	private SimpleAuthenticationToken validateToken(String token) {
		if (token.startsWith("Basic ")) {
			String basicToken = token.substring(6);
			byte[] decodeBase64 = org.apache.tomcat.util.codec.binary.Base64.decodeBase64(basicToken);
			String decoded = new String(decodeBase64);
			String login = decoded.substring(0, decoded.indexOf(":"));
			String password = decoded.substring(decoded.indexOf(":") + 1);
			User userAuthenticated = userRepository.findByLoginAndPassword(login, password);
			if (userAuthenticated != null) {
				SimpleAuthenticationToken authenticationToken = new SimpleAuthenticationToken(userAuthenticated);
				authenticationToken.setAuthToken(token);
				authenticationToken.setUser(userAuthenticated);
				Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
				authorities.add(new SimpleGrantedAuthority(ROLE_AUTHENTICATED));
				authenticationToken.setAuthorities(authorities);
				return authenticationToken;
			}
		}
		return null;
	}

}
