package uz.pdp.app_jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.app_jwt.entity.Outcome;

import java.util.ArrayList;

@Repository
public interface OutcomeRepository extends JpaRepository<Outcome, Integer> {

    ArrayList<Outcome> findOutcomesByToCardId(Integer toCardId);
}
