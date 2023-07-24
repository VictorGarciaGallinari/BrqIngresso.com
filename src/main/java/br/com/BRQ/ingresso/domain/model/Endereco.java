package br.com.BRQ.ingresso.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Endereco {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String logradouro;

    @NotEmpty
    private String numero;

    @NotEmpty
    private String bairro;

    @NotBlank
    private String cidade;

    @NotEmpty
    private String estado;

    @NotEmpty
    private String pais;

    @NotEmpty
    private String cep;

}
