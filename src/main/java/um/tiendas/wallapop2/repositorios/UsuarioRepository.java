package um.tiendas.wallapop2.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import um.tiendas.wallapop2.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	List<Usuario> findByEmail(String email);

	Usuario findFirstByEmail(String email);

}