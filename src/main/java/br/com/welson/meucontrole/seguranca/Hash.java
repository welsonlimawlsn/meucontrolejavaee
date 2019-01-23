package br.com.welson.meucontrole.seguranca;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class Hash {
	public static String encode(String value) {
        try {
            MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
            byte[] digest = algorithm.digest(value.getBytes(StandardCharsets.UTF_8));
            StringBuilder hex = new StringBuilder();
            for (byte b : digest) {
                hex.append(String.format("%02X", 0xFF & b));
            }
            return hex.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new WebApplicationException("Exceção ao gerar o hash", e, Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}
