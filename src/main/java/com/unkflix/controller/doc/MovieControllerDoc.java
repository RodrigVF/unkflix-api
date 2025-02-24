package com.unkflix.controller.doc;

import com.unkflix.controller.request.MovieRequest;
import com.unkflix.controller.response.MovieResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Movie", description = "Recurso responsável pelo gerenciamento dos filmes.")
public interface MovieControllerDoc {

    @Operation(
            summary = "Listar todos os filmes",
            description = "Método responsável por retornar todos os filmes cadastrados",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(
            responseCode = "200",
            description = "Retorna todos os filmes cadastrados.",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = MovieResponse.class))))
    public ResponseEntity<List<MovieResponse>> findAll();


    @Operation(
            summary = "Buscar filme por ID",
            description = "Método responsável por buscar um filme pelo seu ID",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(
            responseCode = "200",
            description = "Filme encontrado com sucesso!",
            content = @Content(schema = @Schema(implementation = MovieResponse.class)))
    @ApiResponse(
            responseCode = "404",
            description = "Filme não encontrado.",
            content = @Content())
    public ResponseEntity<MovieResponse> findById(@PathVariable Long id);


    @Operation(
            summary = "Buscar filme por categoria",
            description = "Método responsável por buscar uma lista de filmes por sua categoria",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(
            responseCode = "200",
            description = "Filmes encontrados com sucesso!",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = MovieResponse.class))))
    public ResponseEntity<List<MovieResponse>> findByCategory(@RequestParam Long category);


    @Operation(
            summary = "Salvar filme",
            description = "Método responsável por salvar um novo filme",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(
            responseCode = "201",
            description = "Filme salvo com sucesso!",
            content = @Content(schema = @Schema(implementation = MovieResponse.class)))
    public ResponseEntity<MovieResponse> save(@Valid @RequestBody MovieRequest request);


    @Operation(
            summary = "Alterar filme",
            description = "Método responsável por alterar dados de um filme",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(
            responseCode = "201",
            description = "Filme alterado com sucesso!",
            content = @Content(schema = @Schema(implementation = MovieResponse.class)))
    @ApiResponse(
            responseCode = "404",
            description = "Filme não encontrado.",
            content = @Content())
    public ResponseEntity<MovieResponse> update(@PathVariable Long id, @Valid @RequestBody MovieRequest request);


    @Operation(
            summary = "Deletar filme por ID",
            description = "Método responsável por deletar um filme pelo seu ID",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(
            responseCode = "204",
            description = "Filme deletado com sucesso!",
            content = @Content())
    @ApiResponse(
            responseCode = "404",
            description = "Filme não encontrado.",
            content = @Content())
    public ResponseEntity<Void> delete(@PathVariable Long id);
}
