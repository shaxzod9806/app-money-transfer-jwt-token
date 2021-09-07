package uz.pdp.app_jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.app_jwt.entity.Card;
import uz.pdp.app_jwt.entity.Income;
import uz.pdp.app_jwt.entity.Outcome;
import uz.pdp.app_jwt.payload.ApiResponse;
import uz.pdp.app_jwt.payload.TransferDto;
import uz.pdp.app_jwt.repository.CardRepository;
import uz.pdp.app_jwt.repository.IncomeRepository;
import uz.pdp.app_jwt.repository.OutcomeRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
public class TransferService {

    @Autowired
    CardRepository cardRepository;
    @Autowired
    IncomeRepository incomeRepository;
    @Autowired
    OutcomeRepository outcomeRepository;

    public ApiResponse transfer(TransferDto transferDto) {
        Optional<Card> optionalPoorCard = cardRepository.findById(transferDto.getPoorCardId());
        if (optionalPoorCard.isEmpty())
            return new ApiResponse("Card not found", false);

        Optional<Card> optionalRichCard = cardRepository.findById(transferDto.getRichCardId());
        if (optionalRichCard.isEmpty())
            return new ApiResponse("Card not found", false);

        Card poorCard = optionalPoorCard.get();
        Card richCard = optionalRichCard.get();

        cardRepository.save(richCard);
        cardRepository.save(poorCard);

        Income income = new Income();
        income.setFromCardId(transferDto.getRichCardId());
        income.setToCardId(transferDto.getPoorCardId());
        income.setAmount(transferDto.getAmount());
        income.setDate(new Date());

        incomeRepository.save(income);

        if (richCard.getBalance() < transferDto.getAmount() + transferDto.getAmount())
            return new ApiResponse("Balance is not enough", false);

        richCard.setBalance(richCard.getBalance() - transferDto.getAmount() - transferDto.getCommissionAmount());
        poorCard.setBalance(poorCard.getBalance() + transferDto.getAmount());

        Outcome outcome = new Outcome();
        outcome.setFromCardId(transferDto.getPoorCardId());
        outcome.setToCardId(transferDto.getRichCardId());
        outcome.setAmount(transferDto.getAmount() + transferDto.getCommissionAmount());
        outcome.setDate(new Date());
        outcome.setCommissionAmount(transferDto.getCommissionAmount());

        outcomeRepository.save(outcome);

        return new ApiResponse("Money moved successfully", true);
    }

    public ArrayList<Income> getIncomes(String username) {
        Optional<ArrayList<Card>> optionalCard = cardRepository.findByUsername(username);
        if (optionalCard.isEmpty())
            return null;

        for (Card card : optionalCard.get()) {
            return incomeRepository.findIncomesByToCardId(card.getId());
        }
        return null;
    }

    public ArrayList<Outcome> getOutcomes(String username) {
        Optional<ArrayList<Card>> optionalCard = cardRepository.findByUsername(username);
        if (optionalCard.isEmpty())
            return null;

        for (Card card : optionalCard.get()) {
            return outcomeRepository.findOutcomesByToCardId(card.getId());

        }
        return null;

    }
}
