package com.forum.perguntas.forumPerguntasAPI.domain.Topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCriacaoTopico(
        @NotBlank
        String titulo,
        @NotNull
        String mensagem,
        @NotBlank
        String autor,
        @NotBlank
        String curso ) {
}
