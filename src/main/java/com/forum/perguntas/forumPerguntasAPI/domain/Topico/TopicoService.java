package com.forum.perguntas.forumPerguntasAPI.domain.Topico;

import java.util.List;

public class TopicoService {
    public boolean verificarTopicoDuplicado(List<Topico> topicoList, DadosCriacaoTopico novoTopico) {
        return topicoList.stream()
                .filter(t -> t.getTitulo().equalsIgnoreCase(novoTopico.titulo()))
                .anyMatch(t -> t.getMensagem().equalsIgnoreCase(novoTopico.mensagem()));
    }
}
