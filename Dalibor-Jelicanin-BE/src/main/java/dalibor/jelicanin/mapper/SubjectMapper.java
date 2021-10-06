package dalibor.jelicanin.mapper;

import org.mapstruct.Mapper;

import dalibor.jelicanin.dto.SubjectDto;
import dalibor.jelicanin.entity.SubjectEntity;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

	SubjectDto toDto(SubjectEntity entity);

	SubjectEntity toEntity(SubjectDto dto);
}
