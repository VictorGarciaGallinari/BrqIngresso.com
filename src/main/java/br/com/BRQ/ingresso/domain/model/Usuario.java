package br.com.BRQ.ingresso.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Usuario {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String cpf;

    @NotEmpty
    private String email;

    @NotEmpty
    private String nome;

    @NotEmpty
    private String senha;

    @NotEmpty
    private String apelido;

//    @Column(nullable = true)
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
//    private LocalDate dataNascimento;

    @NotNull
    private Integer celular;

    @NotNull
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


}
