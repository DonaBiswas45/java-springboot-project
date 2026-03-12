package com.example.EventService;

import com.example.EventService.Entity.Event;
import com.example.EventService.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public void run(String... args) throws Exception {

        if (eventRepository.count() == 0) {

            Event e1 = new Event();
            e1.setName("Sunburn Festival");
            e1.setCategory("music");
            e1.setLocation("Bangalore");
            e1.setDescription("Biggest electronic music festival");
            e1.setPrice(999.0);
            e1.setEventDate(LocalDate.of(2025, 4, 20));
            e1.setRating(4.5);
            e1.setStatus("PUBLISHED");
            eventRepository.save(e1);

            Event e2 = new Event();
            e2.setName("Street Food Fest");
            e2.setCategory("food");
            e2.setLocation("Mumbai");
            e2.setDescription("Best street food from across India");
            e2.setPrice(299.0);
            e2.setEventDate(LocalDate.of(2025, 5, 10));
            e2.setRating(4.2);
            e2.setStatus("PUBLISHED");
            eventRepository.save(e2);

            Event e3 = new Event();
            e3.setName("Tech Summit 2025");
            e3.setCategory("tech");
            e3.setLocation("Hyderabad");
            e3.setDescription("Latest in AI and Cloud technology");
            e3.setPrice(1499.0);
            e3.setEventDate(LocalDate.of(2025, 6, 15));
            e3.setRating(4.8);
            e3.setStatus("APPROVED");
            eventRepository.save(e3);

            Event e4 = new Event();
            e4.setName("Art & Culture Fair");
            e4.setCategory("art");
            e4.setLocation("Delhi");
            e4.setDescription("Celebrating Indian art and culture");
            e4.setPrice(199.0);
            e4.setEventDate(LocalDate.of(2025, 7, 5));
            e4.setRating(4.0);
            e4.setStatus("PUBLISHED");
            eventRepository.save(e4);

            Event e5 = new Event();
            e5.setName("IPL Watch Party");
            e5.setCategory("sports");
            e5.setLocation("Chennai");
            e5.setDescription("Watch IPL finals live with fans");
            e5.setPrice(499.0);
            e5.setEventDate(LocalDate.of(2025, 5, 25));
            e5.setRating(4.6);
            e5.setStatus("PUBLISHED");
            eventRepository.save(e5);

            Event e6 = new Event();
            e6.setName("Local Band Night");
            e6.setCategory("music");
            e6.setLocation("Pune");
            e6.setDescription("Local indie bands performing live");
            e6.setPrice(349.0);
            e6.setEventDate(LocalDate.of(2025, 4, 28));
            e6.setRating(4.1);
            e6.setStatus("PUBLISHED");
            eventRepository.save(e6);

            System.out.println("✅ Sample data loaded!");
        }
    }
}