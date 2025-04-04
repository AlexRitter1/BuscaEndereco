package com.alex.buscacep.infra.service;
import com.alex.buscacep.domain.dtos.request.EnderecoRequestDTO;
import com.alex.buscacep.domain.dtos.request.ViaCepRequestErrorDTO;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;
import static org.assertj.core.api.Assertions.assertThat;

class CepServiceTest {

    private final RestTemplate restTemplate = new RestTemplate();

    @Test
    void getViaCepCase1() {
        String url = "https://viacep.com.br/ws/24900435/json/";
        var endereco = (restTemplate.getForObject(url, EnderecoRequestDTO.class));
        assertThat(endereco.getCep()).isEqualTo("24900-435");
    }

    @Test
    void getViaCepCase2() {
        String url = "https://viacep.com.br/ws/00000000/json/";
        var result = (restTemplate.getForObject(url, EnderecoRequestDTO.class));
        assertThat(result.getCep()).isEqualTo(null);
    }
}