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

import dalibor.jelicanin.dto.ProfessorDto;
import dalibor.jelicanin.exception.MyEntityNotPresentedException;
import dalibor.jelicanin.service.ProfessorService;

@RestController
@RequestMapping(path = "/api/professor")
public class ProfessorRestController {

	private final ProfessorService professorService;

	@Autowired
	public ProfessorRestController(ProfessorService professorService) {
		this.professorService = professorService;
	}
	
	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<Object> findById(@PathVariable Long id) {
		Optional<ProfessorDto> professorDto = professorService.findById(id);
		if(professorDto.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(professorDto.get());
		}else {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body("Professor with id " + id + " does not exist!");
		}
	}
	
	@GetMapping
	public @ResponseBody ResponseEntity<List<ProfessorDto>> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(professorService.getAll());
	}
	
	@GetMapping("/page")
	public @ResponseBody ResponseEntity<Page<ProfessorDto>> getByPage(Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(professorService.findByPage(pageable));
	}
	
	@PostMapping
	public @ResponseBody ResponseEntity<Object> save(@Valid @RequestBody ProfessorDto professorDto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(professorService.save(professorDto));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping
	public @ResponseBody ResponseEntity<ProfessorDto> update(@Valid @RequestBody ProfessorDto professorDto) {
		Optional<ProfessorDto> professor;
		try {
			professor = professorService.update(professorDto);
			
			if (professor.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(professor.get());
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(professorDto);
			}
		} catch (MyEntityNotPresentedException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(professorDto);
		}
	}
	
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<String> delete(@PathVariable(name = "id") Long id) {
		try {
			professorService.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body("Deleted professor with id: " + id);
		} catch (MyEntityNotPresentedException enpe) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(enpe.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping("/engagement/{subjectId}")
	public @ResponseBody ResponseEntity<List<ProfessorDto>> findAllByEngagement(@PathVariable(name = "subjectId") Long subjectId) {
		return ResponseEntity.status(HttpStatus.OK).body(professorService.findAllByEngagement(subjectId));
	}
}
