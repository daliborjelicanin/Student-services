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

import dalibor.jelicanin.dto.SubjectDto;
import dalibor.jelicanin.entity.Semester;
import dalibor.jelicanin.entity.SubjectEntity;
import dalibor.jelicanin.exception.MyEntityExistException;
import dalibor.jelicanin.exception.MyEntityNotPresentedException;
import dalibor.jelicanin.mapper.SubjectMapper;
import dalibor.jelicanin.repository.SubjectRepository;
import dalibor.jelicanin.service.SubjectService;

@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {

	private final SubjectRepository subjectRepository;
	private final SubjectMapper subjectMapper = Mappers.getMapper(SubjectMapper.class);

	@Autowired
	public SubjectServiceImpl(SubjectRepository subjectRepository) {
		this.subjectRepository = subjectRepository;
	}

	@Override
	public Optional<SubjectDto> findById(Long id) {
		Optional<SubjectEntity> subject = subjectRepository.findById(id);
		if (subject.isPresent()) {
			return Optional.of(subjectMapper.toDto(subject.get()));
		}
		return Optional.empty();
	}

	@Override
	public List<SubjectDto> getAll() {
		List<SubjectEntity> entities = (List<SubjectEntity>) subjectRepository.findAll();
		return entities.stream().map(entity -> {
			return subjectMapper.toDto(entity);
		}).collect(Collectors.toList());
	}

	@Override
	public SubjectDto save(SubjectDto subjectDto) throws MyEntityExistException {
		Optional<SubjectEntity> entity = subjectRepository.findById(subjectDto.getId());
		if (entity.isPresent()) {
			throw new MyEntityExistException("Subject already exists!", subjectMapper.toDto(entity.get()));
		}

		SubjectEntity subject = subjectRepository.save(subjectMapper.toEntity(subjectDto));
		return subjectMapper.toDto(subject);
	}

	@Override
	public Optional<SubjectDto> update(SubjectDto subjectDto) {
		Optional<SubjectEntity> entity = subjectRepository.findById(subjectDto.getId());
		if (entity.isPresent()) {
			SubjectEntity subjectEntity = subjectRepository.save(subjectMapper.toEntity(subjectDto));
			return Optional.of(subjectMapper.toDto(subjectEntity));
		}

		return Optional.empty();
	}

	@Override
	public void delete(Long id) throws Exception {
		Optional<SubjectEntity> entity = subjectRepository.findById(id);
		if (!entity.isPresent()) {
			throw new MyEntityNotPresentedException("Subject with id " + id + " does not exist!");
		}
		int countEngagements = subjectRepository.engagementRelatedToSubject(id);
		if (countEngagements> 0) {
			throw new Exception("Can not delete subject with id " + id + ", there are professor engagement related to it.");
		}
		int countExams = subjectRepository.examRelatedToSubject(id);
		if (countExams> 0) {
			throw new Exception("Can not delete subject with id " + id + ", there are exam related to it.");
		}
			
		subjectRepository.delete(entity.get());
	}

	@Override
	public Page<SubjectDto> findByPage(Pageable pageable) {
		Page<SubjectDto> entites = subjectRepository.findAll(pageable).map(subjectMapper::toDto);
		return entites;
	}

}
