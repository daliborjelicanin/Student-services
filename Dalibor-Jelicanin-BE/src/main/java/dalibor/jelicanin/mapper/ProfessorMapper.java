package dalibor.jelicanin.mapper;

import org.mapstruct.Mapper;

import dalibor.jelicanin.dto.ProfessorDto;
import dalibor.jelicanin.entity.ProfessorEntity;

@Mapper(uses = {CityMapper.class, TitleMapper.class, SubjectMapper.class})
public interface ProfessorMapper {

	ProfessorDto toDto(ProfessorEntity entity);

	ProfessorEntity toEntity(ProfessorDto dto);
}
