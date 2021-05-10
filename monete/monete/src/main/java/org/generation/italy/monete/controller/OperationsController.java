package org.generation.italy.monete.controller;

import org.generation.italy.monete.dao.IDaoOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/operations")
public class OperationsController {

	@Autowired
	private IDaoOperations dao;
	//somma
	@GetMapping("/sum/{cost1}/{cost2}")
	public String sum(@PathVariable String cost1, @PathVariable String cost2) {
		return dao.sum(cost1, cost2);

	}
	//sottrazione
	@GetMapping("/sub/{cost1}/{cost2}")
	public String sub(@PathVariable String cost1, @PathVariable String cost2) {
		return dao.sub(cost1, cost2);
	}
	//moltiplicazione
	@GetMapping("/mult/{cost1}/{mult1}")
	public String mult(@PathVariable String cost1, @PathVariable int mult1) {
		return dao.mult(cost1, mult1);
	}
	//divisione
	@GetMapping("/div/{cost1}/{div1}")
	public String div(@PathVariable String cost1, @PathVariable int div1) {
		return dao.div(cost1, div1);

	}

}
