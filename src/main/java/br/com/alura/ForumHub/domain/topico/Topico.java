package br.com.alura.ForumHub.domain.topico;


import br.com.alura.ForumHub.domain.curso.Curso;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "Topico")
@Table(name = "topicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime data;
    private Boolean ativo;

    @Embedded
    private Curso curso;

    public Topico(DadosTopico dados) {
        this.ativo = true;
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();
        this.data = dados.data();
        this.curso = dados.curso();
    }


    public void atualizar(DadosTopico dados) {
        if (dados.titulo()!=null){
            this.titulo = dados.titulo();
        }
        if (dados.mensagem()!=null){
            this.mensagem = dados.mensagem();
        }
        if (dados.data()!=null){
            this.data = dados.data();
        }
        if (dados.curso()!=null){
            this.titulo = dados.titulo();
        }
    }
}
