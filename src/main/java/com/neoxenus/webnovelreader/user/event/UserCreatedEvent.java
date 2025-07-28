package com.neoxenus.webnovelreader.user.event;

import com.neoxenus.webnovelreader.user.entity.User;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class UserCreatedEvent extends ApplicationEvent {
    private final User user;
    public UserCreatedEvent(Object source, User user) {
        super(source);
        this.user = user;
    }
}
