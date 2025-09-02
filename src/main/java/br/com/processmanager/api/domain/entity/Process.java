package br.com.processmanager.api.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data // Anotação do Lombok para criar Getters, Setters, etc. automaticamente
@Entity // Marca esta classe como uma entidade JPA (uma tabela no banco)
@Table(name = "processes") // Define o nome exato da tabela no banco de dados
public class Process {

    @Id // Marca este campo como a chave primária da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configura o ID para ser autoincrementado pelo banco
    private Long id;

    private String name;
    private String description;

    // Define o relacionamento: Um Processo tem Muitas Tarefas (Tasks)
    @OneToMany(mappedBy = "process", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Task> tasks;
}