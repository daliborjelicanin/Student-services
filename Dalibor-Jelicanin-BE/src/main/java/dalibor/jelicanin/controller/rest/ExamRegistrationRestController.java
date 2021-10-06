package dalibor.jelicanin.controller.rest;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dalibor.jelicanin.dto.ExamRegistrationDto;
import dalibor.jelicanin.exception.MyEntityNotPresentedException;
import dalibor.jelicanin.service.ExamRegistrationService;

@RestController
@RequestMapping(path = "/api/exam-registration")
public class ExamRegistrationRestController {

	private final ExamRegistrationService examRegistrationService;

	@Autowired
	public ExamRegistrationRestController(ExamRegistrationService examRegistrationService) {
		this.examRegistrationService = examRegistrationService;
	}
	
	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<Object> findById(@PathVariable Long id) {
		Optional<ExamRegistrationDto> examRegistrationDto = examRegistrationService.findById(id);
		if (examRegistrationDto.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(examRegistrationDto.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exam registration with id " + id + " does not exist!");
		}
	}

	@GetMapping
	public @ResponseBody ResponseEntity<List<ExamRegistrationDto>> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(examRegistrationService.getAll());
	}

	@GetMapping("/page")
	public @ResponseBody ResponseEntity<Page<ExamRegistrationDto>> getByPage(Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(examRegistrationService.findByPage(pageable));
	}
	
	@PostMapping
	public @ResponseBody ResponseEntity<Object> save(@Valid @RequestBody ExamRegistrationDto examRegistrationDto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(examRegistrationService.save(examRegistrationDto));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PutMapping
	public @ResponseBody ResponseEntity<Object> update(@Valid @RequestBody ExamRegistrationDto examRegistrationDto) {
		try {
			ExamRegistrationDto examRegistration = examRegistrationService.update(examRegistrationDto);

			return ResponseEntity.status(HttpStatus.OK).body(examRegistration);
		} catch (MyEntityNotPresentedException enpe) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(enpe.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<String> delete(@PathVariable(name = "id") Long id) {
		try {
			examRegistrationService.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body("Deleted exam registration with id: " + id);
		} catch (MyEntityNotPresentedException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
}
