package uz.pdp.app_jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.app_jwt.entity.Income;

import java.util.ArrayList;


@Repository
public interface IncomeRepository extends JpaRepository<Income, Integer> {

    ArrayList<Income> findIncomesByToCardId(Integer toCardId);
}
