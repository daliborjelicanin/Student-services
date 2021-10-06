package dalibor.jelicanin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dalibor.jelicanin.dto.ExamRegistrationDto;
import dalibor.jelicanin.exception.MyEntityExistException;
import dalibor.jelicanin.exception.MyEntityNotPresentedException;

public interface ExamRegistrationService {

	Optional<ExamRegistrationDto> findById(Long id);

	List<ExamRegistrationDto> getAll();

	ExamRegistrationDto save(ExamRegistrationDto examRegistrationDto) throws MyEntityExistException, MyEntityNotPresentedException, Exception;

	ExamRegistrationDto update(ExamRegistrationDto examRegistrationDto) throws MyEntityNotPresentedException, Exception;

	void delete(Long id) throws MyEntityNotPresentedException;
	
	Page<ExamRegistrationDto> findByPage(Pageable pageable);
	
}
