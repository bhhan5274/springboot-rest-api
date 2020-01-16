package com.bhhan.springbootrestapi.web.dto.events;

import com.bhhan.springbootrestapi.domain.events.Event;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Created by hbh5274@gmail.com on 2020-01-16
 * Github : http://github.com/bhhan5274
 */


@Builder
@Data
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
