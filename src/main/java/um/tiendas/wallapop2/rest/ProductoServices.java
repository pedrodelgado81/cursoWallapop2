package um.tiendas.wallapop2.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import um.tiendas.wallapop2.entity.Producto;
import um.tiendas.wallapop2.entity.Usuario;
import um.tiendas.wallapop2.repositorios.ProductoRepository;
import um.tiendas.wallapop2.repositorios.UsuarioRepository;


@RestController
@RequestMapping(value = "/public/wallapop")
public class ProductoServices {

	@Autowired
	private ProductoRepository productoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

    @Value ("${umu.wallapop2.sucursal}")
    private String sucursal;
	
    @GetMapping("api/{version}/productos/{id}")
	public Producto getProductoById(@PathVariable("id") Integer id) {
		return this.productoRepository.findById(id).orElse(null);
	}

    @GetMapping("api/{version}/productos")
	public List<Producto> obtenerTodosProductos() {
		return this.productoRepository.findAll();
	}

    @PostMapping("api/{version}/productos/add")
    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
	public Integer registrarProducto(@RequestParam Map<String, String> inputData) {
		Producto producto = new Producto();
		producto.setNombre(inputData.get("nombre"));
		producto.setPrecio(Double.parseDouble(inputData.get("precio")));
		producto.setDescripcionCorta(inputData.get("descripcion_corta"));
		producto.setDescripcionLarga(inputData.get("descripcion_larga"));
		producto.setImagen(inputData.get("imagen"));

		Usuario usuario = usuarioRepository.findFirstByEmail(inputData.get("usuario"));
		producto.setUsuario(usuario);
		
        Producto resultado = this.productoRepository.saveAndFlush(producto);

		return resultado.getId();
	}

	@GetMapping("api/{version}/sucursal")
	public String getSucursal(){
		return sucursal;
	}

}