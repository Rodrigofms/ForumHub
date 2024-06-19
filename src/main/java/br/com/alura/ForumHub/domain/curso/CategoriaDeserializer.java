package br.com.alura.ForumHub.domain.curso;

import br.com.alura.ForumHub.domain.ValidacaoException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CategoriaDeserializer extends JsonDeserializer<Categoria> {
    @Override
    public Categoria deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String value = p.getText().toUpperCase();
        try {
            return Categoria.valueOf(value);
        } catch (IllegalArgumentException e) {
            throw new ValidacaoException("Categoria inválida");
        }
    }
}
