package repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projeto.entities.Usuario;

@Repository
public interface UserRepository extends JpaRepository<Usuario, UUID> {

}
