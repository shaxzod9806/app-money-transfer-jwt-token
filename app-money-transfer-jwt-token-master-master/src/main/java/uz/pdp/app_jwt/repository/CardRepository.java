package uz.pdp.app_jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.app_jwt.entity.Card;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {

    Optional<Card> findByNumber(String number);

    Optional<ArrayList<Card>> findByUsername(String username);

    boolean existsByNumber(String number);

}
