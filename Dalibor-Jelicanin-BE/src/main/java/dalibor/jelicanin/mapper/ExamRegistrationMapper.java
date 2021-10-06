package dalibor.jelicanin.mapper;

import org.mapstruct.Mapper;

import dalibor.jelicanin.dto.ExamRegistrationDto;
import dalibor.jelicanin.entity.ExamRegistrationEntity;

@Mapper
public interface ExamRegistrationMapper {

	ExamRegistrationDto toDto(ExamRegistrationEntity entity);

	ExamRegistrationEntity toEntity(ExamRegistrationDto dto);
}
