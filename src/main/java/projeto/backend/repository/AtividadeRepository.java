package projeto.backend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projeto.backend.entities.Atividade;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade, UUID> {

}
