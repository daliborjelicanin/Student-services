package dalibor.jelicanin.service.impl;

import java.time.LocalDate;
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
import dalibor.jelicanin.dto.ExamRegistrationDto;
import dalibor.jelicanin.dto.StudentDto;
import dalibor.jelicanin.entity.ExamEntity;
import dalibor.jelicanin.entity.ExamRegistrationEntity;
import dalibor.jelicanin.exception.MyEntityExistException;
import dalibor.jelicanin.exception.MyEntityNotPresentedException;
import dalibor.jelicanin.mapper.ExamRegistrationMapper;
import dalibor.jelicanin.repository.ExamRegistrationRepository;
import dalibor.jelicanin.service.ExamRegistrationService;

@Service
@Transactional
public class ExamRegistrationServiceImpl implements ExamRegistrationService{

	private final ExamRegistrationRepository examRegistrationRepository;
	private final ExamRegistrationMapper examRegistrationMapper = Mappers.getMapper(ExamRegistrationMapper.class);
	
	@Autowired
	public ExamRegistrationServiceImpl(ExamRegistrationRepository examRegistrationRepository) {
		this.examRegistrationRepository = examRegistrationRepository;
	}

	@Override
	public Optional<ExamRegistrationDto> findById(Long id) {
		Optional<ExamRegistrationEntity> exam = examRegistrationRepository.findById(id);
		if (exam.isPresent()) {
			return Optional.of(examRegistrationMapper.toDto(exam.get()));
		}
		return Optional.empty();
	}

	@Override
	public List<ExamRegistrationDto> getAll() {
		List<ExamRegistrationEntity> entities = (List<ExamRegistrationEntity>) examRegistrationRepository.findAll();
		return entities.stream().map(entity -> {
			return examRegistrationMapper.toDto(entity);
		}).collect(Collectors.toList());
	}

	@Override
	public ExamRegistrationDto save(ExamRegistrationDto examRegistrationDto)
			throws MyEntityExistException, MyEntityNotPresentedException, Exception {
		Optional<ExamRegistrationEntity> entity = examRegistrationRepository.
				getById(examRegistrationDto.getStudent().getId(), examRegistrationDto.getExam().getId());
		if (entity.isPresent()) {
			throw new MyEntityExistException("Exam registration already exists!", examRegistrationMapper.toDto(entity.get()));
		}
		if (!(studentAndSubjectYearOfStudyValid(examRegistrationDto))) {
			throw new Exception("Student can not register exam for that subject, he's current year of study is lower then year of study for that subject.");
		}
		if (!(oneWeekBeforeActiveExamPeriod(examRegistrationDto))) {
			throw new Exception("Registration for an exam is possible only in the last week before beginning of active exam period.");
		}
		
		ExamRegistrationEntity examRegistration = examRegistrationRepository.save(examRegistrationMapper.toEntity(examRegistrationDto));
		return examRegistrationMapper.toDto(examRegistration);
	}

	@Override
	public ExamRegistrationDto update(ExamRegistrationDto examRegistrationDto)
			throws MyEntityNotPresentedException, Exception {
		Optional<ExamRegistrationEntity> entity = examRegistrationRepository.findById(examRegistrationDto.getId());
		if (entity.isPresent()) {
			if (!(studentAndSubjectYearOfStudyValid(examRegistrationDto))) {
				throw new Exception("Student can not register exam for that subject, he's current year of study is lower then year of study for that subject.");
			}
			if (!(oneWeekBeforeActiveExamPeriod(examRegistrationDto))) {
				throw new Exception("Registration for an exam is possible only in the last week before beginning of active exam period.");
			}
			
			ExamRegistrationEntity examRegistrationEntity = examRegistrationRepository.save(examRegistrationMapper.toEntity(examRegistrationDto));
			return examRegistrationMapper.toDto(examRegistrationEntity);
		} else {
			throw new MyEntityNotPresentedException("Exam registration with id " + examRegistrationDto.getId() + " does not exist");
		}
	}

	@Override
	public void delete(Long id) throws MyEntityNotPresentedException {
		Optional<ExamRegistrationEntity> entity = examRegistrationRepository.findById(id);
		if (!entity.isPresent()) {
			throw new MyEntityNotPresentedException("Exam registration with id " + id + " does not exist!");
		}
		
		examRegistrationRepository.delete(entity.get());
	}

	@Override
	public Page<ExamRegistrationDto> findByPage(Pageable pageable) {
		Page<ExamRegistrationDto> entites = examRegistrationRepository.findAll(pageable).map(examRegistrationMapper::toDto);
		return entites;
	}
	
	private boolean studentAndSubjectYearOfStudyValid(ExamRegistrationDto examRegistrationDto) {
		//for (ExamDto exam : examRegistrationDto.getExam()) {
			if (examRegistrationDto.getStudent().getCurrentYearOfStudy() >= examRegistrationDto.getExam().getSubject().getYearOfStudy()) {
				return true;
			}
		//}
		return false;
	}

	private boolean oneWeekBeforeActiveExamPeriod(ExamRegistrationDto examRegistrationDto) {
		//for (ExamDto exam : examRegistrationDto.getStudent().getExams()) {
			if (LocalDate.now().isAfter(examRegistrationDto.getExam().getExamPeriod().getStartDate().minusWeeks(1).minusDays(1))
					&& LocalDate.now().isBefore(examRegistrationDto.getExam().getExamPeriod().getStartDate())) {
				return true;
			}
		//}
		return false;
	}

}
