package com.fatec.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatec.model.Beneficiado;

@Repository
public interface BeneficiadoRepository extends JpaRepository<Beneficiado,Long>{

}
