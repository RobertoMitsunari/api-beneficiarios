package com.fatec.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import com.fatec.logging.Logging;
import com.fatec.model.Beneficiado;

public class CSVHelper {
	public static String TYPE = "text/csv";
	static String[] HEADERs = { "CPF FAVORECIDO", "NIS FAVORECIDO", "NOME FAVORECIDO" };

	public static boolean hasCSVFormat(MultipartFile file) {

		if (!TYPE.equals(file.getContentType())) {
			return false;
		}

		return true;
	}

	public static List<Beneficiado> csvParaBeneficiados(InputStream is) {
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				CSVParser csvParser = new CSVParser(fileReader,
						CSVFormat.EXCEL.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim().withDelimiter(';'));)
		{

			List<Beneficiado> beneficiados = new ArrayList<Beneficiado>();

			Iterable<CSVRecord> csvRecords = csvParser.getRecords();
			int qnt = 100;
			int cont = 0;
			for (CSVRecord csvRecord : csvRecords) {
				Beneficiado beneficiado = new Beneficiado(csvRecord.get("NOME"),
						csvRecord.get("CPF FAVORECIDO"),
						csvRecord.get("NIS FAVORECIDO"));
				beneficiados.add(beneficiado);
				if(cont >= 100) {
					break;
				}
				cont++;
			}

			return beneficiados;
		} catch (IOException e) {
			Logging.logger.error("fail to parse CSV file: " + e.getMessage());
			throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		}
	}

}
