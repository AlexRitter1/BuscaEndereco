package com.alex.buscacep.domain.dtos.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EnderecoRequestDTO {

    @NotBlank(message = "O CEP é obrigatório")
    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;
    private String ddd;

    public EnderecoRequestDTO(String cep, String logradouro, String bairro, String localidade, String uf, String ddd) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.localidade = localidade;
        this.uf = uf;
        this.ddd = ddd;
    }

    public String getCep() {
        return cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public String getUf() {
        return uf;
    }

    public String getDdd() {
        return ddd;
    }

}