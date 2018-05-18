package jsug.flaky.stub.event;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
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
@RequestMapping("/api/sessions")
public class SessionsStub {
	
	
	static class EditForm {
		@NotEmpty
		public String title;
		@NotEmpty
		public String overview;
		@DateTimeFormat(iso=ISO.DATE_TIME)
		public LocalDateTime startDateTime;
		@DateTimeFormat(iso=ISO.DATE_TIME)
		public LocalDateTime endDateTime;		
		@NotNull
		public UUID eventId;
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

