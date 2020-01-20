package com.bhhan.springbootrestapi.config;

import com.bhhan.springbootrestapi.domain.accounts.Account;
import com.bhhan.springbootrestapi.domain.accounts.AccountRole;
import com.bhhan.springbootrestapi.domain.accounts.AccountService;
import com.bhhan.springbootrestapi.domain.commons.AppProperties;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

/**
 * Created by hbh5274@gmail.com on 2020-01-19
 * Github : http://github.com/bhhan5274
 */

@Configuration
public class UtilitiesConfiguration {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public ApplicationRunner applicationRunner(){
        return new ApplicationRunner() {

            @Autowired
            private AccountService accountService;

            @Autowired
            private AppProperties appProperties;

            @Override
            public void run(ApplicationArguments args) throws Exception {
                final Account admin = Account.builder()
                        .email(appProperties.getAdminUsername())
                        .password(appProperties.getAdminPassword())
                        .roles(Set.of(AccountRole.ADMIN))
                        .build();

                accountService.saveAccount(admin);

                final Account user = Account.builder()
                        .email(appProperties.getUserUsername())
                        .password(appProperties.getUserPassword())
                        .roles(Set.of(AccountRole.USER))
                        .build();

                accountService.saveAccount(user);
            }
        };
    }
}
