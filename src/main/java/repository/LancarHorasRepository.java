package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projeto.entities.LancarHoras;

@Repository
public interface LancarHorasRepository extends JpaRepository<LancarHoras, Long> {

}
