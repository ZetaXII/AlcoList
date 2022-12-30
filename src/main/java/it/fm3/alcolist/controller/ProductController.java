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

import it.fm3.alcolist.DTO.ProductDTO;
import it.fm3.alcolist.service.ProductServiceI;

@RestController
@CrossOrigin
@RequestMapping("manage-products")
public class ProductController {
	
	@Autowired
	ProductServiceI productService;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<?> add(@RequestBody ProductDTO product, HttpServletRequest request){
		try {
			return ResponseEntity.ok(productService.add(product));
		} catch (Exception e) {
			// TODO definire un json che permetta al frontend di gestire le eccezioni
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@RequestParam String name, HttpServletRequest request) throws Exception{
		return new ResponseEntity<>(productService.delete(name),HttpStatus.OK);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<?> update(@RequestBody ProductDTO product, HttpServletRequest request) throws Exception{
		return ResponseEntity.ok(productService.update(product));
	}
	
	@RequestMapping(value = "/get/{uuid}", method = RequestMethod.GET)
	public ResponseEntity<?> getByName(@PathVariable(name = "uuid") String uuid) throws Exception {
		return ResponseEntity.ok(productService.get(uuid));
	}
	
	@RequestMapping(value = "/searchByFields", method = RequestMethod.POST)
	public ResponseEntity<?> searchByFields(@RequestBody ProductDTO product, HttpServletRequest request) throws Exception {
		return ResponseEntity.ok(productService.searchByFields(product));
	}
	
}