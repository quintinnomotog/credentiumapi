package br.com.quintinno.credentiumapi.utility;

public class SegurancaUtility {

	private static final String[] ENDPOINT_PUBLIC = {
			"/credentium/api/login", 
			"/credentium/api/login/v1",
			"/credentium/api/usuario", 
			"/credentium/api/usuario/v1", 
			"/credentium/criptografia/encoder/aes",
			"/credentium/criptografia/decoder/aes",
			"actuator/info"
	};

	public static boolean isUrlPublica(String path) {
		for (String rota : ENDPOINT_PUBLIC) {
			if (path.startsWith(rota)) {
				return true;
			}
		}
		return false;
	}

	public static String[] getRotas() {
		return ENDPOINT_PUBLIC;
	}

}
