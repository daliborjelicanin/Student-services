package dalibor.jelicanin.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dalibor.jelicanin.dto.CityDto;
import dalibor.jelicanin.entity.CityEntity;
import dalibor.jelicanin.mapper.CityMapper;
import dalibor.jelicanin.repository.CityRepository;
import dalibor.jelicanin.service.CityService;

@Service
@Transactional
public class CityServiceImpl implements CityService {

	private final CityRepository cityRepository;
	private final CityMapper cityMapper = Mappers.getMapper(CityMapper.class);
	
	@Autowired
	public CityServiceImpl(CityRepository cityRepository) {
		this.cityRepository = cityRepository;
	}

	@Override
	public List<CityDto> getAll() {
		List<CityEntity> entities = (List<CityEntity>) cityRepository.findAll();
		return entities.stream().map(entity -> {
			return cityMapper.toDto(entity);
		}).collect(Collectors.toList());
	}

}
