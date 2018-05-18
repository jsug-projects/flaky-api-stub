package jsug.flaky.stub.event;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping("/api/attendees")
public class AttendeesStub {
	ObjectMapper om = new ObjectMapper();

	Random random = new Random();
	
	@GetMapping(params={"eventId", "keyword"})
	public JsonNode search(@RequestParam UUID eventId, @RequestParam String keyword){
		ArrayNode attendees = om.createArrayNode();
		IntStream.range(0, 9).forEach((idx)->{
			UUID id = UUID.randomUUID();
			ObjectNode attendee = om.createObjectNode()
					.put("id", id.toString())
					.put("displayName", "some attendee "+id.toString().substring(0, 3))
					.put("belongTo", "some belongTo")
					.put("isAttended", random.nextInt()%2==0?true:false)
					.put("attendeeSize", 80)
					;
			attendees.add(attendee);
		});
		
		return attendees;		
	}
	
	@GetMapping("/size")
	public JsonNode size(@RequestParam UUID eventId) { 
		return om.createObjectNode()
		.put("attendanceSize", random.nextInt(100))
		.put("attendeeSize", 100)
		;
	}
	
	@PutMapping("/{id}/attend")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void attend(@PathVariable UUID id) {
		
	}
	
	@PutMapping("/{id}/unattend")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void unattend(@PathVariable UUID id) {
		
	}
	
}
