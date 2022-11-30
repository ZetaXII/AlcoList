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

import it.fm3.alcolist.DTO.UserAccountDTO;
import it.fm3.alcolist.service.UserAccountServiceI;

@RestController
@CrossOrigin
@RequestMapping("manage-users")
public class UserAccountController {
	
	@Autowired
	UserAccountServiceI userService;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<?> add(@RequestBody UserAccountDTO user, HttpServletRequest request){
		try {
			return ResponseEntity.ok(userService.add(user));
		} catch (Exception e) {
			// TODO definire un json che permetta al frontend di gestire le eccezioni: email già usata
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@RequestParam String uuid, HttpServletRequest request) throws Exception{
		return new ResponseEntity<>(userService.delete(uuid),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<?> update(@RequestBody UserAccountDTO user, HttpServletRequest request) throws Exception{
		return ResponseEntity.ok(userService.update(user));
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody UserAccountDTO user, HttpServletRequest request){
		try {
			return ResponseEntity.ok(userService.login(user));
		} catch (Exception e) {
			// TODO definire un json che permetta al frontend di gestire le eccezioni: pwd errata o utente non esistente
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/get/{uuid}", method = RequestMethod.GET)
	public ResponseEntity<?> getByUuid(@PathVariable(name = "uuid") String uuid) throws Exception {
		return ResponseEntity.ok(userService.get(uuid));
	}
	
}
