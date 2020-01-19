package com.bhhan.springbootrestapi.web.dto.events;

import com.bhhan.springbootrestapi.domain.events.Event;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Created by hbh5274@gmail.com on 2020-01-16
 * Github : http://github.com/bhhan5274
 */


@Getter
@Setter
@NoArgsConstructor
public class EventDto {

    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    @NotNull
    private LocalDateTime beginEnrollmentDateTime;
    @NotNull
    private LocalDateTime closeEnrollmentDateTime;
    @NotNull
    private LocalDateTime beginEventDateTime;
    @NotNull
    private LocalDateTime endEventDateTime;
    @NotEmpty
    private String location;
    @Min(0)
    private int basePrice;
    @Min(0)
    private int maxPrice;
    @Min(0)
    private int limitOfEnrollment;

    @Builder
    public EventDto(String name, String description, LocalDateTime beginEnrollmentDateTime,
                    LocalDateTime closeEnrollmentDateTime, LocalDateTime beginEventDateTime,
                    LocalDateTime endEventDateTime, String location, int basePrice,
                    int maxPrice, int limitOfEnrollment){
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
    }

    public EventDto(Event event){
        this.name =  event.getName();
        this.description = event.getDescription();
        this.beginEnrollmentDateTime = event.getBeginEnrollmentDateTime();
        this.closeEnrollmentDateTime = event.getCloseEnrollmentDateTime();
        this.beginEventDateTime = event.getBeginEventDateTime();
        this.endEventDateTime = event.getEndEventDateTime();
        this.location = event.getLocation();
        this.basePrice = event.getBasePrice();
        this.maxPrice = event.getMaxPrice();
        this.limitOfEnrollment = event.getLimitOfEnrollment();
    }

    public Event toEntity(){
        final Event event = Event.builder()
                .name(name)
                .description(description)
                .beginEnrollmentDateTime(beginEnrollmentDateTime)
                .closeEnrollmentDateTime(closeEnrollmentDateTime)
                .beginEventDateTime(beginEventDateTime)
                .endEventDateTime(endEventDateTime)
                .location(location)
                .basePrice(basePrice)
                .maxPrice(maxPrice)
                .limitOfEnrollment(limitOfEnrollment)
                .build();
        event.update();

        return event;
    }
}
