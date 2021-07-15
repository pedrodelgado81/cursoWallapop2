package um.tiendas.wallapop2.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import um.tiendas.wallapop2.entity.Usuario;
import um.tiendas.wallapop2.repositorios.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	//El email del usuario debe ser unico, si no lo es devolvemos nulo
	public List<Usuario> findUsuarioByEmail(String email) {
		return  this.usuarioRepository.findByEmail(email);		
	}

	public void registrarUsuario(Usuario usuario) {
		this.usuarioRepository.saveAndFlush(usuario);
	}

	public List<Usuario> listarUsuarios() {
		return this.usuarioRepository.findAll();
	}

	public Usuario findUsuarioById(Integer idUsuario) {
		return this.usuarioRepository.findById(idUsuario).orElse(null);
	}

}
