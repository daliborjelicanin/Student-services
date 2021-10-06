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

import dalibor.jelicanin.dto.ProfessorDto;
import dalibor.jelicanin.entity.CityEntity;
import dalibor.jelicanin.entity.ProfessorEntity;
import dalibor.jelicanin.entity.TitleEntity;
import dalibor.jelicanin.exception.MyEntityExistException;
import dalibor.jelicanin.exception.MyEntityNotPresentedException;
import dalibor.jelicanin.mapper.ProfessorMapper;
import dalibor.jelicanin.repository.CityRepository;
import dalibor.jelicanin.repository.ProfessorRepository;
import dalibor.jelicanin.repository.TitleRepository;
import dalibor.jelicanin.service.ProfessorService;

@Service
@Transactional
public class ProfessorServiceImpl implements ProfessorService {

	private final ProfessorRepository professorRepository;
	private final CityRepository cityRepository;
	private final TitleRepository titleRepository;
	private final ProfessorMapper professorMapper = Mappers.getMapper(ProfessorMapper.class);
	
	@Autowired
	public ProfessorServiceImpl(ProfessorRepository professorRepository, CityRepository cityRepository, TitleRepository titleRepository) {
		this.professorRepository = professorRepository;
		this.cityRepository = cityRepository;
		this.titleRepository = titleRepository;
	}

	@Override
	public Optional<ProfessorDto> findById(Long id) {
		Optional<ProfessorEntity> professor = professorRepository.findById(id);
		if (professor.isPresent()) {
			return Optional.of(professorMapper.toDto(professor.get()));
		}
		return Optional.empty();
	}

	@Override
	public List<ProfessorDto> getAll() {
		 List<ProfessorEntity> entities = (List<ProfessorEntity>) professorRepository.findAll();
		 return entities.stream().map(entity->{
			 return professorMapper.toDto(entity);
		 }).collect(Collectors.toList());
	}

	@Override
	public ProfessorDto save(ProfessorDto professorDto) throws MyEntityExistException, MyEntityNotPresentedException {
		Optional<CityEntity> cityEntity = cityRepository.findById(professorDto.getCity().getPostalCode());
		if(!cityEntity.isPresent()) {
			throw new MyEntityNotPresentedException("City with postal code " + professorDto.getCity().getPostalCode() + " does not exist!");
		}
		
		Optional<TitleEntity> titleEntity = titleRepository.findById(professorDto.getTitle().getId());
		if(!titleEntity.isPresent()) {
			throw new MyEntityNotPresentedException("Title with id " + professorDto.getTitle().getId() + " does not exist!");
		}
		
		Optional<ProfessorEntity> entity = professorRepository.findById(professorDto.getId());
		
		if (entity.isPresent()) {
			throw new MyEntityExistException("Professor already exists!", professorMapper.toDto(entity.get()));
		}
		
		ProfessorEntity professorEntity =  professorRepository.save(professorMapper.toEntity(professorDto));
		return professorMapper.toDto(professorEntity);
	}

	@Override
	public Optional<ProfessorDto> update(ProfessorDto professorDto) throws MyEntityNotPresentedException {
		Optional<CityEntity> cityEntity = cityRepository.findById(professorDto.getCity().getPostalCode());
		if(!cityEntity.isPresent()) {
			throw new MyEntityNotPresentedException("City with code " + professorDto.getCity().getPostalCode() + " does not exist!");
		}
		
		Optional<TitleEntity> titleEntity = titleRepository.findById(professorDto.getTitle().getId());
		if(!titleEntity.isPresent()) {
			throw new MyEntityNotPresentedException("Title with id " + professorDto.getTitle().getId() + " does not exist!");
		}
		
		Optional<ProfessorEntity> entity = professorRepository.findById(professorDto.getId());
		if (entity.isPresent()) {
			ProfessorEntity professorEntity = professorRepository.save(professorMapper.toEntity(professorDto));
			return Optional.of(professorMapper.toDto(professorEntity));
		} else {
			throw new MyEntityNotPresentedException("Professor with id " + professorDto.getId() + " does not exist!");
		}
	}

	@Override
	public void delete(Long id) throws Exception {
		Optional<ProfessorEntity> entity = professorRepository.findById(id);
		if(!entity.isPresent()) {
			throw new MyEntityNotPresentedException("Professor with id "+ id + " does not exist!");
		}
		int countEngagements = professorRepository.engagementRelatedToProfessor(id);
		if (countEngagements> 0) {
			throw new Exception("Can not delete professor with id " + id + ", there are professor engagement related to it.");
		}
		int countExams = professorRepository.examRelatedToProfessor(id);
		if (countExams> 0) {
			throw new Exception("Can not delete professor with id " + id + ", there are exam related to it.");
		}
		
		professorRepository.delete(entity.get());
	}

	@Override
	public Page<ProfessorDto> findByPage(Pageable pageable) {
		Page<ProfessorDto> entites = professorRepository.findAll(pageable).map(professorMapper::toDto);
		return entites;
	}

	@Override
	public List<ProfessorDto> findAllByEngagement(Long id) {
		 List<ProfessorEntity> entities = (List<ProfessorEntity>) professorRepository.findAllByEngagement(id);
		 return entities.stream().map(entity->{
			 return professorMapper.toDto(entity);
		 }).collect(Collectors.toList());
	}

}
