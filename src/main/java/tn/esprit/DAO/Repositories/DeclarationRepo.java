package tn.esprit.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.DAO.Entities.Declaration;

public interface DeclarationRepo extends JpaRepository<Declaration, Long> {
}
