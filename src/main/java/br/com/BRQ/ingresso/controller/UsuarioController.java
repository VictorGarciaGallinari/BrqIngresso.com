package br.com.BRQ.ingresso.controller;

import br.com.BRQ.ingresso.domain.exception.EntidadeEmUsoException;
import br.com.BRQ.ingresso.domain.model.Usuario;
import br.com.BRQ.ingresso.domain.repository.UsuarioRepository;
import br.com.BRQ.ingresso.domain.service.CadastroUsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController  //Controller + ResponseBody
@RequestMapping(value = "/challengebrq/v1")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CadastroUsuarioService cadastroUsuario;

    @GetMapping(value = "/usuarios")
    public List<Usuario> listar() {
        return usuarioRepository.findAll();

    }

    @GetMapping(value = "/usuarios/{usuarioId}")
    public ResponseEntity<?> detalhar(@PathVariable("usuarioId") Long usuarioId) {

          Optional<Usuario> usuarioAtual = cadastroUsuario.detalharOuFalhar(usuarioId);

          return ResponseEntity.ok(usuarioAtual);

   }

    @PostMapping(value = "/usuarios")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> cadastrar(@RequestBody @Valid Usuario usuario) {

        usuario = cadastroUsuario.salvar(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }


//    @PutMapping("/usuarios/{usuarioId}")
//    public ResponseEntity<Usuario> atualizar(@PathVariable Long usuarioId, @RequestBody Usuario usuario) {
//
//        Optional<Usuario> usuarioAtual = usuarioRepository.findById(usuarioId);
//
//        if (usuarioAtual.isPresent()) {
//            BeanUtils.copyProperties(usuario, usuarioAtual.get(), "id");
//
//            Usuario usuarioSalvo = cadastroUsuario.salvar(usuarioAtual.get());
//
//            return ResponseEntity.ok(usuarioSalvo);
//
//        }
//
//        return ResponseEntity.notFound().build();
//
//    }

    @DeleteMapping("/usuarios/{usuarioId}")
    public ResponseEntity<?> remover(@PathVariable Long usuarioId) {
        try {
            cadastroUsuario.excluir(usuarioId);
            return ResponseEntity.noContent().build();

//        } catch (EntidadeNaoEncontradaException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

    }

    @PatchMapping("/usuarios/{usuarioId}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Long usuarioId,
                                    @RequestBody Map<String, Object> campos) {

        Optional<Usuario> usuarioAtual = cadastroUsuario.detalharOuFalhar(usuarioId);

        merge(campos, usuarioAtual.get());

        cadastroUsuario.salvar(usuarioAtual.get());

        return ResponseEntity.ok(usuarioAtual);

    }


    private void merge(Map<String, Object> camposOrigem, Usuario usuarioDestino) {
        ObjectMapper objectMapper = new ObjectMapper();
        Usuario usuarioOrigem = objectMapper.convertValue(camposOrigem, Usuario.class);

        camposOrigem.forEach((nomePropriedade, valorPropriedade) -> {
            Field field = ReflectionUtils.findField(Usuario.class, nomePropriedade);
            field.setAccessible(true);

            Object novoValor = ReflectionUtils.getField(field, usuarioOrigem);
            System.out.println(nomePropriedade + " = " + valorPropriedade);
            ReflectionUtils.setField(field, usuarioDestino, novoValor);
        });
    }


    @PutMapping("/usuarios/{usuarioId}/senhas")
    public ResponseEntity<Usuario> alterarSenhaUsuario(@PathVariable Long usuarioId, @RequestBody Usuario usuario) {
        Optional<Usuario> usuarioAtual = usuarioRepository.findById(usuarioId);

        String senha = usuario.getSenha();

        if (usuarioAtual.isPresent()) {
            usuario = usuarioAtual.get();
            usuario.setSenha(senha);
            BeanUtils.copyProperties(usuario, usuarioAtual.get(), "id", "formaPagamento",
                    "endereco","dataCadastro");
            cadastroUsuario.salvar(usuarioAtual.get());
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

}