package com.example.laboratorio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.laboratorio.model.Nota;

public interface NotaRepository extends JpaRepository<Nota, Long> {
    
}
