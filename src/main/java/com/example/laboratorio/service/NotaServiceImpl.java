package com.example.laboratorio.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.example.laboratorio.model.Nota;
import com.example.laboratorio.repository.NotaRepository;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NotaServiceImpl implements NotaService {

    private final NotaRepository notaRepository;

    public NotaServiceImpl(NotaRepository notaRepository) {
        this.notaRepository = notaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Nota> listarTodas() {
        return notaRepository.findAll();
    }

    @Override
    public Nota crear(Nota nota) {
        return notaRepository.save(nota);
    }

    @Override
    public Nota actualizar(Long id, Nota n) {
        return notaRepository.findById(id)
                .map(actual -> {
                    actual.setTitulo(n.getTitulo());
                    actual.setContenido(n.getContenido());
                    actual.setAutor(n.getAutor());
                    actual.setImportancia(n.getImportancia());
                    return notaRepository.save(actual);
                })
                .orElseThrow(() -> new RuntimeException("Nota no encontrada con id: " + id));
    }

    @Override
    public void eliminar(Long id) {
        if (notaRepository.existsById(id)) {
            notaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Nota no encontrada con id: " + id);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Nota> buscarPorId(Long id) {
        return notaRepository.findById(id);
    }

}// fin clase
