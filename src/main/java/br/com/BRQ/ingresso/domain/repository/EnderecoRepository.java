package br.com.BRQ.ingresso.domain.repository;

import br.com.BRQ.ingresso.domain.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

 //   List<Usuario> consultarPorNome(String nome);

}
