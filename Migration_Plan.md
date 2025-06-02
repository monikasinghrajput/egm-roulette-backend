# Migration Plan: RouletteSSeaterController to Spring Boot

## 1. Dependencies
- Replace Play Framework's dependencies with Spring Boot dependencies:
  - Use `spring-boot-starter-web` for HTTP endpoints.
  - Use `spring-boot-starter-websocket` for WebSocket support.
  - Use `spring-boot-starter-json` for JSON handling.
  - Include `akka` dependencies if Akka actors are retained.

## 2. Replace Controller and Action
- Convert Play's `AbstractController` and `Action` to Spring's `@RestController`.
- Use `@GetMapping`, `@PostMapping`, or `@RequestMapping` for endpoint mappings.

### Example:
From:
```scala
def sendPlayerPage: Action[AnyContent] = Action { request =>
  Ok(views.html.pages.roulette.player())
}
```
To:
```java
@GetMapping("/player")
public ResponseEntity<String> sendPlayerPage(HttpServletRequest request) {
    logger.info("Sending roulette player page for " + request.getRemoteAddr());
    return ResponseEntity.ok("Player Page");
}
```

## 3. JSON Handling
- Replace Play's `Reads` and `JsPath` with Jackson annotations or `ObjectMapper`.

### Example:
From:
```scala
implicit val wheelMsgDecoder: Reads[WheelMsg] = (
  (JsPath \ "MessageType").read[String] and
  ((JsPath \ "result").read[String] or Reads.pure("-1"))
)(WheelMsg.apply _)
```
To:
```java
public class WheelMsg {
    private String messageType;
    private String result = "-1"; // Default value

    // Getters and setters
}
```

## 4. WebSocket Support
- Use Spring's `@ServerEndpoint` or `WebSocketHandler` for WebSocket handling.
- Configure WebSocket endpoints in a Spring configuration class.

### Example:
From:
```scala
def player: WebSocket = WebSocket.accept[JsValue, JsValue] { request =>
    ActorFlow.actorRef { out =>
        ClientActor.props(out, rouletteSSeaterTableService.getAutoRouletteActor, gameService.getLoggingActor, ip)
    }
}
```
To:
```java
@Component
public class PlayerWebSocketHandler extends TextWebSocketHandler {
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        // Handle WebSocket messages here
    }
}
```

## 5. Logging
- Replace Play's `Logger` with SLF4J or Spring's built-in logging.

### Example:
From:
```scala
log.logger.info("Sending roulette player page for " + request.remoteAddress)
```
To:
```java
logger.info("Sending roulette player page for {}", request.getRemoteAddr());
```

## 6. Services and Dependency Injection
- Define `GameService` and `RouletteSSeaterTableService` as Spring services using `@Service`.
- Inject these services into the controller using `@Autowired`.

## 7. API Endpoints
- Migrate all API methods (`sendInitialDataJson`, `sendAuthenticateJson`, etc.) to Spring endpoints using appropriate annotations.

## 8. Akka Integration
- If Akka actors are retained, integrate Akka with Spring Boot using Spring's Akka support.
- Alternatively, replace Akka actors with Spring-managed beans.

## 9. Testing
- Write unit and integration tests for all migrated components using JUnit and Spring's testing framework.