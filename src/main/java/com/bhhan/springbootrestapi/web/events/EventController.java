package com.bhhan.springbootrestapi.web.events;

import com.bhhan.springbootrestapi.domain.events.Event;
import com.bhhan.springbootrestapi.domain.events.EventRepository;
import com.bhhan.springbootrestapi.domain.events.EventValidator;
import com.bhhan.springbootrestapi.web.dto.events.EventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by hbh5274@gmail.com on 2020-01-16
 * Github : http://github.com/bhhan5274
 */

@RestController
@RequestMapping(value = "/api/events", produces = MediaTypes.HAL_JSON_VALUE)
@RequiredArgsConstructor
public class EventController {

    private final EventRepository eventRepository;
    private final EventValidator eventValidator;

    @PostMapping
    public ResponseEntity createEvent(@RequestBody @Valid EventDto eventDto, Errors errors){

        if(errors.hasErrors()){
            return ResponseEntity.badRequest().body(errors);
        }

        eventValidator.validate(eventDto, errors);

        if(errors.hasErrors()){
            return ResponseEntity.badRequest().body(errors);
        }

        final Event savedEvent = eventRepository.save(eventDto.toEntity());

        final URI createdUri = linkTo(EventController.class).slash(savedEvent.getId()).toUri();

        return ResponseEntity.created(createdUri).body(savedEvent);
    }
}
