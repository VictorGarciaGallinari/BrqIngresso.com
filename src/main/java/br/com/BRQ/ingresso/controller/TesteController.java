package br.com.BRQ.ingresso.controller;

import br.com.BRQ.ingresso.domain.model.Usuario;
import br.com.BRQ.ingresso.domain.repository.EnderecoRepository;
import br.com.BRQ.ingresso.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teste")
public class TesteController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @GetMapping("/usuarios/por-nome")
    public List<Usuario> usuariosPorNome(@RequestParam("nome") String nome) {
        return usuarioRepository.findTodosByNomeContaining(nome);
    }


    @GetMapping("/usuarios/unico-por-nome")
    public Optional<Usuario> usuarioPorNome(@RequestParam("nome") String nome) {
        return usuarioRepository.findByNome(nome);
    }
}
