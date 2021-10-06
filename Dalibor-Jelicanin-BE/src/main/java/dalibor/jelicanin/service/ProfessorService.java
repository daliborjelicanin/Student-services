package dalibor.jelicanin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dalibor.jelicanin.dto.ProfessorDto;
import dalibor.jelicanin.exception.MyEntityExistException;
import dalibor.jelicanin.exception.MyEntityNotPresentedException;

public interface ProfessorService {

	Optional<ProfessorDto> findById(Long id);

	List<ProfessorDto> getAll();

	ProfessorDto save(ProfessorDto professorDto) throws MyEntityExistException, MyEntityNotPresentedException;

	Optional<ProfessorDto> update(ProfessorDto professorDto) throws MyEntityNotPresentedException;

	void delete(Long id) throws MyEntityNotPresentedException, Exception;
	
	Page<ProfessorDto> findByPage(Pageable pageable);
	
	List<ProfessorDto> findAllByEngagement(Long id);
}
