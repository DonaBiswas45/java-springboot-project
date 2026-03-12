package com.example.EventService;

import com.example.EventService.Entity.Event;
import com.example.EventService.repository.EventRepository;
import com.example.EventService.service.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EventServiceTest {

    @Autowired
    private EventService eventService;

    @Autowired
    private EventRepository eventRepository;

    @BeforeEach
    void setUp() {
        eventRepository.deleteAll();
    }

    @Test
    void testCreateEvent() {
        Event event = new Event();
        event.setName("Sunburn Festival");
        event.setCategory("music");
        event.setLocation("Bangalore");
        event.setPrice(999.0);
        event.setEventDate(LocalDate.of(2025, 4, 20));
        event.setRating(4.5);

        Event saved = eventService.createEvent(event);

        assertNotNull(saved.getId());
        assertEquals("Sunburn Festival", saved.getName());
        assertEquals("SUBMITTED", saved.getStatus());
        System.out.println("✅ Test 1 Passed: Event created!");
    }

    @Test
    void testGetAllEvents() {
        Event event = new Event();
        event.setName("Food Fest");
        event.setCategory("food");
        event.setLocation("Mumbai");
        event.setPrice(299.0);
        event.setEventDate(LocalDate.of(2025, 5, 10));
        event.setRating(4.0);
        eventService.createEvent(event);

        List<Event> events = eventService.getAllEvents();
        assertFalse(events.isEmpty());
        System.out.println("✅ Test 2 Passed: Get all events!");
    }

    @Test
    void testSearchByCategory() {
        Event event = new Event();
        event.setName("Rock Night");
        event.setCategory("music");
        event.setLocation("Delhi");
        event.setPrice(499.0);
        event.setEventDate(LocalDate.of(2025, 6, 15));
        event.setRating(4.2);
        eventService.createEvent(event);

        List<Event> results = eventService.searchEvents(
                null, "music", null, null, null);
        assertFalse(results.isEmpty());
        assertEquals("music", results.get(0).getCategory());
        System.out.println("✅ Test 3 Passed: Search working!");
    }

    @Test
    void testStatusTransition() {
        Event event = new Event();
        event.setName("Art Show");
        event.setCategory("art");
        event.setLocation("Pune");
        event.setPrice(199.0);
        event.setEventDate(LocalDate.of(2025, 7, 20));
        event.setRating(3.8);
        Event saved = eventService.createEvent(event);

        Event approved = eventService.updateStatus(saved.getId(), "APPROVED");
        assertEquals("APPROVED", approved.getStatus());
        System.out.println("✅ Test 4 Passed: Status transition!");
    }

    @Test
    void testCancelledEventCannotChange() {
        Event event = new Event();
        event.setName("Tech Talk");
        event.setCategory("tech");
        event.setLocation("Hyderabad");
        event.setPrice(0.0);
        event.setEventDate(LocalDate.of(2025, 8, 5));
        event.setRating(4.0);
        Event saved = eventService.createEvent(event);
        eventService.updateStatus(saved.getId(), "CANCELLED");

        Exception exception = assertThrows(RuntimeException.class, () -> {
            eventService.updateStatus(saved.getId(), "APPROVED");
        });

        assertTrue(exception.getMessage()
                .contains("Cannot change status of cancelled event"));
        System.out.println("✅ Test 5 Passed: Cancelled protection!");
    }
}