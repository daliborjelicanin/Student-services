package dalibor.jelicanin.controller.rest;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dalibor.jelicanin.dto.SubjectDto;
import dalibor.jelicanin.exception.MyEntityExistException;
import dalibor.jelicanin.exception.MyEntityNotPresentedException;
import dalibor.jelicanin.service.SubjectService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/api/subject")
public class SubjectRestController {

	private final SubjectService subjectService;

	@Autowired
	public SubjectRestController(SubjectService subjectService) {
		this.subjectService = subjectService;
	}

	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<Object> findById(@PathVariable(name = "id") Long id) {
		Optional<SubjectDto> subjectDto = subjectService.findById(id);
		if(subjectDto.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(subjectDto.get());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Subject with id " + id + " does not exist!");
	}

	@GetMapping
	public @ResponseBody ResponseEntity<List<SubjectDto>> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(subjectService.getAll());
	}

	@GetMapping("/page")
	public @ResponseBody ResponseEntity<Page<SubjectDto>> getByPage(Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(subjectService.findByPage(pageable));
	}

	@PostMapping
	public @ResponseBody ResponseEntity<Object> save(@Valid @RequestBody SubjectDto subjectDto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(subjectService.save(subjectDto));
		} catch (MyEntityExistException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PutMapping
	public @ResponseBody ResponseEntity<SubjectDto> update(@Valid @RequestBody SubjectDto subjectDto) {
		Optional<SubjectDto> city = subjectService.update(subjectDto);
		if (city.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(city.get());
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(subjectDto);
		}
	}

	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<String> delete(@PathVariable(name = "id") Long id) {
		try {
			subjectService.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body("Deleted subject with id:" + id);
		} catch (MyEntityNotPresentedException enpe) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(enpe.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}
