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

    @GetMapping(value={"/lista",""})
    public String listaTecnicos(Model model){
        model.addAttribute("listaTecnicos",tecnicoRepository.findAll());
        return "tecnico/lista";
    }
    @GetMapping("/nuevoTecnico")
    public String nuevoEmpleado(Model model, @ModelAttribute("employee") Employee employee){
        model.addAttribute("listaEmpleado",employeeRepository.findAll());
        model.addAttribute("listaDepartamento",departmentRepository.findAll());
        model.addAttribute("listaPuesto",jobRepository.findAll());
        return "employee/editEmployee";
    }
    @PostMapping("/guardar")
    public String registrarEmpleado(RedirectAttributes attributes, Model model ,
                                    @ModelAttribute("employee") @Valid Tecnico tecnico,
                                    BindingResult bindingResult){
        if(!bindingResult.hasErrors()){
            if(tecnico.getId()==0){
                System.out.println(employee.getFirstName());
                attributes.addFlashAttribute("mensaje","Empleado creado exitosamente");
            }else{
                attributes.addFlashAttribute("mensaje","Empleado actualizado exitosamente");
            }
            employeeRepository.save(employee);

            return "redirect:/employee";

        }else{
            model.addAttribute("employee",employee);
            model.addAttribute("listaEmpleado",employeeRepository.findAll());
            model.addAttribute("listaDepartamento",departmentRepository.findAll());
            model.addAttribute("listaPuesto",jobRepository.findAll());
            return "employee/editEmployee";

        }
        /*if(employee.getId()==0){
            System.out.println(employee.getFirstName());
            attributes.addFlashAttribute("mensaje","Empleado creado exitosamente");
        }else{
            attributes.addFlashAttribute("mensaje","Empleado actualizado exitosamente");
        }
        employeeRepository.save(employee);

        return "redirect:/employee";*/
    }
    @GetMapping("/editar")
    public String editarEmpleado(@ModelAttribute("employee") Employee employee,Model model,@RequestParam("id") int id){
        Optional<Employee> employeeOptional=employeeRepository.findById(id);
        if(employeeOptional.isPresent()){
            employee=employeeOptional.get();
            model.addAttribute("employee",employee);
            model.addAttribute("listaEmpleado",employeeRepository.findAll());
            model.addAttribute("listaDepartamento",departmentRepository.findAll());
            model.addAttribute("listaPuesto",jobRepository.findAll());
            return "employee/editEmployee";
        }else{
            return "redirect:/employee";
        }
    }


    @GetMapping("/borrar")
    public String borrarEmpleado(Model model,@RequestParam("id") int id,
                                 RedirectAttributes attributes){
        Optional<Employee> employeeOptional=employeeRepository.findById(id);
        if(employeeOptional.isPresent()){
            employeeRepository.deleteById(id);
            attributes.addFlashAttribute("mensaje","Empleado borrado exitosamente");
        }
        return "redirect:/employee";

    }

}


}
