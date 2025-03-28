package com.alex.buscacep.controller;

import com.alex.buscacep.domain.dtos.response.BuscaEnderecoResponseDTO;
import com.alex.buscacep.domain.models.User;
import com.alex.buscacep.infra.service.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@Validated
@RestController
@RequestMapping(value = "/buscacep")
public class EnderecoController {

    @Autowired
    private EnderecoService service;

    @Operation(summary = "Rota responsável por encontrar o endereço do cep informado")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Endereço encontrado com sucesso",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = BuscaEnderecoResponseDTO.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Não foi possível localizar o endereço referente ao cep informado",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }
            )
    })
    @GetMapping(value = "/{cep}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<BuscaEnderecoResponseDTO> buscarEndereco(
            @PathVariable String cep,
            Authentication authentication)
            throws IOException, InterruptedException {
        User usuario = (User) authentication.getPrincipal();
        return ResponseEntity.ok(service.buscaEndereco(cep, usuario));
    }

    @Operation(summary = "Rota responsável por acessar o db e retornar a lista de endereços buscados com a data")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Endereço encontrado com sucesso",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = BuscaEnderecoResponseDTO.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Não foi possível localizar o endereço referente ao cep informado",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }
            )
    })
    @GetMapping
    public ResponseEntity<List<BuscaEnderecoResponseDTO>> buscaCompletaDB(Authentication authentication){
        User usuario = (User) authentication.getPrincipal();
        return ResponseEntity.ok(service.findAll(usuario));
    }
}