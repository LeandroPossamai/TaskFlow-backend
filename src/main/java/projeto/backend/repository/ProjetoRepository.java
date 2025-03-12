package projeto.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto.backend.entities.Projeto;
import java.util.Optional;
import java.util.UUID;

public interface ProjetoRepository extends JpaRepository<Projeto, UUID> {
  Optional<Projeto> findByNome(String nome);
}