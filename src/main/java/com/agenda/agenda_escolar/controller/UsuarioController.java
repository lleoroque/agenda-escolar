package com.agenda.agenda_escolar.controller;

import com.agenda.agenda_escolar.model.Usuario;
import com.agenda.agenda_escolar.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;


@Controller
public class UsuarioController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/cadastro")
    public String cadastro(Model model){

        model.addAttribute("usuario", new Usuario());

        return "cadastro";
    }
    @GetMapping("/login")
    public String login() {

        return "login";
    }

    @PostMapping("/cadastro")
    public String salvarUsuario(@ModelAttribute Usuario usuario){

        usuario.setSenha(
                passwordEncoder.encode(usuario.getSenha())
        );

        usuarioRepository.save(usuario);

        return "redirect:/login";
    }
}