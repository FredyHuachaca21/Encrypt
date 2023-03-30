package com.fredgar.pe.principal;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Encrypt {

  public static void main(String[] args) {
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    String textoPlano = "Este es el texto a encriptar";
    String textoEncriptado = passwordEncoder.encode(textoPlano);
    System.out.println(textoEncriptado);

    boolean esIgual = passwordEncoder.matches(textoPlano, textoEncriptado);
    System.out.println("esIgual = " + esIgual);
  }

}
