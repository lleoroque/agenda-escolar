package com.agenda.agenda_escolar.service;

import com.agenda.agenda_escolar.model.Usuario;
import com.agenda.agenda_escolar.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        System.out.println("PROCURANDO USUARIO: " + email);

        Usuario usuario = usuarioRepository.findByEmail(email);

        if(usuario == null){
            throw new UsernameNotFoundException("Usuário não encontrado");

        }
        System.out.println("EMAIL: " + usuario.getEmail());
        System.out.println("SENHA BANCO: " + usuario.getSenha());

        return User.builder()
                .username(usuario.getEmail())
                .password(usuario.getSenha())
                .roles("USER")
                .build();
    }
}