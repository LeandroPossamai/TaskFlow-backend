package projeto.backend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projeto.backend.entities.Projeto;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, UUID> {

}
