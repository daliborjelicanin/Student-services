package dalibor.jelicanin.mapper;

import org.mapstruct.Mapper;

import dalibor.jelicanin.dto.CityDto;
import dalibor.jelicanin.entity.CityEntity;

@Mapper()
public interface CityMapper {

	CityDto toDto(CityEntity entity);

	CityEntity toEntity(CityDto dto);
	
}
