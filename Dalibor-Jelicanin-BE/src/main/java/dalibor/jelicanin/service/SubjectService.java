package dalibor.jelicanin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dalibor.jelicanin.dto.SubjectDto;
import dalibor.jelicanin.exception.MyEntityExistException;
import dalibor.jelicanin.exception.MyEntityNotPresentedException;

public interface SubjectService {
	
	Optional<SubjectDto> findById(Long id);

	List<SubjectDto> getAll();

	SubjectDto save(SubjectDto subjectDto) throws MyEntityExistException;

	Optional<SubjectDto> update(SubjectDto subjectDto);

	void delete(Long id) throws MyEntityNotPresentedException, Exception;
	
	Page<SubjectDto> findByPage(Pageable pageable);
}
