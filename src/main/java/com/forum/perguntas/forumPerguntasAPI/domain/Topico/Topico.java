package com.forum.perguntas.forumPerguntasAPI.domain.Topico;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

@Table(name = "topicos")
@Entity(name = "topico")
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensagem;
    @Column(name = "dataCriacao")
    private String dataCriacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "estadoTopico")
    private EstadoTopico estadoTopico;
    private String autor;
    private String curso;

    public Topico() {}

    public Topico(Long id, String titulo, String mensagem, String dataCriacao, EstadoTopico estadoTopico, String autor, String curso) {
        this.id = id;
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.dataCriacao = dataCriacao;
        this.estadoTopico = estadoTopico;
        this.autor = autor;
        this.curso = curso;
    }

    public Topico(DadosCriacaoTopico dados) {
        this(null, dados.titulo(), dados.mensagem(), "2000", EstadoTopico.ATIVO, dados.autor(), dados.curso());
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public EstadoTopico getEstadoTopico() {
        return estadoTopico;
    }

    public String getAutor() {
        return autor;
    }

    public String getCurso() {
        return curso;
    }

    public void atualizar(DadosCriacaoTopico dados) {
        if (dados.titulo() != null) titulo = dados.titulo();
        if (dados.autor() != null) autor = dados.autor();
        if (dados.curso() != null) curso = dados.curso();
        if (dados.mensagem() != null) mensagem = dados.mensagem();
    }
}
