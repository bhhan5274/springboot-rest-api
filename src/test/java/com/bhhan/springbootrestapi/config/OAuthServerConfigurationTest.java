package com.bhhan.springbootrestapi.config;

import com.bhhan.springbootrestapi.common.BaseControllerTest;
import com.bhhan.springbootrestapi.domain.accounts.Account;
import com.bhhan.springbootrestapi.domain.accounts.AccountRole;
import com.bhhan.springbootrestapi.domain.accounts.AccountService;
import com.bhhan.springbootrestapi.domain.commons.AppProperties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by hbh5274@gmail.com on 2020-01-20
 * Github : http://github.com/bhhan5274
 */

public class OAuthServerConfigurationTest extends BaseControllerTest {
    @Autowired
    AccountService accountService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    AppProperties appProperties;

    @Test
    @DisplayName("인증 토큰을 발급 받는 테스트")
    public void getAuthToken() throws Exception {

        final String userName = "bhhan@gmail.com";
        final String password = "bhhan";

        final Account account = Account.builder()
                .email(userName)
                .password(password)
                .roles(Set.of(AccountRole.ADMIN, AccountRole.USER))
                .build();
        accountService.saveAccount(account);


        String clientId = appProperties.getClientId();
        String clientSecret = appProperties.getClientSecret();

        this.mockMvc.perform(post("/oauth/token")
                            .with(httpBasic(clientId, clientSecret))
                            .param("username", userName)
                            .param("password", password)
                            .param("grant_type", "password"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("access_token").exists());
    }
}