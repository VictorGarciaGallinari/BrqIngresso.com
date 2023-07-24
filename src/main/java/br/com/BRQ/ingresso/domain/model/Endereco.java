package br.com.BRQ.ingresso.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Endereco {

//    @EqualsAndHashCode.Include

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String logradouro;

    @Column(nullable = false)
    private String numero;

    @Column(nullable = false)
    private String bairro;

    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private String pais;

    @Column(nullable = false)
    private String cep;




//    @Valid
//    @NotNull
//    @ManyToOne
//    @JoinColumn(name = "usuario_id", nullable = false)
//    private Usuario usuario;

//    @JsonIgnore
//    @Embedded
//    private Endereco2Alga endereco;
//
//    private LocalDateTime dataCadastro;
//
//    private LocalDateTime dataAtualizacao;
//
//    @JsonIgnore
//    @ManyToMany
//    @JoinTable(name = "endereco_forma_pagamento",
//                joinColumns = @JoinColumn(name = "endereco_id"))
//    private List<FormaPagamento> formasPagamento = new ArrayList<>();


}
