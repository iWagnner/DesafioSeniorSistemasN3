package br.com.senior.prova3.util;

import java.io.Serializable;
/**
 * Autor: Wagner Ribeiro
 * Data: 12 de julho de 2023
 */
public class Util implements Serializable {
	private static final long serialVersionUID = 1L;

	 public static boolean stringNullOrEmpty(String txt) {
	        return txt == null || txt.trim().isEmpty();
	    }
	 
		public static String removerEspacosCaracteresEspeciais(String texto) {
			if (texto != null) {
				// Remove espaços em branco
				texto.replaceAll("\\s", "");
				texto.replaceAll("[^a-zA-Z0-9]", "");
			}
			return texto;
		}
}
