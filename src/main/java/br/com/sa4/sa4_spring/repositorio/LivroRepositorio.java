package br.com.sa4.sa4_spring.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.sa4.sa4_spring.modelo.Livro;

public interface LivroRepositorio extends JpaRepository<Livro, Long> {

}