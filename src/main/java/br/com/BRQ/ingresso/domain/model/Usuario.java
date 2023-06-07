package br.com.BRQ.ingresso.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Usuario {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/mm/dd");

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private String apelido;

//    @Column(nullable = true)
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
//    private LocalDate dataNascimento;

    @Column(nullable = false)
    private Integer celular;

    @Column(nullable = false)
    private Integer sexo;

//    @Column(nullable = false)
//    private LocalDateTime dataCadastro;
//
//    private LocalDateTime dataAtualizacao;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id" , nullable = false)
    private Endereco endereco;


}
