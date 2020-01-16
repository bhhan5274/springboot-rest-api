package com.bhhan.springbootrestapi.domain.events;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by hbh5274@gmail.com on 2020-01-16
 * Github : http://github.com/bhhan5274
 */
public interface EventRepository extends JpaRepository<Event, Long> {
}
