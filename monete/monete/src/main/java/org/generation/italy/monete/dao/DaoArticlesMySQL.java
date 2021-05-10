package org.generation.italy.monete.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.generation.italy.monete.model.Article;
import org.generation.italy.monete.util.BasicDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class DaoArticlesMySQL extends BasicDao implements IDaoArticle {
	// recupera i dati per la connessione dal file application properties
	public DaoArticlesMySQL(@Value("${db.address}") String dbAddress, @Value("${db.user}") String user,
			@Value("${db.psw}") String password) {
		super(dbAddress, user, password);
	}

	// lista articoli
	@Override
	public List<Article> articles() {
		List<Article> res = new ArrayList<>();

		List<Map<String, String>> maps = getAll("SELECT * FROM articles");

		for (Map<String, String> map : maps) {
			Article a = new Article();
			a.fromMap(map);
			res.add(a);
		}

		return res;
	}

	// singolo articolo tramite code
	@Override
	public Article article(int code) {
		Article res = null;
		Map<String, String> map = getOne("SELECT * FROM articles WHERE code = ?", code);

		if (map != null) {
			res = new Article();
			res.fromMap(map);
		}

		return res;
	}

	// aggiunta
	@Override
	public void add(Article article) {
		execute("INSERT INTO articles (name, cost) VALUES (?, ?)", article.getName(), article.getCost());
	}

	// cancellazione
	@Override
	public void delete(int code) {
		execute("DELETE FROM articles WHERE code = ?", code);
	}

	// modifica
	@Override
	public void update(Article article) {
		execute("UPDATE articles SET name = ?, cost = ? WHERE code = ?", article.getName(), article.getCost(),
				article.getCode());
	}

}
