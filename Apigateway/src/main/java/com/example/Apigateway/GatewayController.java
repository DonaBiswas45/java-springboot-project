package com.example.Apigateway;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@CrossOrigin(origins = "*")
@RestController
public class GatewayController {

    @Autowired
    private RateLimitter rateLimitter;

    private RestTemplate restTemplate = new RestTemplate();

    private static final String AUTH_SERVICE = "http://localhost:8081";
    private static final String EVENT_SERVICE = "http://localhost:8082";

    @RequestMapping(value = "/auth/**",
                    method = {RequestMethod.GET, RequestMethod.POST,
                              RequestMethod.PUT, RequestMethod.DELETE})
    @CircuitBreaker(name = "authService", fallbackMethod = "authFallback")
    public ResponseEntity<String> routeToAuth(
            HttpServletRequest request,
            @RequestBody(required = false) String body) {

        String ip = request.getRemoteAddr();
        if (!rateLimitter.isAllowed(ip)) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                    .body("Rate limit exceeded! Max 5 requests per 10 seconds.");
        }

        String url = AUTH_SERVICE + request.getRequestURI();
        String queryString = request.getQueryString();
        if (queryString != null) url += "?" + queryString;
        return forward(request, url, body);
    }

    @RequestMapping(value = "/events/**",
                    method = {RequestMethod.GET, RequestMethod.POST,
                              RequestMethod.PUT, RequestMethod.DELETE})
    @CircuitBreaker(name = "eventService", fallbackMethod = "eventFallback")
    public ResponseEntity<String> routeToEvents(
            HttpServletRequest request,
            @RequestBody(required = false) String body) {

        String ip = request.getRemoteAddr();
        if (!rateLimitter.isAllowed(ip)) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                    .body("Rate limit exceeded! Max 5 requests per 10 seconds.");
        }

        String url = EVENT_SERVICE + request.getRequestURI();
        String queryString = request.getQueryString();
        if (queryString != null) url += "?" + queryString;
        return forward(request, url, body);
    }

    public ResponseEntity<String> authFallback(
            HttpServletRequest request,
            String body, Throwable t) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Auth service temporarily unavailable. Please try again later.");
    }

    public ResponseEntity<String> eventFallback(
            HttpServletRequest request,
            String body, Throwable t) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Event service temporarily unavailable. Please try again later.");
    }

    private ResponseEntity<String> forward(
            HttpServletRequest request, String url, String body) {

        HttpMethod method = HttpMethod.valueOf(request.getMethod());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        return restTemplate.exchange(url, method, entity, String.class);
    }
}
