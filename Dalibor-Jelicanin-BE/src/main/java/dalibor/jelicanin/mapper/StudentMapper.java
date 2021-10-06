package dalibor.jelicanin.mapper;

import org.mapstruct.Mapper;

import dalibor.jelicanin.dto.StudentDto;
import dalibor.jelicanin.entity.StudentEntity;

@Mapper(uses = {CityMapper.class})
public interface StudentMapper {
	
	StudentDto toDto(StudentEntity entity);

	StudentEntity toEntity(StudentDto dto);

}
