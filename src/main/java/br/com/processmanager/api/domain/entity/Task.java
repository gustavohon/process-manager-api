package br.com.processmanager.api.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int executionOrder; // A ordem em que a tarefa deve ser executada
    private String details;

    // Define o relacionamento: Muitas Tarefas pertencem a Um Processo
    @ManyToOne
    @JoinColumn(name = "process_id") // Cria a coluna de chave estrangeira 'process_id' na tabela 'tasks'
    private Process process;
}