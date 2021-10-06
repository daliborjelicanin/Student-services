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

import dalibor.jelicanin.dto.ExamDto;
import dalibor.jelicanin.dto.ProfessorDto;
import dalibor.jelicanin.entity.ExamEntity;
import dalibor.jelicanin.entity.ExamPeriodEntity;
import dalibor.jelicanin.entity.ProfessorEntity;
import dalibor.jelicanin.exception.MyEntityExistException;
import dalibor.jelicanin.exception.MyEntityNotPresentedException;
import dalibor.jelicanin.mapper.ExamMapper;
import dalibor.jelicanin.mapper.ProfessorMapper;
import dalibor.jelicanin.repository.ExamPeriodRepository;
import dalibor.jelicanin.repository.ExamRepository;
import dalibor.jelicanin.service.ExamService;

@Service
@Transactional
public class ExamServiceImpl implements ExamService {

	private final ExamRepository examRepository;
	private final ExamPeriodRepository examPeriodRepository;
	private final ExamMapper examMapper = Mappers.getMapper(ExamMapper.class);
	private final ProfessorMapper professorMapper = Mappers.getMapper(ProfessorMapper.class);

	@Autowired
	public ExamServiceImpl(ExamRepository examRepository, ExamPeriodRepository examPeriodRepository) {
		this.examRepository = examRepository;
		this.examPeriodRepository = examPeriodRepository;
	}

	@Override
	public Optional<ExamDto> findById(Long id) {
		Optional<ExamEntity> exam = examRepository.findById(id);
		if (exam.isPresent()) {
			return Optional.of(examMapper.toDto(exam.get()));
		}
		return Optional.empty();
	}

	@Override
	public List<ExamDto> getAll() {
		List<ExamEntity> entities = (List<ExamEntity>) examRepository.findAll();
		return entities.stream().map(entity -> {
			return examMapper.toDto(entity);
		}).collect(Collectors.toList());
	}

	@Override
	public ExamDto save(ExamDto examDto) throws MyEntityExistException, MyEntityNotPresentedException, Exception {
		Optional<ExamEntity> entity = examRepository.findById(examDto.getId());
		if (entity.isPresent()) {
			throw new MyEntityExistException("Exam already exists!", examMapper.toDto(entity.get()));
		}
		if (dateNotInRangeOfExamPeriod(examDto)) {
			throw new Exception("Can not save exam, date is not in range of exam period.");
		}
		if (examPeriodContainsExam(examDto)) {
			throw new Exception("There is already an exam for that subject in this exam period.");
		}
		
		ExamEntity exam = examRepository.save(examMapper.toEntity(examDto));
		return examMapper.toDto(exam);
	}

	@Override
	public ExamDto update(ExamDto examDto) throws MyEntityNotPresentedException, Exception {
		Optional<ExamEntity> entity = examRepository.findById(examDto.getId());
		if (entity.isPresent()) {
			if (dateNotInRangeOfExamPeriod(examDto)) {
				throw new Exception("Can not save exam, date is not in range of exam period.");
			}
			if (examPeriodContainsExam(examDto)) {
				throw new Exception("There is already an exam for that subject in this exam period.");
			}
			
			ExamEntity examEntity = examRepository.save(examMapper.toEntity(examDto));
			return examMapper.toDto(examEntity);
		} else {
			throw new MyEntityNotPresentedException("Exam with id " + examDto.getId() + " does not exist");
		}
	}

	@Override
	public void delete(Long id) throws Exception {
		Optional<ExamEntity> entity = examRepository.findById(id);
		if (!entity.isPresent()) {
			throw new MyEntityNotPresentedException("Exam with id " + id + " does not exist!");
		}
		int countExams = examRepository.examRegistrationRelatedToExam(id);
		if (countExams > 0) {
			throw new Exception("Can not delete exam with id " + id + ", there are exam registration related to it.");
		}
		
		examRepository.delete(entity.get());
	}
	
	@Override
	public Page<ExamDto> findByPage(Pageable pageable) {
		Page<ExamDto> entites = examRepository.findAll(pageable).map(examMapper::toDto);
		return entites;
	}

	private boolean dateNotInRangeOfExamPeriod(ExamDto examDto) {
		ExamPeriodEntity examPeriod = (examPeriodRepository.findById(examDto.getExamPeriod().getId())).get();
		
			if (!( (examDto.getDate().equals(examPeriod.getStartDate()) || examDto.getDate().isAfter(examPeriod.getStartDate())) &&
				(examDto.getDate().isBefore(examPeriod.getCompletionDate()) || examDto.getDate().equals(examPeriod.getCompletionDate())) )) {
				return true;
			}
		return false;
	}

	private boolean examPeriodContainsExam(ExamDto examDto) {
		List<ExamEntity> entities = (List<ExamEntity>) examRepository.findAll();
		for (ExamEntity exam : entities) {
			if (exam.getSubject().getId() == examDto.getSubject().getId() && 
					exam.getExamPeriod().getId() == examDto.getExamPeriod().getId()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<ExamDto> getAllForActiveExamperiod() {
		List<ExamEntity> entities = (List<ExamEntity>) examRepository.findAllForActiveExamPeriod();
		return entities.stream().map(entity -> {
			return examMapper.toDto(entity);
		}).collect(Collectors.toList());
	}

}







