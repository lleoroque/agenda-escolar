package com.agenda.agenda_escolar.repository;

import com.agenda.agenda_escolar.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository
        extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(String email);
}