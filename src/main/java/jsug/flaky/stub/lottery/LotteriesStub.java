package jsug.flaky.stub.lottery;

import java.time.LocalDate;
import java.util.UUID;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping("/api/lotteries")
public class LotteriesStub {

	ObjectMapper om = new ObjectMapper();

	@GetMapping(params="eventId")
	public JsonNode lottery(@RequestParam UUID eventId) {
		ArrayNode lotteries = om.createArrayNode();
		ObjectNode lottery = om.createObjectNode()
		.put("id", UUID.randomUUID().toString())
		.put("electionSize", 100)
		.put("lotteryDate", LocalDate.now().toString())
		.put("executedDate", LocalDate.now().toString())
		.put("eventId", eventId.toString())
		;
		
		lotteries.add(lottery);
		
		lottery = om.createObjectNode()
		.put("id", UUID.randomUUID().toString())
		.put("electionSize", 100)
		.put("lotteryDate", LocalDate.now().toString())
		.put("executedDate", LocalDate.now().toString())
		.put("eventId", eventId.toString())
		;
		
		lotteries.add(lottery);
		
		return lotteries;
	}
	
	
	static class EditForm {
		@Min(1)
		public int electionSize;
		
		@DateTimeFormat(iso=ISO.DATE)
		public LocalDate lotteryDate;
		
		@NotNull
		public UUID eventId;
	}
	@PostMapping
	public ResponseEntity<?> add(@Validated @RequestBody EditForm form) {
		return ResponseEntity
				.created(
						ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(UUID.randomUUID().toString()).toUri())
				.build();		
	}
	
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@Validated @RequestBody EditForm form) {
	}	
	
	
	
	
	@DeleteMapping("/{id}")
	public void delete() {
		
	}
	
}
