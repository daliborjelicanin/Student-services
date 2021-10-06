package dalibor.jelicanin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dalibor.jelicanin.entity.ExamRegistrationEntity;

@Repository
public interface ExamRegistrationRepository extends JpaRepository<ExamRegistrationEntity, Long> {

	@Query(value = "SELECT er.id, er.student_id, er.exam_id, er.date\r\n"
			+ "FROM exam_registration er\r\n"
			+ "WHERE student_id = :studentId AND exam_id = :examId", nativeQuery = true)
	Optional<ExamRegistrationEntity> getById(@Param("studentId") Long studentId, @Param("examId") Long examId);
}
