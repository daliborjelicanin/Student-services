package dalibor.jelicanin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dalibor.jelicanin.dto.ExamDto;
import dalibor.jelicanin.dto.ProfessorDto;
import dalibor.jelicanin.exception.MyEntityExistException;
import dalibor.jelicanin.exception.MyEntityNotPresentedException;

public interface ExamService {

	Optional<ExamDto> findById(Long id);

	List<ExamDto> getAll();

	ExamDto save(ExamDto examDto) throws MyEntityExistException, MyEntityNotPresentedException, Exception;

	ExamDto update(ExamDto examDto) throws MyEntityNotPresentedException, Exception;

	void delete(Long id) throws MyEntityNotPresentedException, Exception;
	
	Page<ExamDto> findByPage(Pageable pageable);
	
	List<ExamDto> getAllForActiveExamperiod();
	
}
