package um.tiendas.wallapop2.seguridad.filtros;

import static um.tiendas.wallapop2.seguridad.util.Constantes.HEADER_AUTHORIZATION_KEY;
import static um.tiendas.wallapop2.seguridad.util.Constantes.TOKEN_BEARER_PREFIX;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import um.tiendas.wallapop2.seguridad.model.LoginUsuario;
import um.tiendas.wallapop2.seguridad.util.JwtAuthenticationFailureHandler;
import um.tiendas.wallapop2.seguridad.util.TokenUtil;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private AuthenticationManager authenticationManager;
	
	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
		super.setAuthenticationFailureHandler(new JwtAuthenticationFailureHandler());
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			LoginUsuario userCredentials = new ObjectMapper().readValue(request.getInputStream(), LoginUsuario.class);

			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					userCredentials.getEmail(), userCredentials.getPassword()));
		} catch (IOException e) {
			return null;
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		String token = TokenUtil.generaToken(authResult);
		response.addHeader(HEADER_AUTHORIZATION_KEY, TOKEN_BEARER_PREFIX + " " + token);
	}

}
