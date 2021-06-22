package com.fatec.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.model.Beneficiado;
import com.fatec.repository.BeneficiadoRepository;
import com.fatec.service.CSVService;

@RestController
@RequestMapping("/log")
public class LoggingController {
	
	@Autowired
	CSVService service;
	@Autowired
	BeneficiadoRepository rep;
	
	@GetMapping
	public ResponseEntity<List<Beneficiado>> index() {
		return  ResponseEntity.ok().body(service.getAllBeneficiados());
	}
	
	@PostMapping
	public ResponseEntity<String> addBeneficiado() {
		rep.save(new Beneficiado("jose","abc","123"));
		return  ResponseEntity.ok().body("Cadastrou");
	}
}
