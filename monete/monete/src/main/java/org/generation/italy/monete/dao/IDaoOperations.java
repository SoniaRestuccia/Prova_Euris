package org.generation.italy.monete.dao;

public interface IDaoOperations {
	
	/**
	 * Metodo che riceve come parametri due stringhe e ne calcola la somma
	 * 
	 * @param cost1
	 * @param cost2
	 * @return una stringa pari alla somma tra i due
	 */
	String sum(String cost1, String cost2);

	/**
	 * Metodo che riceve come parametri due stringhe e ne calcola la differenza
	 * 
	 * @param cost1
	 * @param cost2
	 * @return una stringa pari alla differenza tra i due
	 */
	String sub(String cost1, String cost2);

	/**
	 * Metodo che riceve come parametri una stringa e un fattore moltiplicativo e
	 * restituisce la moltiplicazione
	 * 
	 * @param cost
	 * @param mult
	 * @return una stringa pari alla moltiplicazione tra i due
	 */
	String mult(String cost, int mult);

	/**
	 * Metodo che riceve come parametri una stringa e un divisore e restituisce la
	 * divisione
	 * 
	 * @param cost
	 * @param div
	 * @return una stringa pari alla divisione tra i due
	 */
	String div(String cost, int div);
}
