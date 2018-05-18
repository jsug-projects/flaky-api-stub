package jsug.flaky.stub.event;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping("/api/events")
public class EventsStub {
	
	ObjectMapper om = new ObjectMapper();
	
	@GetMapping("/{id}")
	public JsonNode detail(@PathVariable UUID id) {

		String publishDate = null;
		if (id.toString().matches("[0-5][^/]*") || publishedUuids.contains(id)) {
			publishDate = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).toString();
		}		
		
		ObjectNode event = om.createObjectNode()
		.put("id", id.toString())
		.put("eventName", "some event")
		.put("overview", "some overview")
		.put("attendeeMaxSize", 100)
		.put("startDateTime", LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).toString())
		.put("endDateTime", LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).toString())
		.put("eventPlace", "some place")
		.put("publishDateTime", publishDate)
		.put("attendeeSize", 80)
		;
		ArrayNode sessions = om.createArrayNode();		
		event.set("sessions", sessions);
		IntStream.rangeClosed(0, 2).forEach((idx) -> {
			String sessionId = UUID.randomUUID().toString(); 
			ObjectNode session = om.createObjectNode()
			.put("id", sessionId)
			.put("title", "some title")
			.put("overview", "some overview")
			.put("startDateTime", LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).toString())
			.put("endDateTime", LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).toString())
			.put("eventId", id.toString())
			;
			sessions.add(session);			
			
			ArrayNode speakers = om.createArrayNode();
			session.set("speakers", speakers);
			IntStream.rangeClosed(0, 1).forEach((idx2)->{
				ObjectNode speaker = om.createObjectNode()
				.put("id", UUID.randomUUID().toString())
				.put("displayName", "some name")
				.put("profile", "some profile")
				.put("belongTo", "some company")
				.put("sessionId", sessionId)
				.put("memberId", UUID.randomUUID().toString())
				;
				speakers.add(speaker);
			});
			
		});
		
		return event;

	}

	@GetMapping("/future")
	public JsonNode futureEvents() {
		ArrayNode events = om.createArrayNode();
		IntStream.range(0, 9).forEach((idx)->{
			UUID id = UUID.randomUUID();
			ObjectNode event = om.createObjectNode()
					.put("id", id.toString())
					.put("eventName", "some event "+id.toString().substring(0, 3))
					.put("attendeeMaxSize", 100)
					.put("startDateTime", LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).toString())
					.put("endDateTime", LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).toString())
					.put("eventPlace", "some place")
					.put("publishDateTime", LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).toString())
					.put("attendeeSize", 80)
					;
			events.add(event);
		});
		
		return events;
	}
	@GetMapping("/past")
	public JsonNode pastEvents() {
		ArrayNode events = om.createArrayNode();
		IntStream.range(0, 9).forEach((idx)->{
			UUID id = UUID.randomUUID();
			ObjectNode event = om.createObjectNode()
					.put("id", id.toString())
					.put("eventName", "some event "+id.toString().substring(0, 3))
					.put("attendeeMaxSize", 100)
					.put("startDateTime", LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).toString())
					.put("endDateTime", LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).toString())
					.put("eventPlace", "some place")
					.put("publishDateTime", LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).toString())
					.put("attendeeSize", 80)
					;
			events.add(event);
		});
		
		return events;
	}
	
	
	List<UUID> publishedUuids = new ArrayList<>();
	
	@PutMapping("/{id}/publish")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void publish(@PathVariable UUID id) {
		publishedUuids.add(id);
	}
	
	
	
	static class EditForm {
		@NotEmpty
		public String eventName;
		@NotEmpty
		public String overview;
		@Min(1)
		public int attendeeMaxSize;
		@DateTimeFormat(iso=ISO.DATE_TIME)
		public LocalDateTime startDateTime;
		@DateTimeFormat(iso=ISO.DATE_TIME)
		public LocalDateTime endDateTime;
		@NotEmpty
		public String eventPlace;
	}
	@PostMapping
	public ResponseEntity<?> create(@Validated @RequestBody EditForm form) {
		return ResponseEntity
				.created(
						ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(UUID.randomUUID().toString()).toUri())
				.build();
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@Validated @RequestBody EditForm form) {
	}

}
