package br.com.processmanager.api.controller;

import br.com.processmanager.api.domain.entity.Process;
import br.com.processmanager.api.domain.repository.ProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Anotação que diz ao Spring que esta classe vai lidar com requisições web e retornar JSON
@RequestMapping("/api/processes") // Define a URL base para todos os endpoints neste controller
public class ProcessController {

    @Autowired // Injeção de Dependência: O Spring vai nos dar uma instância pronta do ProcessRepository
    private ProcessRepository processRepository;

    // Endpoint para CRIAR um novo processo (HTTP POST)
    @PostMapping
    public ResponseEntity<Process> createProcess(@RequestBody Process process) {
        // Garante a relação bidirecional para o JPA salvar corretamente
        if (process.getTasks() != null) {
            process.getTasks().forEach(task -> task.setProcess(process));
        }
        Process savedProcess = processRepository.save(process);
        return new ResponseEntity<>(savedProcess, HttpStatus.CREATED); // Retorna o processo salvo e o status 201 CREATED
    }

    // Endpoint para LISTAR TODOS os processos (HTTP GET)
    @GetMapping
    public List<Process> getAllProcesses() {
        return processRepository.findAll();
    }

    // Endpoint para BUSCAR UM processo pelo seu ID (HTTP GET)
    @GetMapping("/{id}")
    public ResponseEntity<Process> getProcessById(@PathVariable Long id) {
        return processRepository.findById(id)
                .map(ResponseEntity::ok) // Se encontrar, retorna o processo com status 200 OK
                .orElse(ResponseEntity.notFound().build()); // Se não encontrar, retorna status 404 NOT FOUND
    }
}