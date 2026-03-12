package com.example.EventService.controller;

import com.example.EventService.Entity.Event;
import com.example.EventService.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/events")
public class EventController {
    
	@Autowired
    private EventService eventService;

    // CREATE
    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        return eventService.createEvent(event);
    }

    // GET ALL
    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    // SEARCH & FILTER
    @GetMapping("/search")
    public List<Event> searchEvents(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice) {
        return eventService.searchEvents(name, category,
                                         location, minPrice, maxPrice);
    }

    // UPDATE STATUS
    @PutMapping("/{id}/status")
    public Event updateStatus(@PathVariable Long id,
                              @RequestParam String newStatus) {
        return eventService.updateStatus(id, newStatus);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return "Event deleted successfully!";
    }
}
