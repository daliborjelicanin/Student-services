package dalibor.jelicanin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dalibor.jelicanin.entity.ExamEntity;
import dalibor.jelicanin.entity.ProfessorEntity;

@Repository
public interface ExamRepository extends JpaRepository<ExamEntity, Long> {

	@Query(value = "SELECT e.id, e.exam_period_id, e.professor_id, e.subject_id, e.date\r\n"
			+ "FROM Exam e LEFT JOIN Exam_period ep ON(e.exam_period_id = ep.id)\r\n"
			+ "WHERE ep.active = TRUE", nativeQuery = true)
	List<ExamEntity> findAllForActiveExamPeriod();
	
	@Query(value = "SELECT COUNT(*) FROM exam_registration WHERE exam_id = :id", nativeQuery = true)
	int examRegistrationRelatedToExam(@Param("id") Long id);
	
}
