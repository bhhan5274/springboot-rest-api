package com.bhhan.springbootrestapi.domain.accounts;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by hbh5274@gmail.com on 2020-01-20
 * Github : http://github.com/bhhan5274
 */

@Entity
@Table(name = "ACCOUNTS")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNT_ID")
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<AccountRole> roles;

    @Builder
    public Account(String email, String password, Set<AccountRole> roles){
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
}
