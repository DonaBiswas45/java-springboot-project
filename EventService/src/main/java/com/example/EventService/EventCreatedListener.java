package com.example.EventService;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EventCreatedListener {
	  @EventListener
	    public void handleEventCreated(EventCreatedEvent event) {
	        System.out.println("🎉 New Event Created: " + event.getEventName());
	        System.out.println("📧 Notification would be sent here!");
	    }
}
