package com.example.laboratorio.service;

import java.util.List;
import java.util.Optional;

import com.example.laboratorio.model.Nota;

public interface NotaService {

    List<Nota> listarTodas();
    Nota crear(Nota nota);
    Nota actualizar(Long id, Nota notaActualizada);
    void eliminar(Long id);
    Optional<Nota> buscarPorId(Long id);
    
}
