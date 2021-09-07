package uz.pdp.app_jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app_jwt.entity.Income;
import uz.pdp.app_jwt.entity.Outcome;
import uz.pdp.app_jwt.payload.ApiResponse;
import uz.pdp.app_jwt.payload.TransferDto;
import uz.pdp.app_jwt.service.TransferService;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/api/transfer")
public class TransferController {

    @Autowired
    TransferService transferService;

    @PostMapping()
    public HttpEntity<?> transferMoney(@RequestBody TransferDto transferDto) {
        ApiResponse apiResponse = transferService.transfer(transferDto);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    @GetMapping(value = "/incomes/{username}")
    public ArrayList<Income> getIncomesByUsername(@PathVariable String username) {
        return transferService.getIncomes(username);
    }

    @GetMapping(value = "/outcomes/{username}")
    public ArrayList<Outcome> getOutcomesByUsername(@PathVariable String username) {
        return transferService.getOutcomes(username);
    }
}
