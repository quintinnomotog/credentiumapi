package br.com.quintinno.credentiumapi;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.SecretKeySpec;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import br.com.quintinno.credentiumapi.service.CriptografiaService;

@SpringBootTest
class CriptografiaServiceTest {

	@Autowired
	private CriptografiaService criptografiaService;

	@Test
	void deveCriptografarComSucesso() {
		String chave = "?SenhaSegura?";
		String chaveCriptografada = criptografiaService.criptografar(chave);
		Assertions.assertNotEquals(chave, chaveCriptografada);
	}

	@Test
	void deveDescriptografarComSucesso() {
		String chave = "?SenhaSegura?";
		String chaveCriptografada = criptografiaService.criptografar(chave);
		String chaveDescriptografada = criptografiaService.descriptografar(chaveCriptografada);
		Assertions.assertEquals(chave, chaveDescriptografada);
	}

	@Test
	void deveCriptografarComCaracteresEspeciais() {
		String original = "יוֹסֵףç&~%¨#@©🚀✓漢字";
		String criptografado = criptografiaService.criptografar(original);
		String descriptografado = criptografiaService.descriptografar(criptografado);
		Assertions.assertEquals(original, descriptografado);
	}

	@Test
	void deveFalharAoCriptografarNulo() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			criptografiaService.criptografar(null);
		});
	}
	
	@Test
	void deveFalharAoCriptografarVazio() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			criptografiaService.criptografar("");
		});
	}

	@Test
	void deveFalharAoDescriptografarValorCorrompido() {
		String original = "senha123";
		String criptografado = criptografiaService.criptografar(original);
		String corrompido = criptografado.substring(0, criptografado.length() - 2) + "xx";
		Assertions.assertThrows(RuntimeException.class, () -> {
			criptografiaService.descriptografar(corrompido);
		});
	}
	
	@Test
	void deveLancarIllegalBlockSizeExceptionComNoPadding() throws Exception {
	    Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
	    SecretKeySpec secretKey = new SecretKeySpec("1234567890123456".getBytes(), "AES");
	    cipher.init(Cipher.ENCRYPT_MODE, secretKey);
	    byte[] invalido = "teste".getBytes();
	    Assertions.assertThrows(IllegalBlockSizeException.class, () -> {
	        cipher.doFinal(invalido);
	    });
	}
	
	@Test
	void deveFalharAoCriptografarComChaveSecretaInvalida() {
	    CriptografiaService serviceInvalido = new CriptografiaService();
	    ReflectionTestUtils.setField(serviceInvalido, "chaveSecreta", "chaveErrada");
	    RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
	        serviceInvalido.criptografar("algum texto");
	    });
	    Assertions.assertTrue(exception.getMessage().contains("Criptografar"));
	}

}
