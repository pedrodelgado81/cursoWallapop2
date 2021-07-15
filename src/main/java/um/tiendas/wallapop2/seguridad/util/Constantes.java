package um.tiendas.wallapop2.seguridad.util;

//Clase de constantes de seguridad declarada como final y con constructor privado para que no pueda extenderse
public final class Constantes {

	private Constantes() {
	}

	public static final String HEADER_AUTHORIZATION_KEY = "Authorization";
	public static final String TOKEN_BEARER_PREFIX = "Bearer"; 
	public static final String ISSUER_TOKEN = "WALLAPOP";
	public static final String ISSUER_INFO = "https://ticarum.es/";
	public static final String SIGNING_KEY = "123467891011121314151617181920123467891011121314151617181920123467891011121314151617181920123467891011121314151617181920";
	public static final long TOKEN_EXPIRATION_TIME = 864_000_000; // 10 dias
	public static final String AUTHORITIES_KEY = "CLAIM_TOKEN";

}
