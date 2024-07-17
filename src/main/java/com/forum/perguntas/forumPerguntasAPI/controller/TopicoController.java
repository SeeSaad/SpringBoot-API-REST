package com.forum.perguntas.forumPerguntasAPI.controller;

import com.forum.perguntas.forumPerguntasAPI.domain.Topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("topicos")
public class TopicoController {

    @Autowired
    TopicoRepository repository;

    TopicoService service = new TopicoService();

    @PostMapping
    @Transactional
    public ResponseEntity adicionarTopico(@RequestBody @Valid DadosCriacaoTopico dados) {
        if (service.verificarTopicoDuplicado(repository.findAll(), dados)) {
            return ResponseEntity.badRequest().body("Duplicated Request");
        }
        var topico = new Topico(dados);
        repository.save(topico);
        return ResponseEntity.ok(dados);
    }

    @GetMapping
    public ResponseEntity<Page<TopicoDTO>> listarTopicos(@PageableDefault(size = 10, sort = {"curso"}) Pageable paginacao) {
        var page = repository.findAll(paginacao)
                .map(t -> new TopicoDTO(t.getId(), t.getTitulo(), t.getMensagem(), t.getDataCriacao(), t.getEstadoTopico(), t.getAutor(), t.getCurso()));
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity pegarTopico(@PathVariable Long id) {
        var element = repository.findById(id);

        if (element.isEmpty()) return ResponseEntity.badRequest().body("No such element");

        var topico = element.get();
        var dto = new TopicoDTO(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao(), topico.getEstadoTopico(), topico.getAutor(), topico.getCurso());

        return ResponseEntity.ok().body(dto);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity atualizarTopico(@PathVariable Long id, @RequestBody @Valid DadosCriacaoTopico dados) {
        var element = repository.findById(id);

        if (element.isEmpty()) return ResponseEntity.badRequest().body("No such element");
        if (service.verificarTopicoDuplicado(repository.findAll(), dados)) return ResponseEntity.badRequest().body("Duplicated element");

        var topico = element.get();
        topico.atualizar(dados);

        var dto = new TopicoDTO(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao(), topico.getEstadoTopico(), topico.getAutor(), topico.getCurso());

        return ResponseEntity.accepted().body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarTopico(@PathVariable Long id) {
        var element = repository.findById(id);

        if (element.isEmpty()) return ResponseEntity.badRequest().body("No such element");

        repository.deleteById(id);

        return ResponseEntity.accepted().body("Transaction succsessful");
    }
}
