package com.ams.theamsbanksystem.controller;

import com.ams.theamsbanksystem.model.Account;
import com.ams.theamsbanksystem.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

//@RestController
@Controller
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    @GetMapping(path = "testing")
    public String getIndex(Model model) {
        List<Account> accounts = accountService.getAllAccounts();
        model.addAttribute("accounts", accounts);
        return "index";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account newAccount = accountService.createAccount(account);
        return ResponseEntity.ok(newAccount);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long id, @RequestBody Account accountDetails) {
        Account updatedAccount = accountService.updateAccount(id, accountDetails);
        return ResponseEntity.ok(updatedAccount);
    }

//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteAccount(@PathVariable Long id) {
//        accountService.deleteAccount(id);
//        return ResponseEntity.noContent().build();  // 204 No Content is typically returned for successful DELETE operations
//    }

@PostMapping("/{id}")
public String deleteAccount(@PathVariable Long id) {
    accountService.deleteAccount(id);
    return "redirect:/api/accounts/testing";
}

    // Method to serve the update account page
    @GetMapping("/update/{id}")
    public String updateAccountPage(@PathVariable Long id, Model model) {
        Account account = accountService.getAccountById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
        model.addAttribute("account", account);
        return "updateAccount";  // Refers to updateAccount.html in templates directory
    }

    @PostMapping("/update")
    public String updateAccount(@ModelAttribute Account account) {
        accountService.updateAccount(account.getId(), account);
        return "redirect:/api/accounts/testing"; // Redirect back to the testing page
    }


}