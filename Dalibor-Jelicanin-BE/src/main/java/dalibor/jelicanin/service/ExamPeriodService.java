package dalibor.jelicanin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dalibor.jelicanin.dto.ExamPeriodDto;
import dalibor.jelicanin.exception.MyEntityExistException;
import dalibor.jelicanin.exception.MyEntityNotPresentedException;

public interface ExamPeriodService {

	Optional<ExamPeriodDto> findById(Long id);

	List<ExamPeriodDto> getAll();

	ExamPeriodDto save(ExamPeriodDto examPeriodDto) throws MyEntityExistException, MyEntityNotPresentedException, Exception;

	ExamPeriodDto update(ExamPeriodDto examPeriodDto) throws MyEntityNotPresentedException, Exception;

	void delete(Long id) throws MyEntityNotPresentedException, Exception;
	
	Page<ExamPeriodDto> findByPage(Pageable pageable);
}
