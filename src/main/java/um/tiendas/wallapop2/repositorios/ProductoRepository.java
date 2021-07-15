package um.tiendas.wallapop2.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import um.tiendas.wallapop2.entity.Producto;
import um.tiendas.wallapop2.entity.Usuario;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

	Producto findByUsuario(Usuario usuario);

	List<Producto> findAll();

	List<Producto> findByCompraIsNull();

	List<Producto> findByNombreContainsIgnoreCaseAndCompraIsNull(String nombre);

	List<Producto> findByNombreContainsIgnoreCaseAndUsuario(String nombre, Usuario propietario);

}