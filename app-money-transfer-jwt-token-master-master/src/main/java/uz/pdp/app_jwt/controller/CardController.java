package uz.pdp.app_jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app_jwt.entity.Card;
import uz.pdp.app_jwt.payload.ApiResponse;
import uz.pdp.app_jwt.service.CardService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/card")
public class CardController {

    @Autowired
    CardService cardService;

    @PostMapping
    public HttpEntity<?> addCard(@RequestBody Card card) {
        ApiResponse apiResponse = cardService.addCard(card);

        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    @GetMapping
    public List<Card> getAll() {
        List<Card> all = cardService.getAll();
        return all;
    }

    @GetMapping(value = "/{id}")
    public Card getCardById(@PathVariable Integer id) {
        return cardService.getCardById(id);
    }

    @PutMapping(value = "/{id}")
    public HttpEntity<?> editCard(@PathVariable Integer id, @RequestBody Card card) {
        ApiResponse apiResponse = cardService.editCard(id, card);

        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping(value = "/{id}")
    public HttpEntity<?> deleteCard(@PathVariable Integer id) {
        ApiResponse apiResponse = cardService.deleteCardById(id);

        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }
}

