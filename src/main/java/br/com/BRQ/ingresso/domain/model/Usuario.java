package br.com.BRQ.ingresso.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Usuario {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String email;

    @NotNull
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

    @JsonIgnore
    @CreationTimestamp
    private LocalDateTime dataCadastro;

    @JsonIgnore
    @UpdateTimestamp
    private LocalDateTime dataAtualizacao;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

////    @JsonIgnore
//    @Embedded
//    private Endereco endereco;

//    @JsonIgnore
//    @OneToMany(mappedBy = "usuario")
//    private List<Endereco> enderecos = new ArrayList<>();




}
