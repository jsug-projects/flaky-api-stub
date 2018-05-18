package jsug.flaky.stub.member;

import java.time.LocalDate;
import java.util.UUID;
import java.util.stream.IntStream;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/api/staffs")
public class StaffsStub {
	
	ObjectMapper om = new ObjectMapper();
	
	@GetMapping("/me")
	public JsonNode me() {
		return om.createObjectNode()
			.put("emailAddress", "foo@example.com")
			.put("registeredDate", LocalDate.now().toString());
	}

	@PutMapping("/logout")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void logout() {
		
	}
	
	@GetMapping
	public JsonNode staffs() {
		ArrayNode staffs = om.createArrayNode();
		IntStream.rangeClosed(0, 4).forEach((idx)->{
			UUID id = UUID.randomUUID();
			ObjectNode staff = om.createObjectNode()
					.put("id", id.toString())
					.put("emailAddress", "staff"+idx+"@example.com")
					.put("registeredDate", LocalDate.now().toString())
					;
			staffs.add(staff);
		});
		
		return staffs;
		
	}
	
	
	static class CreateForm {
		@NotNull
		public UUID memberId;
	}
	@PostMapping
	public ResponseEntity<?> create(@Validated @RequestBody CreateForm form) {
		return ResponseEntity
				.created(
						ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(UUID.randomUUID().toString()).toUri())
				.build();		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delte(@PathVariable UUID id) {
		
	}
}
