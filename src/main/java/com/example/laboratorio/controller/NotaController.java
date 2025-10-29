package com.example.laboratorio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.laboratorio.model.Nota;
import com.example.laboratorio.service.NotaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/notas")
public class NotaController {

    private final NotaService notaService;

    public NotaController(NotaService notaService) {
        this.notaService = notaService;
    }

    @GetMapping
    public String listarTodas(Model model) {
        model.addAttribute("notas", notaService.listarTodas());
        return "notas/lista";

    }

    @GetMapping("/nuevo")
    public String crearNota(Model model) {
        model.addAttribute("nota", new Nota());
        model.addAttribute("importancias", Nota.Importancia.values());
        return "notas/form";

    }

    @PostMapping
    public String guardar(@Valid @ModelAttribute("nota") Nota nota,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("importancias", Nota.Importancia.values());
            return "notas/form";
        }
        notaService.crear(nota);
        return "redirect:/notas";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable("id") Long id, Model model, RedirectAttributes ra) {
        var opt = notaService.buscarPorId(id);
        if (opt.isEmpty()) {
            ra.addFlashAttribute("msg", "Nota no encontrada" + "con id: " + id);
            return "redirect:/notas";
        }
        model.addAttribute("nota", opt.get());
        model.addAttribute("importancias", Nota.Importancia.values());
        return "notas/form";

    }

    @PostMapping("/{id}/actualizar")
    public String actualizar(@PathVariable("id") Long id,
            @Valid @ModelAttribute("nota") Nota nota,
            BindingResult result,
            RedirectAttributes ra,
            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("importancias", Nota.Importancia.values());
            return "notas/form";
        }
        notaService.actualizar(id, nota);
        ra.addFlashAttribute("msg", "Nota Actualizada!");
        return "redirect:/notas";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id, RedirectAttributes ra) {
        notaService.eliminar(id);
        ra.addFlashAttribute("msg", "Nota Eliminada!");
        return "redirect:/notas";
    }

}// fin class
