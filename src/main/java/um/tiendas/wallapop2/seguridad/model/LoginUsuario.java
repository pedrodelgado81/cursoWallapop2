package um.tiendas.wallapop2.seguridad.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class LoginUsuario {
	
	private String email;

	private String password;

	public LoginUsuario() {
	}
	
	
}
