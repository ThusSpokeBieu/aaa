package br.edu.lbt.aaa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.lbt.aaa.models.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
}
