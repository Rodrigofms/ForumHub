package br.com.alura.ForumHub.domain.topico.validadores;

import br.com.alura.ForumHub.domain.ValidacaoException;
import br.com.alura.ForumHub.domain.curso.Categoria;
import br.com.alura.ForumHub.domain.topico.DadosTopico;
import org.springframework.stereotype.Component;

@Component
public class ValidadorCategoriaCurso implements ValidadoresTopico{

    @Override
    public void validar(DadosTopico dadosTopico) {
        var categoriaCurso = dadosTopico.curso().getCategoria();

        if (categoriaCurso == null) {
            throw new ValidacaoException("Categoria não pode estar vazia");
        }

    }
}
