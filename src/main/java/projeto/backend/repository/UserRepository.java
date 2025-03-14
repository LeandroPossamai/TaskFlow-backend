package projeto.backend.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import projeto.backend.entities.Usuario;

@Repository
public interface UserRepository extends JpaRepository<Usuario, UUID> {
  Optional<Usuario> findByUsername(String username);

  Optional<Usuario> findByEmail(String email);

}
