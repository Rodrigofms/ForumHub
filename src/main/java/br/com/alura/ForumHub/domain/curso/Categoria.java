package br.com.alura.ForumHub.domain.curso;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@JsonDeserialize(using = CategoriaDeserializer.class)
public enum Categoria {
    TECNOLOGIA,
    ARTES,
    ADMINISTRACAO;

}
