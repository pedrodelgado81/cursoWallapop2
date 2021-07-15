package um.tiendas.wallapop2.model;

import lombok.Data;

@Data
public class UsuarioDTO {

	private String nombre;
	private String apellidos;
	private String email;

	public UsuarioDTO(String nombre, String apellidos, String email) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
	}

}
