package com.bhhan.springbootrestapi.web.index;

import com.bhhan.springbootrestapi.web.events.EventController;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by hbh5274@gmail.com on 2020-01-17
 * Github : http://github.com/bhhan5274
 */

@RestController
public class IndexController {
    @GetMapping("/api")
    public ResourceSupport index(){
        final ResourceSupport index = new ResourceSupport();
        index.add(linkTo(EventController.class).withRel("events"));
        return index;
    }
}
