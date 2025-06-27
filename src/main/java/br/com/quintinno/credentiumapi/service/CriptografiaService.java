package br.com.quintinno.credentiumapi.service;

import java.util.Base64;
import java.util.Optional;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.quintinno.credentiumapi.utility.ConstantesUtility;

@Service
public class CriptografiaService {

	@Value("${credentiumapi.chave-secreta}")
	private String chaveSecreta;

	public String criptografar(String chave) {
		String chaveValidada = validarChave(chave);
		try {
			Cipher cipher = this.getCipher(Cipher.ENCRYPT_MODE, chaveSecreta);
			byte[] chaveCriptografada = cipher.doFinal(chaveValidada.getBytes());
			return Base64.getEncoder().encodeToString(chaveCriptografada);
		} catch (Exception e) {
			throw new RuntimeException(String.format(ConstantesUtility.MENSAGEM_0001, "Criptografar"), e);
		}
	}

	public String descriptografar(String chave) {
		String chaveValidada = validarChave(chave);
		try {
			Cipher cipher = this.getCipher(Cipher.DECRYPT_MODE, chaveSecreta);
			byte[] chaveDecriptografada = cipher.doFinal(Base64.getDecoder().decode(chaveValidada));
			return new String(chaveDecriptografada);
		} catch (Exception e) {
			throw new RuntimeException(String.format(ConstantesUtility.MENSAGEM_0001, "Descriptografar"), e);
		}
	}
	
	private String validarChave(String chave) {
		return Optional.ofNullable(chave).filter(chaveOptional -> !chaveOptional.isBlank())
				.orElseThrow(() -> new IllegalArgumentException(ConstantesUtility.MENSAGEM_0002));
	}
	
	private Cipher getCipher(int cipherMode, String chave) throws Exception {
		Cipher cipher = Cipher.getInstance(ConstantesUtility.CRIPTOGRAFIA_AES);
		SecretKeySpec secretKeySpec = new SecretKeySpec(chave.getBytes(), ConstantesUtility.CRIPTOGRAFIA_AES);
		cipher.init(cipherMode, secretKeySpec);
		return cipher;
	}

}
