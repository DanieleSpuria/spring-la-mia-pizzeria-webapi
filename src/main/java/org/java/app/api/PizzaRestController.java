package org.java.app.api;

import java.util.List;
import java.util.Optional;

import org.java.app.api.dto.PizzaDto;
import org.java.app.db.pojo.Ingrediente;
import org.java.app.db.pojo.Offerta;
import org.java.app.db.pojo.Pizza;
import org.java.app.db.serv.IngredienteService;
import org.java.app.db.serv.OffertaService;
import org.java.app.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/pizzeria")
public class PizzaRestController {

	@Autowired
	private PizzaService pizzaServ;
	
	@Autowired
	private IngredienteService ingredienteServ;
	
	@Autowired 
	private OffertaService offertaServ;
	
	
	
	
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
		
		Pizza pizza = new Pizza(pizzaDto);
		
	    pizzaServ.save(pizza);
		
		return new ResponseEntity<Pizza>(pizza, HttpStatus.OK);
		
	}
	
	
	
	
	@PutMapping("{id}")
	public  ResponseEntity<Pizza> update(
				@PathVariable int id, 
				@RequestBody PizzaDto pizzaDto
			) {
		
		Optional<Pizza> optPizza = pizzaServ.findById(id);
		
		if (optPizza.isEmpty()) 
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		
		Pizza pizza = optPizza.get();
		
		pizza.updatePizza(pizzaDto);
		
		try {
			pizzaServ.save(pizza);
			
			return new ResponseEntity<Pizza>(pizza, HttpStatus.OK);
		} catch (Exception e) {
			
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> delete(
				@PathVariable int id
			) {
		
			Optional<Pizza> optPizza = pizzaServ.findById(id);
			
			if (optPizza.isEmpty())
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			
			List <Offerta> offerte = offertaServ.findAll();
			List <Ingrediente> ingredienti = ingredienteServ.findAll();
	
			for (Offerta offerta : offerte) 			
				if (offerta.getPizza().getId() == id) 
					offertaServ.deleteOfferta(offerta);
			
			for (Ingrediente ingrediente : ingredienti)
				if (ingrediente.getPizze().contains(optPizza.get()))
					ingrediente.getPizze().remove(optPizza.get());
			
			Pizza pizza = optPizza.get();
			pizzaServ.deletePizza(pizza);
			
			return new ResponseEntity<String>("Pizza eliminata.", HttpStatus.OK);
	}
}
