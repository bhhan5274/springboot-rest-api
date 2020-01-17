package com.bhhan.springbootrestapi.web.events;

import com.bhhan.springbootrestapi.domain.commons.ErrorsResource;
import com.bhhan.springbootrestapi.domain.events.Event;
import com.bhhan.springbootrestapi.domain.events.EventRepository;
import com.bhhan.springbootrestapi.domain.events.EventResource;
import com.bhhan.springbootrestapi.domain.events.EventValidator;
import com.bhhan.springbootrestapi.web.dto.events.EventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

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
            return badResponse(errors);
        }

        eventValidator.validate(eventDto, errors);

        if(errors.hasErrors()){
            return badResponse(errors);
        }

        final Event savedEvent = eventRepository.save(eventDto.toEntity());

        final ControllerLinkBuilder selfLinkBuilder = linkTo(EventController.class).slash(savedEvent.getId());
        final URI createdUri = selfLinkBuilder.toUri();

        final EventResource eventResource = new EventResource(savedEvent)
                .addLink(linkTo(EventController.class).withRel("query-events"))
                .addLink(selfLinkBuilder.withSelfRel())
                .addLink(selfLinkBuilder.withRel("update-event"))
                .addLink(new Link("/docs/index.html#resources-events-create").withRel("profile"));

        return ResponseEntity.created(createdUri).body(eventResource);
    }

    private ResponseEntity badResponse(Errors errors) {
        return ResponseEntity.badRequest().body(new ErrorsResource(errors));
    }
}
