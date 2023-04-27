package tn.esprit.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.DAO.Entities.Propriete;

public interface ProprieteRepo extends JpaRepository<Propriete, Long> {
}
