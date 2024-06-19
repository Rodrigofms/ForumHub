package br.com.alura.ForumHub.controller;

import br.com.alura.ForumHub.domain.ValidacaoException;
import br.com.alura.ForumHub.domain.topico.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
@Tag(name = "Tópicos - Necessita autenticar")
public class TopicoController {

    @Autowired
    TopicoService service;

    @PostMapping
    @Transactional
    @Operation(summary = "Criar", description = "Cria nova tópico")
    public ResponseEntity criar(@RequestBody @Valid DadosTopico dados, UriComponentsBuilder uriBuilder){

        var topicoSalvo = service.criar(dados);
        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topicoSalvo.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhadosTopico(topicoSalvo));
    }

    @GetMapping
    @Operation(summary = "Listar", description = "Lista todos tópicos")
    public ResponseEntity<List<Topico>> listar(){
        var todos = service.listar();
        return ResponseEntity.ok(todos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Detalhar", description = "Lista tópico com determinado id")
    public ResponseEntity<Topico> listar(@PathVariable Long id){
        Optional<Topico> topicoOptional = service.detalhar(id);

        if (topicoOptional.isPresent()) {
            Topico topico = topicoOptional.get();
            return ResponseEntity.ok(topico);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Atualizar", description = "Atualiza tópico com determinado id")
    public ResponseEntity<Topico> atualizar(@PathVariable Long id, @RequestBody @Valid DadosTopico dados) {
            Topico topicoAtualizado = service.atualizar(id, dados);
            return ResponseEntity.ok(topicoAtualizado); // Return 200 OK with updated Topico object
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Apagar", description = "Apaga tópico com determinado id")
    public ResponseEntity<Topico> apagar(@PathVariable Long id) {
        service.apagar(id);
        return ResponseEntity.noContent().build();
    }
}
