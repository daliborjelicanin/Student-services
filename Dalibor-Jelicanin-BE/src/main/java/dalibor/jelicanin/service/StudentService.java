package dalibor.jelicanin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dalibor.jelicanin.dto.StudentDto;
import dalibor.jelicanin.exception.MyEntityExistException;
import dalibor.jelicanin.exception.MyEntityNotPresentedException;

public interface StudentService {

	Optional<StudentDto> findById(Long id);

	List<StudentDto> getAll();

	StudentDto save(StudentDto studentDto) throws MyEntityExistException, MyEntityNotPresentedException;

	Optional<StudentDto> update(StudentDto studentDto) throws MyEntityNotPresentedException;

	void delete(Long id) throws MyEntityNotPresentedException, Exception;
	
	Page<StudentDto> findByPage(Pageable pageable);
	
}
