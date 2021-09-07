package uz.pdp.app_jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.app_jwt.entity.Card;
import uz.pdp.app_jwt.payload.ApiResponse;
import uz.pdp.app_jwt.repository.CardRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CardService {
    @Autowired
    CardRepository cardRepository;

    public ApiResponse addCard(Card card) {
        boolean existsByNumber = cardRepository.existsByNumber(card.getNumber());
        if (existsByNumber)
            return new ApiResponse("This card already added", false);

        cardRepository.save(card);
        return new ApiResponse("Card added successfully", true);
    }

    public List<Card> getAll() {
        List<Card> all = cardRepository.findAll();

        return all;
    }

    public Card getCardById(Integer id) {
        Optional<Card> optionalCard = cardRepository.findById(id);
        if (optionalCard.isEmpty())
            return null;
        return optionalCard.get();
    }

    public ApiResponse editCard(Integer id, Card comingCard) {
        Optional<Card> optionalCard = cardRepository.findById(id);
        if (optionalCard.isEmpty())
            return new ApiResponse("Card not found", false);

        Card card = optionalCard.get();
        card.setUsername(comingCard.getUsername());
        card.setNumber(comingCard.getNumber());
        card.setBalance(comingCard.getBalance());
        card.setExpiredDate(comingCard.getExpiredDate());
        card.setActive(comingCard.getActive());

        cardRepository.save(card);

        return new ApiResponse("Card edited successfully", true);
    }

    public ApiResponse deleteCardById(Integer id) {
        Optional<Card> optionalCard = cardRepository.findById(id);
        if (optionalCard.isEmpty())
            return new ApiResponse("Card not found", false);

        cardRepository.deleteById(id);
        return new ApiResponse("Card deleted", true);
    }
}
