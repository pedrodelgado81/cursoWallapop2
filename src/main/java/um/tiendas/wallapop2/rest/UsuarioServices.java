package um.tiendas.wallapop2.rest;

import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import um.tiendas.wallapop2.entity.Usuario;
import um.tiendas.wallapop2.model.UsuarioDTO;
import um.tiendas.wallapop2.repositorios.UsuarioRepository;

@RestController
@RequestMapping(value = "/public/wallapop")
public class UsuarioServices {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping("api/{version}/usuarios/email/{email}")
	@PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
	public List<Usuario> findUsuarioByEmail(String email) {
		return  this.usuarioRepository.findByEmail(email);		
	}

	@PostMapping("api/{version}/usuarios/add")
	@PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
	public void registrarUsuario(@RequestParam Map<String, String> inputData) {
		Usuario usuario = new Usuario();
		usuario.setNombre(inputData.get("nombre"));
		usuario.setApellidos(inputData.get("apellidos"));
		usuario.setEmail(inputData.get("email"));
		usuario.setPassword(inputData.get("password"));
		usuario.setAvatar(inputData.get("avater"));
		usuario.setFechaAlta(Date.from(Instant.now()));
		
		this.usuarioRepository.saveAndFlush(usuario);
	}

	@GetMapping("api/{version}/usuarios")
	@PreAuthorize(" hasRole('ROLE_ADMIN')")
	// Respuesta tradicional
	//public List<Usuario> listarUsuarios() {		 
		//return this.usuarioRepository.findAll();
	//}
	
	//Respuesta DTO + Funcional
	public List<UsuarioDTO> listarUsuarios() {
		
		//Con Lambda
		//return this.usuarioRepository.findAll().stream().map(u -> u.toDTO()).collect(Collectors.toList());
		//Con referencia a metodo
		return this.usuarioRepository.findAll().stream().map(Usuario::toDTO).collect(Collectors.toList());
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("api/{version}/usuarios/{id}")
	public Usuario findUsuarioById(Integer id) {
		return this.usuarioRepository.findById(id).orElse(null);
	}

}