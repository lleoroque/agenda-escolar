package com.agenda.agenda_escolar.controller;

import com.agenda.agenda_escolar.model.Tarefa;
import com.agenda.agenda_escolar.repository.TarefaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.agenda.agenda_escolar.model.Usuario;
import com.agenda.agenda_escolar.repository.UsuarioRepository;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
public class TarefaController {

    @Autowired
    private TarefaRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/")
    public String listar(Model model){

        Authentication auth =
                SecurityContextHolder.getContext().getAuthentication();

        String email = auth.getName();

        Usuario usuario =
                usuarioRepository.findByEmail(email);

        model.addAttribute(
                "tarefas",
                repository.findByUsuario(usuario)
        );

        model.addAttribute(
                "nomeUsuario",
                usuario.getNome()
        );

        return "index";
    }
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Tarefa tarefa){

        Authentication auth =
                SecurityContextHolder.getContext().getAuthentication();

        String email = auth.getName();

        Usuario usuario =
                usuarioRepository.findByEmail(email);

        tarefa.setUsuario(usuario);

        tarefa.setStatus("Pendente");

        repository.save(tarefa);

        return "redirect:/";
    }
    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id){

        repository.deleteById(id);

        return "redirect:/";
    }

    @GetMapping("/concluir/{id}")
    public String concluir(@PathVariable Long id){

        Tarefa tarefa = repository.findById(id).orElse(null);

        if(tarefa != null){

            tarefa.setStatus("Concluído");

            repository.save(tarefa);
        }

        return "redirect:/";
    }
}