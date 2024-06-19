package br.com.alura.ForumHub.domain.topico;

import br.com.alura.ForumHub.domain.ValidacaoException;
import br.com.alura.ForumHub.domain.topico.validadores.ValidadoresTopico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository repository;

    @Autowired
    private List<ValidadoresTopico> validadores;


    public Topico criar(DadosTopico dados) {

        validadores.forEach(v -> v.validar(dados));

        var topicoNovo = new Topico(null, dados.titulo(), dados.mensagem(), dados.data(), true, dados.curso());

        repository.save(topicoNovo);

        return topicoNovo;
    }

    public List<Topico> listar() {
        return repository.findAll();
    }

    public Optional<Topico> detalhar(Long id) {
        return repository.findById(id);
    }


    public Topico atualizar(Long id, DadosTopico dados) {
        Optional<Topico> topicoOptional = repository.findById(id);

        if (topicoOptional.isPresent()) {
            validadores.forEach(v -> v.validar(dados));
            Topico topico = topicoOptional.get();
            topico.atualizar(dados);
            repository.save(topico);
            return topico;
        }
        else{
            throw new ValidacaoException("Tópico com esse id não encontrado");
        }
    }

    public void apagar(Long id) {
        Optional<Topico> topicoOptional = repository.findById(id);

        if (topicoOptional.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new ValidacaoException("Tópico com esse id não encontrado");
        }
    }
}
