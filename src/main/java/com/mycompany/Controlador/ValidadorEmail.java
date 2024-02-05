/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Controlador;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *Esta clase es para validar si el eamil tiene un @ o un .
 * @author Pedro Garcia Vicente
 */
public class ValidadorEmail {

  private static final String PATRON_EMAIL
    = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

  private final Pattern pattern;

  public ValidadorEmail() {
    pattern = Pattern.compile(PATRON_EMAIL);
  }

  public boolean validarEmail(String email) {
    Matcher matcher = pattern.matcher(email);
    return matcher.matches();
  }
}
