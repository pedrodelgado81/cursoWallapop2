package um.tiendas.wallapop2.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import um.tiendas.wallapop2.seguridad.filtros.JwtAuthenticationFilter;
import um.tiendas.wallapop2.seguridad.filtros.JwtAuthorizationFilter;

// Autenticacion
@Configuration
@EnableWebSecurity
//Autorizacion
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WallapopWebSecurity extends WebSecurityConfigurerAdapter {
	
	 
	@Autowired
	private WallapopUserDetails wallapopUserDetails;
	
	/**
	 * Bean que controla la encriptacion de las contraseñas en bbdd
	 * @return
	 */
	@Bean
	public BCryptPasswordEncoder getPasswordEnconder() {
		return new BCryptPasswordEncoder();
	}
	
	/**
	 * Defino como Bean el authentication manager bean para que sea accedido desde los filtros ya que no estan en el mismo contexto
	 */
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
	public JwtAuthorizationFilter jwtAuthorizationFilterBean() {
		return new JwtAuthorizationFilter();
	}
	
	/**
	 * Proveedor de identidad de usuario
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.wallapopUserDetails).passwordEncoder(getPasswordEnconder());
	}
	
	/**
	 * Proveedor de gestion de acceoss
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // configuro politica de sesion sin estado
				.and().cors() // Aniado configuracion CORS por defecto
				.and().csrf().disable() // Deshabilito csrf ya que exponemos el api a cualquier dominio
				.authorizeRequests().antMatchers("/public/**").permitAll() // Permito pasar sin autenticar todas las peticiones que vayan a "/public"
				.anyRequest().authenticated() // El resto de peticiones deben ser autenticadas;
				//Filtro de atenticacion
				//Primer filtro intercepta peticiones de login
				.and().addFilterBefore(new JwtAuthenticationFilter(authenticationManagerBean()),UsernamePasswordAuthenticationFilter.class)
				//Segundo filtro valida el token
				.addFilterBefore(jwtAuthorizationFilterBean(), UsernamePasswordAuthenticationFilter.class);
	}
	

}
