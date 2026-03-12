package com.example.EventService.Entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "events")
public class Event {
   
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String name;
	    private String category;
	    private String location;
	    private String description;
	    private double price;
	    private LocalDate eventDate;
	    private double rating;
	    private String status; // SUBMITTED, APPROVED, PUBLISHED, CANCELLED

	    public Long getId() { return id; }
	    public String getName() { return name; }
	    public String getCategory() { return category; }
	    public String getLocation() { return location; }
	    public String getDescription() { return description; }
	    public double getPrice() { return price; }
	    public LocalDate getEventDate() { return eventDate; }
	    public double getRating() { return rating; }
	    public String getStatus() { return status; }

	    public void setId(Long id) { this.id = id; }
	    public void setName(String name) { this.name = name; }
	    public void setCategory(String category) { this.category = category; }
	    public void setLocation(String location) { this.location = location; }
	    public void setDescription(String description) { this.description = description; }
	    public void setPrice(double price) { this.price = price; }
	    public void setEventDate(LocalDate eventDate) { this.eventDate = eventDate; }
	    public void setRating(double rating) { this.rating = rating; }
	    public void setStatus(String status) { this.status = status; }
}
