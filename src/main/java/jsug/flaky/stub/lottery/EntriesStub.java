package jsug.flaky.stub.lottery;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/entries")
public class EntriesStub {
	
	ObjectMapper om = new ObjectMapper();

	@GetMapping(value="/size", params="eventId")
	public JsonNode entrySize(@RequestParam UUID eventId) {
		return om.createObjectNode()
		.put("value", 50);
	}

}
