package com.agenda.agenda_escolar.repository;

import com.agenda.agenda_escolar.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import com.agenda.agenda_escolar.model.Usuario;
import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    List<Tarefa> findByUsuario(Usuario usuario);
}