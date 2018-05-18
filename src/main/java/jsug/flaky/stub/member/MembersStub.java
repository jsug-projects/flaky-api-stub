package jsug.flaky.stub.member;

import java.util.UUID;
import java.util.stream.IntStream;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping("/api/members")
public class MembersStub {
	
	ObjectMapper om = new ObjectMapper();
	
	@GetMapping(params="keyword")
	public JsonNode members(@RequestParam String keyword) {
		ArrayNode members = om.createArrayNode();
		IntStream.rangeClosed(0, 9).forEach((idx)->{
			UUID id = UUID.randomUUID();
			ObjectNode member = om.createObjectNode()
					.put("id", id.toString())
					.put("emailAddress", "foo"+idx+"@example.com")
					.put("point", 100)
					;
			members.add(member);
		});
		
		return members;
	}
	
	@GetMapping("/{id}")
	public JsonNode getMember(@PathVariable UUID id) {
			return om.createObjectNode()
					.put("id", id.toString())
					.put("emailAddress", "foo@example.com")
					.put("point", 100)
					;
	}
}
