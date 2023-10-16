package org.java.app.api;

import java.util.List;
import java.util.Optional;

import org.java.app.api.dto.PizzaDto;
import org.java.app.db.pojo.Pizza;
import org.java.app.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/pizzeria")
public class PizzaRestController {

	@Autowired
	private PizzaService pizzaServ;
	
	
	
	
	@GetMapping
	public ResponseEntity<List<Pizza>> getAll() {
		
		List<Pizza> pizze = pizzaServ.findAll();
		
		return new ResponseEntity<List<Pizza>>(pizze, HttpStatus.OK);
	}
	
	
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Pizza> getPizza(
				@PathVariable int id
			) {
		
		Optional<Pizza> optPizza = pizzaServ.findById(id);
		
		if (optPizza.isEmpty())
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(optPizza.get(), HttpStatus.OK);
	}
	
	
	
	
	@PostMapping
	public ResponseEntity<Pizza> create(
				@RequestBody PizzaDto pizzaDto
			) {
		
		Pizza pizza = new Pizza(
					pizzaDto.getNome(), 	
					pizzaDto.getDescrizione(), 
					pizzaDto.getFoto(), 
					pizzaDto.getPrezzo()
				);
		
	    pizzaServ.save(pizza);
		
		return new ResponseEntity<Pizza>(pizza, HttpStatus.OK);
		
	}
}
