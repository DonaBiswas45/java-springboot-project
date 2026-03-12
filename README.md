### Explora - trips and event Finder Platform

A microservices platform built with Java and Spring Boot, featuring an API Gateway, event-driven architecture, rate limiting, and circuit breaking.
AuthService  (port 8081): Handle user Authentication and Authorization.
EventService (port 8082): Handles event CRUD, search, filter, and status workflow.
ApiGateway (port 8080): Single entry point for all requests.


## 🎯 Key Features

- ✅ **Microservices Architecture** — Two independent services with isolated databases
- ✅ **API Gateway** — Centralized routing and cross-cutting concerns
- ✅ **Rate Limiting** — IP-based request throttling (5 req/10s)
- ✅ **Circuit Breaker** — Resilience4j with graceful fallbacks
- ✅ **Event Driven** — Spring Application Events for decoupled communication
- ✅ **Status Workflow** — SUBMITTED → APPROVED → PUBLISHED → CANCELLED
- ✅ **Search & Filter** — Dynamic JPQL queries by name, category, location, price
- ✅ **Unit Tests** — 5 tests covering creation, search, and workflow validation
- ✅ **Frontend** — Responsive HTML/JS UI with search and filter


## ⚙️ Tech Stack

 Layer | Technology :

| Language : Java 17 ;
| Framework : Spring Boot 4.0.3 ;
| Database :  H2 (in-memory) ;
| API Docs  Swagger UI (SpringDoc); 
| Circuit Breaker : Resilience4j ;
| Testing : JUnit 5, Spring Boot Test; 
| Frontend : HTML, CSS, JavaScript ;
| Build Tool : Maven ;
