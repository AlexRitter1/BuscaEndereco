package com.alex.buscacep.infra.service;

import com.alex.buscacep.domain.models.Busca;
import com.alex.buscacep.domain.models.Endereco;
import com.alex.buscacep.domain.models.User;
import com.alex.buscacep.infra.repositories.BuscaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class SalvarBuscaService {

    @Autowired
    private BuscaRepository buscaRepository;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public Busca salvarBusca(Endereco endereco, User user) {
        log.info("Método salvarBusca chamado.");
        var busca = new Busca(LocalDateTime.now(), endereco, user);
        buscaRepository.save(busca);
        log.info("Instância de busca com data/hora, endereço e user criada e setada corretamente e salva no banco de dados.");
        return busca;
    }

    public Busca salvarBusca(User user){
        log.info("Método salvarBusca chamado.");
        var busca = new Busca(LocalDateTime.now(), user);
        buscaRepository.save(busca);
        log.info("Instância de busca com data/hora e user criada e setada corretamente e salva no banco de dados.");
        return busca;
    }
}
