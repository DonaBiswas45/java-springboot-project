package com.example.EventService.service;

import com.example.EventService.Entity.Event;
import com.example.EventService.EventPublisher;
import com.example.EventService.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EventService {
     
	@Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventPublisher eventPublisher;

    // CREATE
    public Event createEvent(Event event) {
        event.setStatus("SUBMITTED");
        Event saved = eventRepository.save(event);
        eventPublisher.publishEventCreated(event.getName());
        return saved;
    }

    // GET ALL
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // SEARCH & FILTER
    public List<Event> searchEvents(String name, String category,
                                    String location, Double minPrice,
                                    Double maxPrice) {
        return eventRepository.searchEvents(name, category,
                                            location, minPrice, maxPrice);
    }

    // UPDATE STATUS
    public Event updateStatus(Long id, String newStatus) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        // Status transition validation
        String current = event.getStatus();
        if (current.equals("CANCELLED")) {
            throw new RuntimeException("Cannot change status of cancelled event");
        }
        if (current.equals("PUBLISHED") && newStatus.equals("SUBMITTED")) {
            throw new RuntimeException("Cannot move published event back to submitted");
        }

        event.setStatus(newStatus);
        return eventRepository.save(event);
    }

    // DELETE
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}
