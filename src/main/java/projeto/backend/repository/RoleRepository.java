package projeto.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projeto.backend.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
