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

import dalibor.jelicanin.dto.ExamPeriodDto;
import dalibor.jelicanin.exception.MyEntityNotPresentedException;
import dalibor.jelicanin.service.ExamPeriodService;

@RestController
@RequestMapping(path = "/api/exam-period")
public class ExamPeriodRestController {

	private final ExamPeriodService examPeriodService;

	@Autowired
	public ExamPeriodRestController(ExamPeriodService examPeriodService) {
		this.examPeriodService = examPeriodService;
	}

	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<Object> findById(@PathVariable Long id) {
		Optional<ExamPeriodDto> examPeriodDto = examPeriodService.findById(id);
		if (examPeriodDto.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(examPeriodDto.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exam period with id " + id + " does not exist!");
		}
	}

	@GetMapping
	public @ResponseBody ResponseEntity<List<ExamPeriodDto>> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(examPeriodService.getAll());
	}

	@GetMapping("/page")
	public @ResponseBody ResponseEntity<Page<ExamPeriodDto>> getByPage(Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(examPeriodService.findByPage(pageable));
	}
	
	@PostMapping
	public @ResponseBody ResponseEntity<Object> save(@Valid @RequestBody ExamPeriodDto examPeriodDto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(examPeriodService.save(examPeriodDto));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PutMapping
	public @ResponseBody ResponseEntity<Object> update(@Valid @RequestBody ExamPeriodDto examPeriodDto) {
		ExamPeriodDto examPeriod;
		try {
			examPeriod = examPeriodService.update(examPeriodDto);

			return ResponseEntity.status(HttpStatus.OK).body(examPeriod);
		} catch (MyEntityNotPresentedException enpe) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(enpe.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<String> delete(@PathVariable(name = "id") Long id) {
		try {
			examPeriodService.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body("Deleted exam period with id: " + id);
		} catch (MyEntityNotPresentedException enpe) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(enpe.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

}
