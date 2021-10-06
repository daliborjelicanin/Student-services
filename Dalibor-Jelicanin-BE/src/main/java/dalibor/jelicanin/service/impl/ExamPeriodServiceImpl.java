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

import dalibor.jelicanin.dto.ExamPeriodDto;
import dalibor.jelicanin.dto.ProfessorDto;
import dalibor.jelicanin.entity.ExamPeriodEntity;
import dalibor.jelicanin.exception.MyEntityExistException;
import dalibor.jelicanin.exception.MyEntityNotPresentedException;
import dalibor.jelicanin.mapper.ExamPeriodMapper;
import dalibor.jelicanin.repository.ExamPeriodRepository;
import dalibor.jelicanin.service.ExamPeriodService;

@Service
@Transactional
public class ExamPeriodServiceImpl implements ExamPeriodService {

	private final ExamPeriodRepository examPeriodRepository;
	private final ExamPeriodMapper examPeriodMapper = Mappers.getMapper(ExamPeriodMapper.class);

	@Autowired
	public ExamPeriodServiceImpl(ExamPeriodRepository examPeriodRepository) {
		this.examPeriodRepository = examPeriodRepository;
	}

	@Override
	public Optional<ExamPeriodDto> findById(Long id) {
		Optional<ExamPeriodEntity> examPeriod = examPeriodRepository.findById(id);
		if (examPeriod.isPresent()) {
			return Optional.of(examPeriodMapper.toDto(examPeriod.get()));
		}
		return Optional.empty();
	}

	@Override
	public List<ExamPeriodDto> getAll() {
		List<ExamPeriodEntity> entities = (List<ExamPeriodEntity>) examPeriodRepository.findAll();
		return entities.stream().map(entity -> {
			return examPeriodMapper.toDto(entity);
		}).collect(Collectors.toList());
	}

	@Override
	public ExamPeriodDto save(ExamPeriodDto examPeriodDto) throws Exception {
		Optional<ExamPeriodEntity> entity = examPeriodRepository.findById(examPeriodDto.getId());
		if (entity.isPresent()) {
			throw new MyEntityExistException("Exam period already exists!", examPeriodMapper.toDto(entity.get()));
		}
		if (activeExamPeriodExist() && examPeriodDto.isActive()) {
			throw new Exception("Can not save exam period, only one can bi active at the moment.");
		}
		if (isExamPeriodDateRangeValid(examPeriodDto)) {
			throw new Exception("Invalid date range of exam period.");
		}
		if (examPeriodsOverlap(examPeriodDto)) {
			throw new Exception("Exam periods cannot overlap.");
		}

		ExamPeriodEntity examPeriod = examPeriodRepository.save(examPeriodMapper.toEntity(examPeriodDto));
		return examPeriodMapper.toDto(examPeriod);
	}

	@Override
	public ExamPeriodDto update(ExamPeriodDto examPeriodDto) throws Exception {
		Optional<ExamPeriodEntity> entity = examPeriodRepository.findById(examPeriodDto.getId());
		if (entity.isPresent()) {
			if (activeExamPeriodExist() && examPeriodDto.isActive() && !entity.get().isActive()) {
				throw new Exception("Can not save exam period, only one can bi active at the moment.");
			}
			if (isExamPeriodDateRangeValid(examPeriodDto)) {
				throw new Exception("Invalid date range of exam period.");
			}
			if (examPeriodsOverlap(examPeriodDto)) {
				throw new Exception("Exam periods cannot overlap.");
			}
			
			ExamPeriodEntity examPeriodEntity = examPeriodRepository.save(examPeriodMapper.toEntity(examPeriodDto));
			return examPeriodMapper.toDto(examPeriodEntity);
		} else {
			throw new MyEntityNotPresentedException("Exam period with id " + examPeriodDto.getId() + " does not exist");
		}
	}

	@Override
	public void delete(Long id) throws Exception {
		Optional<ExamPeriodEntity> entity = examPeriodRepository.findById(id);
		if (!entity.isPresent()) {
			throw new MyEntityNotPresentedException("Exam period with id " + id + " does not exist!");
		}
		int countExams = examPeriodRepository.examRelatedToExamPeriod(id);
		if (countExams > 0) {
			throw new Exception("Can not delete exam period with id " + id + ", there are exam related to it.");
		}
		
		examPeriodRepository.delete(entity.get());
	}
	

	@Override
	public Page<ExamPeriodDto> findByPage(Pageable pageable) {
		Page<ExamPeriodDto> entites = examPeriodRepository.findAll(pageable).map(examPeriodMapper::toDto);
		return entites;
	}

	private boolean activeExamPeriodExist() {
		List<ExamPeriodEntity> entities = (List<ExamPeriodEntity>) examPeriodRepository.findAll();
		for (ExamPeriodEntity entity : entities) {
			if (entity.isActive())
				return true;
			break;
		}
		return false;
	}

	private boolean examPeriodsOverlap(ExamPeriodDto epDto) {
		List<ExamPeriodEntity> entities = (List<ExamPeriodEntity>) examPeriodRepository.findAll();
		for (ExamPeriodEntity ep : entities) {
			if (epDto.getId() == ep.getId()) continue;
			if ((epDto.getStartDate().isBefore(ep.getCompletionDate())
					&& epDto.getCompletionDate().isAfter(ep.getCompletionDate()))
					|| (epDto.getStartDate().isBefore(ep.getStartDate())
							&& epDto.getCompletionDate().isAfter(ep.getStartDate()))
					|| (epDto.getStartDate().isAfter(ep.getStartDate())
							&& epDto.getCompletionDate().isBefore(ep.getCompletionDate()))
					|| (epDto.getStartDate().equals(ep.getStartDate())
							|| epDto.getStartDate().equals(ep.getCompletionDate())
							|| epDto.getCompletionDate().equals(ep.getStartDate())
							|| epDto.getCompletionDate().equals(ep.getCompletionDate()))) {
				return true;
			}
		}
		return false;
	}

	private boolean isExamPeriodDateRangeValid(ExamPeriodDto epDto) {
		if (epDto.getStartDate().isAfter(epDto.getCompletionDate())
				|| epDto.getCompletionDate().isBefore(epDto.getStartDate())) {
			return true;
		}
		return false;
	}
}
