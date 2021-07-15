package um.tiendas.wallapop2.seguridad;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import um.tiendas.wallapop2.entity.Usuario;
import um.tiendas.wallapop2.servicios.UsuarioService;

@Service("wallapopUserDetails")
public class WallapopUserDetails implements UserDetailsService {
	
	@Autowired
	private UsuarioService usuarioService;

	@Override
	public UserDetails loadUserByUsername(String username) {
		List<Usuario> usuarios = usuarioService.findUsuarioByEmail(username);

		if (usuarios.isEmpty() || usuarios.size() > 1) {
			throw new UsernameNotFoundException("No se ha encontrado al usuario");
		} else {

			// Crea un constructor de usuario para "usermane"
			UserBuilder userBuilder = User.withUsername(username);
			// Configura el usuario como activo
			userBuilder.disabled(false);
			// Encapsula el password (este valor es obligatorio)
			userBuilder.password(usuarios.get(0).getPassword());
			// Modo de autorizacion (este valor es obligatorio)
			userBuilder.authorities(new SimpleGrantedAuthority(usuarios.get(0).getRol().getNombre()));
			// Generamos el usuario
			return userBuilder.build();
		}
	}

}
