package br.com.alunoonline.api.model.dto;

import lombok.Data;

@Data
public class EnderecoViaCepDTO {
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String uf;
}
