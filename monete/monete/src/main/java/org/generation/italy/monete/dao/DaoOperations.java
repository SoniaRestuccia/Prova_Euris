package org.generation.italy.monete.dao;

import org.springframework.stereotype.Repository;

@Repository
public class DaoOperations implements IDaoOperations {

	@Override
	public String sum(String cost1, String cost2) {
		int p1 = toP(cost1);
		int p2 = toP(cost2);
		int sumP = p1 + p2;
		return pToString(sumP);
	}

	@Override
	public String sub(String cost1, String cost2) {
		int p1 = toP(cost1);
		int p2 = toP(cost2);
		int subP = p1 - p2;
		return pToString(subP);
	}

	@Override
	public String mult(String cost, int mult) {
		int pence = toP(cost);
		int multP = pence * mult;
		return pToString(multP);
	}

	@Override
	public String div(String cost, int div1) {
		int pence = toP(cost);
		int divP = pence / div1;
		int restP = pence % div1;
		// resto indicato tra parentesi
		return pToString(divP) + "(" + pToString(restP) + ")";
	}

	/**
	 * Metodo privato che riceve da parametro il totale dei pence e ritorna una
	 * stringa
	 * 
	 * @param totP
	 * @return una stringa in formato Xp Ys Zd
	 */
	private String pToString(int totP) {

		String res = "";
		int totS = totP / 12;
		int pound = totS / 20;
		int shilling = totS % 20;
		int pence = totP % 12;

		if (pound != 0) {

			res = pound + "p " + shilling + "s " + pence + "d";

		} else if (shilling != 0 && pound == 0) {

			res = shilling + "s " + pence + "d";

		} else if (pound == 0 && shilling == 0) {

			res = pence + "d";
		}

		return res;
	}

	/**
	 * Metodo che riceve da parametro una stringa e restituisce il totale dei pence
	 * 
	 * @param cost
	 * @return il totale dei pence
	 */
	private int toP(String cost) {

		int res = 0;
		// separo ogni volta che trovo uno spazio e memorizzo i dati nell'array cur
		String cur[] = cost.split(" ");
		String temp;
		for (int i = 0; i < cur.length; i++) {
			temp = "";
			for (int j = 0; j < cur[i].length(); j++) {
				char c = cur[i].charAt(j);

				// controllo se è una cifra, se non lo é si tratta di p,s o d
				if (Character.isDigit(c)) {

					// se è una cifra quindi la sommo a temp
					temp += c;

				} else {

					/*
					 * se non è una cifra vuol dire che sono arrivato al carattere finale (p,s,d)
					 * quindi parso tutto ciò che è contenuto dentro temp e poi lo converto in pence
					 */
					switch (c) {

					case 'p':
						res += Integer.parseInt(temp) * 12 * 20;
						break;
					case 's':
						res += Integer.parseInt(temp) * 12;
						break;
					case 'd':
						res += Integer.parseInt(temp);
						break;
					}
				}
			}
		}
		return res;
	}
}