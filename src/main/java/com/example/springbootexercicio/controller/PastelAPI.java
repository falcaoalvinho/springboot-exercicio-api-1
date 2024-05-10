package com.example.springbootexercicio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootexercicio.model.Pastel;
import com.example.springbootexercicio.repository.PastelDAL;

@RestController
@RequestMapping ("/pastel")
public class PastelAPI {
	
	@Autowired
	PastelDAL dal;
	
	@GetMapping
	public String ListPasteis() {
		String temporary = "";
		
		for (Pastel pastel : dal.Read()) {
			temporary += pastel.getId();
			temporary += ". ";
			temporary += pastel.getNome();
			temporary += "\n";
		}
		
		return temporary;
	}
	
	@PostMapping
	public String InsertPastel(@RequestBody Pastel pastel) {
		return String.valueOf(dal.Create(pastel));
	}
	
	@DeleteMapping
	public String DeletePastel(@RequestBody Pastel pastel) {
		return String.valueOf(dal.Delete(pastel));
	}
	
	@PutMapping
	public String UpdatePastel(@RequestBody Pastel pastel, Long id) {
		return String.valueOf(dal.Update(pastel, id));
	}

}
