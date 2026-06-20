package com.example.EventService.service;

import com.example.EventService.Entity.Event;
import com.example.EventService.kafka.EventKafkaProducer;
import com.example.EventService.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventKafkaProducer kafkaProducer;

    public Event createEvent(Event event) {
        event.setStatus("SUBMITTED");
        Event saved = eventRepository.save(event);
        kafkaProducer.publishEventCreated(saved.getId(), saved.getName());
        return saved;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public List<Event> searchEvents(String name, String category,
                                    String location, Double minPrice, Double maxPrice) {
        return eventRepository.searchEvents(name, category, location, minPrice, maxPrice);
    }

    public Event updateStatus(Long id, String newStatus) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        String current = event.getStatus();
        if (current.equals("CANCELLED"))
            throw new RuntimeException("Cannot change status of cancelled event");
        if (current.equals("PUBLISHED") && newStatus.equals("SUBMITTED"))
            throw new RuntimeException("Cannot move published event back to submitted");

        event.setStatus(newStatus);
        Event updated = eventRepository.save(event);
        kafkaProducer.publishStatusChanged(updated.getId(), current, newStatus);
        return updated;
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}
