package br.com.alura.ForumHub.domain.topico.validadores;

import br.com.alura.ForumHub.domain.ValidacaoException;
import br.com.alura.ForumHub.domain.topico.DadosTopico;
import org.springframework.stereotype.Component;

@Component
public class ValidadorNomeCurso implements ValidadoresTopico{

    @Override
    public void validar(DadosTopico dadosTopico) {
        var nomeCurso = dadosTopico.curso().getNomeCurso();
        if(nomeCurso.isEmpty()){
            throw new ValidacaoException("Nome do curso não pode estar vazio");
        }

    }
}
