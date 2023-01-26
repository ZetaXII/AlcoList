package it.fm3.alcolist.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.fm3.alcolist.DTO.IngredientDTO;
import it.fm3.alcolist.service.IngredientServiceI;

@RestController
@CrossOrigin
@RequestMapping("manage-ingredients")
public class IngredientController {
	
	@Autowired
	IngredientServiceI ingredientService;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<?> add(@RequestBody IngredientDTO ingredient, HttpServletRequest request){
		try {
			return ResponseEntity.ok(ingredientService.add(ingredient));
		} catch (Exception e) { 
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@RequestParam String uuid, HttpServletRequest request) throws Exception{
		return new ResponseEntity<>(ingredientService.delete(uuid),HttpStatus.OK);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<?> update(@RequestBody IngredientDTO ingredient, HttpServletRequest request) throws Exception{
		return ResponseEntity.ok(ingredientService.update(ingredient));
	}
	
	@RequestMapping(value = "/get/{uuid}", method = RequestMethod.GET)
	public ResponseEntity<?> getByUuid(@PathVariable(name = "uuid") String uuid) throws Exception {
		return ResponseEntity.ok(ingredientService.get(uuid));
	}
	
}