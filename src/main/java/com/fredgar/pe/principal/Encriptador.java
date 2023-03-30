package com.fredgar.pe.principal;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Encriptador {

  // Clave de encriptación compartida
  private static final String CLAVE = "miClaveDeEncriptacion";

  /**
   * Encripta un texto utilizando el algoritmo AES con la clave de encriptación compartida
   * @param textoPlano Texto que se desea encriptar
   * @return Texto encriptado en formato Base64
   */
  public static String encriptar(String textoPlano) {
    try {
      // Generar la clave de encriptación a partir de la clave compartida utilizando el algoritmo SHA-256
      MessageDigest md = MessageDigest.getInstance("SHA-256");
      md.update(CLAVE.getBytes());
      byte[] clave = md.digest();

      // Crear un objeto SecretKeySpec con la clave generada
      SecretKeySpec secretKey = new SecretKeySpec(clave, "AES");

      // Crear un objeto Cipher con el algoritmo AES y el modo de cifrado ECB
      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

      // Inicializar el objeto Cipher en modo de encriptación y con la clave SecretKeySpec
      cipher.init(Cipher.ENCRYPT_MODE, secretKey);

      // Encriptar el texto plano y obtener los bytes encriptados
      byte[] bytesEncriptados = cipher.doFinal(textoPlano.getBytes());

      // Convertir los bytes encriptados a una cadena de texto en formato Base64
      return Base64.getEncoder().encodeToString(bytesEncriptados);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Desencripta un texto encriptado en formato Base64 utilizando el algoritmo AES y la clave de encriptación compartida
   * @param textoEncriptado Texto encriptado en formato Base64
   * @return Texto desencriptado
   */
  public static String desencriptar(String textoEncriptado) {
    try {
      // Decodificar la cadena de texto encriptada en formato Base64 a bytes encriptados
      byte[] bytesEncriptados = Base64.getDecoder().decode(textoEncriptado);

      // Generar la clave de encriptación a partir de la clave compartida utilizando el algoritmo SHA-256
      MessageDigest md = MessageDigest.getInstance("SHA-256");
      md.update(CLAVE.getBytes());
      byte[] clave = md.digest();

      // Crear un objeto SecretKeySpec con la clave generada
      SecretKeySpec secretKey = new SecretKeySpec(clave, "AES");

      // Crear un objeto Cipher con el algoritmo AES y el modo de cifrado ECB
      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

      // Inicializar el objeto Cipher en modo de desencriptación y con la clave SecretKeySpec
      cipher.init(Cipher.DECRYPT_MODE, secretKey);

      // Desencriptar los bytes encriptados y obtener el texto plano
      byte[] bytesDesencriptados = cipher.doFinal(bytesEncriptados);

      // Convertir los bytes desencriptados a una cadena de texto
      return new String(bytesDesencriptados);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
