package dalibor.jelicanin.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dalibor.jelicanin.entity.StudentEntity;

@Repository
public interface StudentRepository extends PagingAndSortingRepository<StudentEntity, Long> {

	@Query(value = "SELECT COUNT(*) FROM exam_registration WHERE student_id = :id", nativeQuery = true)
	int examRegistrationRelatedToStudent(@Param("id") Long id);
	
}
