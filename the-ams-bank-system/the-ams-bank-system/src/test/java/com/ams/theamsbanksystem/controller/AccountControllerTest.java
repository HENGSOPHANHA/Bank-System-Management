package com.ams.theamsbanksystem.controller;

import com.ams.theamsbanksystem.model.Account;
import com.ams.theamsbanksystem.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class AccountControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void getAllAccounts_ShouldReturnAccountsList() throws Exception {
        Account account = new Account("1234567890", new BigDecimal("1000.00"));
        given(accountService.getAllAccounts()).willReturn(Arrays.asList(account));

        mockMvc.perform(get("/api/accounts"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'accountNumber': '1234567890', 'balance': 1000.00}]"));

        verify(accountService).getAllAccounts();
    }

    @Test
    public void getAccountById_ShouldReturnAccount() throws Exception {
        Account account = new Account("1234567890", new BigDecimal("1000.00"));
        given(accountService.getAccountById(1L)).willReturn(Optional.of(account));

        mockMvc.perform(get("/api/accounts/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{'accountNumber': '1234567890', 'balance': 1000.00}"));

        verify(accountService).getAccountById(1L);
    }

    @Test
    public void createAccount_ShouldReturnCreatedAccount() throws Exception {
        Account account = new Account("1234567890", new BigDecimal("1000.00"));
        given(accountService.createAccount(any(Account.class))).willReturn(account);

        mockMvc.perform(post("/api/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(account)))
                .andExpect(status().isOk())
                .andExpect(content().json("{'accountNumber': '1234567890', 'balance': 1000.00}"));

        verify(accountService).createAccount(any(Account.class));
    }

    @Test
    public void updateAccount_ShouldReturnUpdatedAccount() throws Exception {
        Account existingAccount = new Account("1234567890", new BigDecimal("1000.00"));
        Account updatedAccount = new Account("1234567890", new BigDecimal("2000.00"));
        given(accountService.updateAccount(eq(1L), any(Account.class))).willReturn(updatedAccount);

        mockMvc.perform(put("/api/accounts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedAccount)))
                .andExpect(status().isOk())
                .andExpect(content().json("{'accountNumber': '1234567890', 'balance': 2000.00}"));

        verify(accountService).updateAccount(eq(1L), any(Account.class));
    }

    // ... Add more tests for different scenarios
}
