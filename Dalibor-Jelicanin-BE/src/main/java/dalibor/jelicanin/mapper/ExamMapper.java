package dalibor.jelicanin.mapper;

import org.mapstruct.Mapper;

import dalibor.jelicanin.dto.ExamDto;
import dalibor.jelicanin.entity.ExamEntity;

@Mapper
public interface ExamMapper {

	ExamDto toDto(ExamEntity entity);

	ExamEntity toEntity(ExamDto dto);
	
}
