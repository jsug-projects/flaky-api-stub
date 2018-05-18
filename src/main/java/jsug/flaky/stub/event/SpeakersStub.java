package jsug.flaky.stub.event;

import java.util.UUID;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/speakers")
public class SpeakersStub {
	
	static class EditForm {
		@NotEmpty
		public String displayName;
		@NotEmpty
		public String profile;
		public String belongTo;
		@NotNull
		public UUID sessionId;
		@NotNull
		public UUID memberId;
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

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete() {
		
	}

}
