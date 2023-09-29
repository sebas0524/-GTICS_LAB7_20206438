package com.example.lab07_20206438.Controllers;


import com.example.lab07_20206438.Entity.Tecnico;
import com.example.lab07_20206438.Repository.TecnicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/tecnico")
public class ControllerTecnico {


    final
    TecnicoRepository tecnicoRepository;

    public ControllerTecnico(TecnicoRepository tecnicoRepository) {
        this.tecnicoRepository = tecnicoRepository;
    }

    @GetMapping(value = {"/lista", ""})
    public String listaTecnicos(Model model) {
        model.addAttribute("listaTecnicos", tecnicoRepository.findAll());
        return "tecnico/lista";
    }

    @GetMapping("/nuevoTecnico")
    public String nuevoTecnico(Model model, @ModelAttribute("employee") Tecnico tecnico) {
        model.addAttribute("listaTecnicos", tecnicoRepository.findAll());
        return "tecnico/editForm";
    }

    @PostMapping("/guardar")
    public String registrarTecnico(RedirectAttributes attributes, Model model,
                                   @ModelAttribute("tecnico") @Valid Tecnico tecnico,
                                   BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            if (tecnico.getIdTecnico() == 0) {
                attributes.addFlashAttribute("mensaje", "Tenico" + tecnico.getFirstName() + tecnico.getLastName() + "creado exitosamente");
            } else {
                attributes.addFlashAttribute("mensaje", "Tecnico" + tecnico.getFirstName() + tecnico.getLastName() + "actualizado exitosamente");
            }
            tecnicoRepository.save(tecnico);

            return "redirect:/tecnico";

        } else {
            model.addAttribute("employee", tecnico);
            model.addAttribute("listaTecnico", tecnicoRepository.findAll());
            return "tecnico/editForm";

        }
    }

    @GetMapping("/editar")
    public String editarTecnico(@ModelAttribute("tecnico") Tecnico tecnico, Model model, @RequestParam("id") int id) {
        Optional<Tecnico> tecnicoOptional = tecnicoRepository.findById(id);
        if (tecnicoOptional.isPresent()) {
            tecnico = tecnicoOptional.get();
            model.addAttribute("tecnico", tecnico);
            model.addAttribute("listaTecnico", tecnicoRepository.findAll());
            return "tecnico/editForm";
        } else {
            return "redirect:/tecnico";
        }
    }
}




