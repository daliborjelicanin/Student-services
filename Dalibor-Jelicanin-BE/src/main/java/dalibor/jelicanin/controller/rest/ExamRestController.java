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

import dalibor.jelicanin.dto.ExamDto;
import dalibor.jelicanin.dto.ExamPeriodDto;
import dalibor.jelicanin.dto.ProfessorDto;
import dalibor.jelicanin.exception.MyEntityNotPresentedException;
import dalibor.jelicanin.service.ExamService;

@RestController
@RequestMapping(path = "/api/exam")
public class ExamRestController {

	private final ExamService examService;

	@Autowired
	public ExamRestController(ExamService examService) {
		this.examService = examService;
	}
	
	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<Object> findById(@PathVariable Long id) {
		Optional<ExamDto> examDto = examService.findById(id);
		if (examDto.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(examDto.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exam with id " + id + " does not exist!");
		}
	}

	@GetMapping
	public @ResponseBody ResponseEntity<List<ExamDto>> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(examService.getAll());
	}
	
	@GetMapping("/active")
	public @ResponseBody ResponseEntity<List<ExamDto>> getAllForActiveExamPeriod() {
		return ResponseEntity.status(HttpStatus.OK).body(examService.getAllForActiveExamperiod());
	}

	@GetMapping("/page")
	public @ResponseBody ResponseEntity<Page<ExamDto>> getByPage(Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(examService.findByPage(pageable));
	}
	
	@PostMapping
	public @ResponseBody ResponseEntity<Object> save(@Valid @RequestBody ExamDto examDto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(examService.save(examDto));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PutMapping
	public @ResponseBody ResponseEntity<Object> update(@Valid @RequestBody ExamDto examDto) {
		try {
			ExamDto exam = examService.update(examDto);

			return ResponseEntity.status(HttpStatus.OK).body(exam);
		} catch (MyEntityNotPresentedException enpe) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(enpe.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<String> delete(@PathVariable(name = "id") Long id) {
		try {
			examService.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body("Deleted exam with id: " + id);
		} catch (MyEntityNotPresentedException enpe) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(enpe.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
}
