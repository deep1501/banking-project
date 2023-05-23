package com.springboot.bankingapp.controller;

import com.springboot.bankingapp.model.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class BankController {
        private Map<String, Account> accounts = new HashMap<>();

        @PostMapping("/account")
        public ResponseEntity<String> createAccount(@RequestBody Account account) {
            accounts.put(account.getAccountNumber(), account);
            return ResponseEntity.ok("Account created successfully!");
        }

        @GetMapping("/account/{accountNumber}")
        public ResponseEntity<Account> getAccount(@PathVariable String accountNumber) {
            Account account = accounts.get(accountNumber);
            if (account != null) {
                return ResponseEntity.ok(account);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @PostMapping("/account/{accountNumber}/deposit")
        public ResponseEntity<String> deposit(@PathVariable String accountNumber, @RequestParam double amount) {
            Account account = accounts.get(accountNumber);
            if (account != null) {
                double currentBalance = account.getBalance();
                account.setBalance(currentBalance + amount);
                return ResponseEntity.ok("Deposit successful!");
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @PostMapping("/account/{accountNumber}/withdraw")
        public ResponseEntity<String> withdraw(@PathVariable String accountNumber, @RequestParam double amount) {
            Account account = accounts.get(accountNumber);
            if (account != null) {
                double currentBalance = account.getBalance();
                if (currentBalance >= amount) {
                    account.setBalance(currentBalance - amount);
                    return ResponseEntity.ok("Withdrawal successful!");
                } else {
                    return ResponseEntity.badRequest().body("Insufficient balance!");
                }
            } else {
                return ResponseEntity.notFound().build();
            }
        }
}
