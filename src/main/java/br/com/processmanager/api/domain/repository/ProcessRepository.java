package br.com.processmanager.api.domain.repository;

import br.com.processmanager.api.domain.entity.Process;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Anotação que marca esta interface como um componente de repositório do Spring
public interface ProcessRepository extends JpaRepository<Process, Long> {
}