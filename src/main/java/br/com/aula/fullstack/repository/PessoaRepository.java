package br.com.aula.fullstack.repository;

import br.com.aula.fullstack.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
