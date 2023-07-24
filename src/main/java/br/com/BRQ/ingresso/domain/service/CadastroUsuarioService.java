package br.com.BRQ.ingresso.domain.service;

import br.com.BRQ.ingresso.domain.exception.EntidadeEmUsoException;
import br.com.BRQ.ingresso.domain.exception.EntidadeNaoEncontradaException;
import br.com.BRQ.ingresso.domain.exception.UsuarioNaoEncontradoException;
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

    private static final String MSG_USUARIO_NAO_ENCONTRADO
            = "Não existe um cadastro de usuario com o codigo %d";

    private static final String MSG_USUARIO_EM_USO
            = "Usuario de código %d não pode ser removida, pois está em uso ";

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);

    }

    public void excluir(Long usuarioId) {

    try {
        usuarioRepository.deleteById(usuarioId);

    } catch (EmptyResultDataAccessException e) {
        throw new UsuarioNaoEncontradoException(usuarioId);

    } catch (DataIntegrityViolationException e) {
        throw new EntidadeEmUsoException(
                String.format(MSG_USUARIO_EM_USO, usuarioId));
    }
}

    public Optional<Usuario> detalharOuFalhar (Long usuarioId) {
        return Optional.ofNullable(usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format(MSG_USUARIO_NAO_ENCONTRADO, usuarioId))));

    }

}
