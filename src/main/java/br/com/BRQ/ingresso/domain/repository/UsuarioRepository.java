package br.com.BRQ.ingresso.domain.repository;

import br.com.BRQ.ingresso.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> findTodosByNomeContaining(String nome);

    Optional<Usuario> findByNome(String nome);

}
