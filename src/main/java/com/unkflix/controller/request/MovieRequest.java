package com.unkflix.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record MovieRequest(
        @Schema(type = "string", description = "Título do filme", defaultValue = "De volta para o futuro")
        @NotBlank(message = "Título do filme é obrigatório")
        String title,

        @Schema(type = "string", description = "Descrição do filme",
                defaultValue = "Um jovem aciona acidentalmente uma máquina do tempo construída por um cientista em um Delorean, retornando aos anos 50.")
        String description,

        @Schema(type = "date", description = "Data de lançamento do filme. ex: '25/12/1985'", defaultValue = "25/12/1985")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate releaseDate,

        @Schema(type = "double", description = "Score do filme. ex: 9.3", defaultValue = "9.3")
        double rating,

        @Schema(type = "array", description = "Lista de códigos(IDs) de categorias", defaultValue = "[3,4]")
        List<Long> categories,

        @Schema(type = "array", description = "Lista de códigos(IDs) de serviços de streaming", defaultValue = "[1,3]")
        List<Long> streamings) {
}
