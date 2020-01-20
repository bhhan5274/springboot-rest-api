package com.bhhan.springbootrestapi.domain.events;

import com.bhhan.springbootrestapi.domain.accounts.Account;
import com.bhhan.springbootrestapi.domain.accounts.AccountSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by hbh5274@gmail.com on 2020-01-16
 * Github : http://github.com/bhhan5274
 */

@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Setter
@Entity
@Table(name = "EVENTS")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EVENT_ID")
    private Long id;

    private String name;
    private String description;
    private LocalDateTime beginEnrollmentDateTime;
    private LocalDateTime closeEnrollmentDateTime;
    private LocalDateTime beginEventDateTime;
    private LocalDateTime endEventDateTime;
    private String location;
    private int basePrice;
    private int maxPrice;
    private int limitOfEnrollment;
    private boolean offline;
    private boolean free;

    @ManyToOne
    @JsonSerialize(using = AccountSerializer.class)
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;

    @Enumerated(value = EnumType.STRING)
    private EventStatus eventStatus;

    @Builder
    public Event(String name, String description, LocalDateTime beginEnrollmentDateTime,
                 LocalDateTime closeEnrollmentDateTime, LocalDateTime beginEventDateTime,
                 LocalDateTime endEventDateTime, String location, int basePrice, int maxPrice,
                 int limitOfEnrollment, boolean offline, boolean free, EventStatus eventStatus,
                 Account account){
        this.name = name;
        this.description = description;
        this.beginEnrollmentDateTime = beginEnrollmentDateTime;
        this.closeEnrollmentDateTime = closeEnrollmentDateTime;
        this.beginEventDateTime = beginEventDateTime;
        this.endEventDateTime = endEventDateTime;
        this.location = location;
        this.basePrice = basePrice;
        this.maxPrice = maxPrice;
        this.limitOfEnrollment = limitOfEnrollment;
        this.offline = offline;
        this.free = free;
        this.eventStatus = EventStatus.DRAFT;
        this.account = account;
    }

    public void update() {
        if(basePrice == 0 && maxPrice == 0){
            free = true;
        }else {
            free = false;
        }

        if(location == null || location.isBlank()){
            offline = false;
        }else {
            offline = true;
        }
    }
}
