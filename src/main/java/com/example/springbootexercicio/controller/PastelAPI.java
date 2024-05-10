package com.example.springbootexercicio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	//Respostas para requisções sem MAP
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
	public String InsertBODY(@RequestBody Pastel pastel) {
		return String.valueOf(dal.Create(pastel));
	}
	
	@DeleteMapping
	public String DeleteBODY(@RequestBody Pastel pastel) {
		return String.valueOf(dal.Delete(pastel));
	}
	
	@PutMapping
	public String UpdateBODY(@RequestBody Pastel pastel) {
		return String.valueOf(dal.Update(pastel));
	}

	//Resposta para resquisições com MAP
	@GetMapping("/{id}")
	public String ListaURL(@PathVariable Long id) {
		Pastel pastel = dal.ReadURL(id);
		
		String temporary = "";
		
		temporary += pastel.getId();
		temporary += ". ";
		temporary += pastel.getNome();

		return temporary;
	}
	
	@PostMapping("/{nome}/{descricao}")
	public String InsertURL(@PathVariable String nome,@PathVariable String descricao) {
		Pastel pastel = new Pastel(null, nome, descricao);
		return String.valueOf(dal.Create(pastel));
	}
	
	@PutMapping("{nome}/{descricao}/{id}")
	public String UpdateBODY(@PathVariable String nome, @PathVariable String descricao, @PathVariable Long id) {
		Pastel pastel = new Pastel(id, nome, descricao);
		return String.valueOf(dal.Update(pastel));
	}
	
	@DeleteMapping("/{id}")
	public String DeleteURL(@PathVariable Long id) {
		return String.valueOf(dal.DeleteURL(id));
	}
}
