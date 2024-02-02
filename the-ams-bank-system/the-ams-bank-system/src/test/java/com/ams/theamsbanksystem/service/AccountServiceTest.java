package com.ams.theamsbanksystem.service;

import com.ams.theamsbanksystem.model.Account;
import com.ams.theamsbanksystem.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @BeforeEach
    public void setup() {
        // Set up any required pre-conditions here
    }

    @Test
    public void createAccount_ShouldSaveAndReturnAccount() {
        Account newAccount = new Account("1234567890", new BigDecimal("1000.00"));
        given(accountRepository.save(any(Account.class))).willReturn(newAccount);

        Account createdAccount = accountService.createAccount(newAccount);
        assertThat(createdAccount).isNotNull();
        assertThat(createdAccount.getAccountNumber()).isEqualTo(newAccount.getAccountNumber());
    }

    @Test
    public void updateAccount_ShouldChangeBalanceAndReturnAccount() {
        Account existingAccount = new Account("1234567890", new BigDecimal("1000.00"));
        Account updatedAccount = new Account("1234567890", new BigDecimal("2000.00"));
        given(accountRepository.findById(1L)).willReturn(Optional.of(existingAccount));
        given(accountRepository.save(any(Account.class))).willReturn(updatedAccount);

        Account result = accountService.updateAccount(1L, updatedAccount);
        assertThat(result.getBalance()).isEqualTo(updatedAccount.getBalance());
    }

    // ... Add more tests for different scenarios
}
