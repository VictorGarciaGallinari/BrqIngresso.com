package br.com.BRQ.ingresso.domain.service;

import br.com.BRQ.ingresso.domain.exception.EntidadeEmUsoException;
import br.com.BRQ.ingresso.domain.exception.EntidadeNaoEncontradaException;
import br.com.BRQ.ingresso.domain.model.Endereco;
import br.com.BRQ.ingresso.domain.model.Usuario;
import br.com.BRQ.ingresso.domain.repository.EnderecoRepository;
import br.com.BRQ.ingresso.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CadastroUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;
    public Usuario salvar(Usuario usuario) {
        Long enderecoId = usuario.getEndereco().getId();
        Optional<Endereco> endereco = enderecoRepository.findById(enderecoId);

        usuario.setEndereco(endereco.get());

        return usuarioRepository.save(usuario);

    }

    public void excluir(Long usuarioId) {

        try {
            usuarioRepository.deleteById(usuarioId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de usuario com o codigo %d", usuarioId));

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Usuario de código %d não pode ser removida, pois está em uso ", usuarioId));
       }
    }

}
