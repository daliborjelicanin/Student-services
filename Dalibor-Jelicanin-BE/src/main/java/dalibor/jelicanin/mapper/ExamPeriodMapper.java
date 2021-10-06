package dalibor.jelicanin.mapper;

import org.mapstruct.Mapper;

import dalibor.jelicanin.dto.ExamPeriodDto;
import dalibor.jelicanin.entity.ExamPeriodEntity;

@Mapper(uses = ExamMapper.class)
public interface ExamPeriodMapper {

	ExamPeriodDto toDto(ExamPeriodEntity entity);

	ExamPeriodEntity toEntity(ExamPeriodDto dto);
	
}
