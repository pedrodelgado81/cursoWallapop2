package um.tiendas.wallapop2.rest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/private/wallapop")
public class AdminServices {
	
	
	@GetMapping("/healthz")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String healthz() {
		return "Hola";
	}

}
