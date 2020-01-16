package com.bhhan.springbootrestapi.domain.events;

import com.bhhan.springbootrestapi.web.dto.events.EventDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Created by hbh5274@gmail.com on 2020-01-16
 * Github : http://github.com/bhhan5274
 */

@Component
public class EventValidator {
    public void validate(EventDto eventDto, Errors errors){
        if(eventDto.getBasePrice() > eventDto.getMaxPrice() && eventDto.getMaxPrice() != 0){
            errors.rejectValue("basePrice", "wrongValue", "BasePrice is wrong.");
            errors.rejectValue("maxPrice", "wrongValue", "MaxPrice is wrong.");
            errors.reject("wrongPrices", "Prices are wrong");
        }

        @NotNull final LocalDateTime endEventDateTime = eventDto.getEndEventDateTime();
        if(endEventDateTime.isBefore(eventDto.getBeginEventDateTime()) ||
        endEventDateTime.isBefore(eventDto.getCloseEnrollmentDateTime()) ||
        endEventDateTime.isBefore(eventDto.getBeginEnrollmentDateTime())){
          errors.rejectValue("endEventDateTime", "wrongValue", "EndEventDateTime is wrong.");
        }

        // TODO: Other Properties Validation
    }
}
