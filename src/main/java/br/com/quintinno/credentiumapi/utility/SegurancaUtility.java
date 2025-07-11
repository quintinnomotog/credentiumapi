package br.com.quintinno.credentiumapi.utility;

public class SegurancaUtility {

	private static final String[] ROTAS_PUBLICAS = { 
			"/credentium/api/login", 
			"/credentium/api/login/v1",
			"/credentium/api/usuario", 
			"/credentium/api/usuario/v1", 
			"/credencium/criptografia/encoder/aes",
			"/credencium/criptografia/decoder/aes" 
	};

	public static boolean isPublica(String path) {
		for (String rota : ROTAS_PUBLICAS) {
			if (path.startsWith(rota)) {
				return true;
			}
		}
		return false;
	}

	public static String[] getRotas() {
		return ROTAS_PUBLICAS;
	}

}
