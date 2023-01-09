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

import it.fm3.alcolist.DTO.MessageDTO;
import it.fm3.alcolist.DTO.OrderedCocktailDTO;
import it.fm3.alcolist.DTO.OrdinationDTO;
import it.fm3.alcolist.DTO.OrdinationStatusEnum;
import it.fm3.alcolist.service.OrdinationServiceI;

@RestController
@CrossOrigin
@RequestMapping("manage-ordinations")
public class OrdinationController {
	
	@Autowired
	OrdinationServiceI ordinationService;
	// TODO definire un json che permetta al frontend di gestire le eccezioni: status ordination
	// in caso di errori per lo status 

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody OrdinationDTO ordinationDTO, HttpServletRequest request){
		try {
			return ResponseEntity.ok(ordinationService.create(ordinationDTO));
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/get/{uuid}", method = RequestMethod.GET)
	public ResponseEntity<?> get(@PathVariable(name = "uuid")String uuid, HttpServletRequest request){
		try {
			return ResponseEntity.ok(ordinationService.get(uuid));
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/addCocktail", method = RequestMethod.POST)
	public ResponseEntity<?> add(@RequestBody OrderedCocktailDTO oc, HttpServletRequest request){
		try {
			return ResponseEntity.ok(ordinationService.addCocktail(oc));
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/removeCocktail", method = RequestMethod.POST)
	public ResponseEntity<?> removeCocktail(@RequestBody OrderedCocktailDTO oc, HttpServletRequest request){
		try {
			return ResponseEntity.ok(ordinationService.removeCocktail(oc));
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/updateStatus/{uuid}", method = RequestMethod.POST)
	public ResponseEntity<?> updateStatus(@PathVariable(name = "uuid") String uuid,@RequestParam OrdinationStatusEnum status,@RequestBody MessageDTO msg, HttpServletRequest request) throws Exception{
		return ResponseEntity.ok(ordinationService.updateStatus(uuid,status,msg));
	}
	
	@RequestMapping(value = "/searchByFields", method = RequestMethod.POST)
	public ResponseEntity<?> searchByFields(@RequestBody OrdinationDTO ordination, HttpServletRequest request) throws Exception {
		return ResponseEntity.ok(ordinationService.searchByFields(ordination));
	}
	
}