package dalibor.jelicanin.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dalibor.jelicanin.dto.TitleDto;
import dalibor.jelicanin.entity.CityEntity;
import dalibor.jelicanin.entity.TitleEntity;
import dalibor.jelicanin.mapper.TitleMapper;
import dalibor.jelicanin.repository.TitleRepository;
import dalibor.jelicanin.service.TitleService;

@Service
@Transactional
public class TitleServiceImpl implements TitleService {

	private final TitleRepository titleRepository;
	private final TitleMapper titleMapper = Mappers.getMapper(TitleMapper.class);
	
	@Autowired
	public TitleServiceImpl(TitleRepository titleRepository) {
		this.titleRepository = titleRepository;
	}

	@Override
	public List<TitleDto> getAll() {
		List<TitleEntity> entities = (List<TitleEntity>) titleRepository.findAll();
		return entities.stream().map(entity -> {
			return titleMapper.toDto(entity);
		}).collect(Collectors.toList());
	}

}
