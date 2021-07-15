package um.tiendas.wallapop2.seguridad.filtros;

import static um.tiendas.wallapop2.seguridad.util.Constantes.HEADER_AUTHORIZATION_KEY;
import static um.tiendas.wallapop2.seguridad.util.Constantes.TOKEN_BEARER_PREFIX;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import um.tiendas.wallapop2.seguridad.WallapopUserDetails;
import um.tiendas.wallapop2.seguridad.util.Constantes;
import um.tiendas.wallapop2.seguridad.util.TokenUtil;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

	@Autowired
	private WallapopUserDetails wallapopUserDetails;
	
	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			FilterChain filterChain) throws ServletException, IOException {
		String authorizationHeader = httpServletRequest.getHeader(HEADER_AUTHORIZATION_KEY);

		if (authorizationHeader == null || authorizationHeader.isEmpty() || !authorizationHeader.startsWith(TOKEN_BEARER_PREFIX)) {
			filterChain.doFilter(httpServletRequest, httpServletResponse);
			return;
		}
		final String token = authorizationHeader.replace(Constantes.TOKEN_BEARER_PREFIX + " ", ""); //Quitamos el espacio en blanco

		String userName = TokenUtil.getNombreUsuario(token);
		UserDetails user = wallapopUserDetails.loadUserByUsername(userName);

		UsernamePasswordAuthenticationToken authenticationToken = TokenUtil.recuperaAutenticador(token, user);
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}
}
