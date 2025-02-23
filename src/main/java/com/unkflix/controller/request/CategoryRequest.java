package com.unkflix.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record CategoryRequest(@NotBlank(message = "Nome da categoria é obrigatório") String name) {
}
