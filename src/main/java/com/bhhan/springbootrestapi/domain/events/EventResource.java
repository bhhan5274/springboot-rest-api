package com.bhhan.springbootrestapi.domain.events;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

/**
 * Created by hbh5274@gmail.com on 2020-01-17
 * Github : http://github.com/bhhan5274
 */
public class EventResource extends Resource<Event> {
    public EventResource(Event content, Link... links) {
        super(content, links);
    }

    public EventResource addLink(Link link){
        add(link);
        return this;
    }
}
