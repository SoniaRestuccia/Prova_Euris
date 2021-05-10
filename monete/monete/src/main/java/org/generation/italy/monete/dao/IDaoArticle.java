package org.generation.italy.monete.dao;

import java.util.List;

import org.generation.italy.monete.model.Article;

public interface IDaoArticle {

	/**
	 * Metodo che restituisce la lista di articoli inseriti nel DB
	 */
	List<Article> articles();

	/**
	 * Metodo che prende come parametro un code e restituisce uno specifico articolo
	 * 
	 * @param code
	 */
	Article article(int code);

	/**
	 * Metodo che prende come parametro un oggetto di tipo Article e lo aggiunge al
	 * DB
	 * 
	 * @param article
	 */
	void add(Article article);

	/**
	 * Metodo che prende come parametro un code e cancella un articolo
	 * corrispondente sul DB
	 * 
	 * @param code
	 */
	void delete(int code);

	/**
	 * Metodo che prende come parametro un oggetto di tipo article e lo modifica nel
	 * DB
	 * 
	 * @param article
	 */
	void update(Article article);

}
