package com.forum.perguntas.forumPerguntasAPI.domain.Topico;

public record TopicoDTO(Long id,
                        String titulo,
                        String mensagem,
                        String dataCriacao,
                        EstadoTopico estadoTopico,
                        String autor,
                        String curso
) {
}
