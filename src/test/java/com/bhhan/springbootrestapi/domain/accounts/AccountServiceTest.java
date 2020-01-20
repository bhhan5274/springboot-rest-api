package com.bhhan.springbootrestapi.domain.accounts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by hbh5274@gmail.com on 2020-01-20
 * Github : http://github.com/bhhan5274
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class AccountServiceTest {
    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void findByUsername(){

        final String userName = "bhhan5274@gmail.com";
        final String password = "bhhan";

        final Account account = Account.builder()
                .email(userName)
                .password(password)
                .roles(Collections.unmodifiableSet(Set.of(AccountRole.ADMIN, AccountRole.USER)))
                .build();

        accountService.saveAccount(account);

        UserDetailsService userDetailsService = (UserDetailsService)accountService;
        final UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

        assertThat(passwordEncoder.matches(password, userDetails.getPassword())).isTrue();
    }

    @Test
    public void findByUsernameFail(){

        final String userName = "random@email.com";

        Assertions.assertThrows(UsernameNotFoundException.class,() -> {
            final UserDetails userDetails = accountService.loadUserByUsername(userName);
        });
    }
}