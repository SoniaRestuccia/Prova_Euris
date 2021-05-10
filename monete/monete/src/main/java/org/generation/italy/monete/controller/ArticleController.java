package org.generation.italy.monete.controller;

import java.util.List;

import org.generation.italy.monete.dao.IDaoArticle;
import org.generation.italy.monete.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articles")
public class ArticleController {

	@Autowired
	private IDaoArticle dao;

	// visualizzazione della lista
	@GetMapping
	public List<Article> get() {
		return dao.articles();
	}

	// visualizzazione del singolo tramite code
	@GetMapping("/{code}")
	public Article getOne(@PathVariable int code) {
		return dao.article(code);

	}

	// cancellazione
	@DeleteMapping("/{code}")
	public void delete(@PathVariable int code) {
		dao.delete(code);
	}

	// aggiunta
	@PostMapping
	public void add(@RequestBody Article article) {

		dao.add(article);
	}

	// modifica
	@PutMapping
	public void update(@RequestBody Article article) {
		dao.update(article);
	}

}
