package com.unkflix.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record StreamingRequest(@NotBlank(message = "Nome da categoria é obrigatório") String name) {
}
