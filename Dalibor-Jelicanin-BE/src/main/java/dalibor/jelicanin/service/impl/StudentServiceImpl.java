package dalibor.jelicanin.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dalibor.jelicanin.dto.StudentDto;
import dalibor.jelicanin.entity.CityEntity;
import dalibor.jelicanin.entity.StudentEntity;
import dalibor.jelicanin.exception.MyEntityExistException;
import dalibor.jelicanin.exception.MyEntityNotPresentedException;
import dalibor.jelicanin.mapper.StudentMapper;
import dalibor.jelicanin.repository.CityRepository;
import dalibor.jelicanin.repository.StudentRepository;
import dalibor.jelicanin.service.StudentService;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

	private final StudentRepository studentRepository;
	private final CityRepository cityRepository;
	private final StudentMapper studentMapper = Mappers.getMapper(StudentMapper.class);

	@Autowired
	public StudentServiceImpl(StudentRepository studentRepository, CityRepository cityRepository) {
		this.studentRepository = studentRepository;
		this.cityRepository = cityRepository;
	}

	@Override
	public Optional<StudentDto> findById(Long id) {
		Optional<StudentEntity> student = studentRepository.findById(id);
		if (student.isPresent()) {
			return Optional.of(studentMapper.toDto(student.get()));
		}
		return Optional.empty();
	}

	@Override
	public List<StudentDto> getAll() {
		List<StudentEntity> entities = (List<StudentEntity>) studentRepository.findAll();
		return entities.stream().map(entity -> {
			return studentMapper.toDto(entity);
		}).collect(Collectors.toList());
	}

	@Override
	public StudentDto save(StudentDto studentDto) throws MyEntityNotPresentedException, MyEntityExistException {
		Optional<CityEntity> cityEntity = cityRepository.findById(studentDto.getCity().getPostalCode());
		if (!cityEntity.isPresent()) {
			throw new MyEntityNotPresentedException(
					"City with code " + studentDto.getCity().getPostalCode() + " does not exist!");
		}

		Optional<StudentEntity> entity = studentRepository.findById(studentDto.getId());
		if (entity.isPresent()) {
			throw new MyEntityExistException("Student already exists!", studentMapper.toDto(entity.get()));
		}

		StudentEntity studentEntity = studentRepository.save(studentMapper.toEntity(studentDto));
		return studentMapper.toDto(studentEntity);
	}

	@Override
	public Optional<StudentDto> update(StudentDto studentDto) throws MyEntityNotPresentedException {
		Optional<CityEntity> cityEntity = cityRepository.findById(studentDto.getCity().getPostalCode());
		if (!cityEntity.isPresent()) {
			throw new MyEntityNotPresentedException(
					"City with code " + studentDto.getCity().getPostalCode() + " does not exist!");
		}

		Optional<StudentEntity> entity = studentRepository.findById(studentDto.getId());
		if (entity.isPresent()) {
			StudentEntity studentEntity = studentRepository.save(studentMapper.toEntity(studentDto));
			return Optional.of(studentMapper.toDto(studentEntity));
		} else {
			throw new MyEntityNotPresentedException("Student with id " + studentDto.getId() + " does not exist!");
		}
	}

	@Override
	public void delete(Long id) throws Exception {
		Optional<StudentEntity> entity = studentRepository.findById(id);
		if (!entity.isPresent()) {
			throw new MyEntityNotPresentedException("Student with id " + id + " does not exist!");
		}
		int countExams = studentRepository.examRegistrationRelatedToStudent(id);
		if (countExams> 0) {
			throw new Exception("Can not delete student with id " + id + ", there are exam registration related to it.");
		}
		
		studentRepository.delete(entity.get());
	}

	@Override
	public Page<StudentDto> findByPage(Pageable pageable) {
		Page<StudentDto> entites = studentRepository.findAll(pageable).map(studentMapper::toDto);
		return entites;
	}

}
