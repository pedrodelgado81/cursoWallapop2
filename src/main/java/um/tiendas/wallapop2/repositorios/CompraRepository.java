package um.tiendas.wallapop2.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import um.tiendas.wallapop2.entity.Compra;
import um.tiendas.wallapop2.entity.Usuario;

public interface CompraRepository extends JpaRepository<Compra, Integer>{
	
	List<Compra> findByUsuario(Usuario usuario);

}