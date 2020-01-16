package com.bhhan.springbootrestapi.domain.events;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by hbh5274@gmail.com on 2020-01-16
 * Github : http://github.com/bhhan5274
 */

public class EventTest {
    @Test
    public void builder(){
        final String name = "Spring REST API";
        final String description = "REST API development with Spring";

        final Event event = Event.builder()
                .name(name)
                .description(description)
                .build();
        assertThat(event).isNotNull();
        assertThat(event.getName()).isEqualTo(name);
        assertThat(event.getDescription()).isEqualTo(description);
    }
}