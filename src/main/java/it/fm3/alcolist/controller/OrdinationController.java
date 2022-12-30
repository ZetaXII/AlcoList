package it.fm3.alcolist.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.fm3.alcolist.DTO.OrdinationDTO;
import it.fm3.alcolist.service.OrdinationServiceI;

@RestController
@CrossOrigin
@RequestMapping("manage-ordination")
public class OrdinationController {
	
	@Autowired
	OrdinationServiceI ordinationService;
	// TODO definire un json che permetta al frontend di gestire le eccezioni
	// in caso di errori per lo status 

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<?> add(@RequestBody OrdinationDTO ordinationDTO, HttpServletRequest request){
		try {
			return ResponseEntity.ok(ordinationService.add(ordinationDTO));
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
}