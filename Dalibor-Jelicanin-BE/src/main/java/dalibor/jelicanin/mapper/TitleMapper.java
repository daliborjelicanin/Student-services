package dalibor.jelicanin.mapper;

import org.mapstruct.Mapper;

import dalibor.jelicanin.dto.TitleDto;
import dalibor.jelicanin.entity.TitleEntity;

@Mapper
public interface TitleMapper {

	TitleDto toDto(TitleEntity entity);

	TitleEntity toEntity(TitleDto dto);
}
