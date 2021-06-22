package com.fatec.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fatec.helper.CSVHelper;
import com.fatec.logging.Logging;
import com.fatec.model.Beneficiado;
import com.fatec.repository.BeneficiadoRepository;

@Service
public class CSVService {
	
	@Autowired
	BeneficiadoRepository repository;
	
	public void save(MultipartFile file) {
		try {
			List<Beneficiado> beneficiados = CSVHelper.csvParaBeneficiados(file.getInputStream());
			repository.saveAll(beneficiados);
		} catch (IOException e) {
			Logging.logger.error("Erro no CSVService " + e.toString()); 
			e.printStackTrace();
		}
	}
	
	public List<Beneficiado> getAllBeneficiados(){
		return repository.findAll();
	}

}
