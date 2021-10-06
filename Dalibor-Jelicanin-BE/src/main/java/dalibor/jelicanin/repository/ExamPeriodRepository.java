package dalibor.jelicanin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dalibor.jelicanin.entity.ExamPeriodEntity;

public interface ExamPeriodRepository extends JpaRepository<ExamPeriodEntity, Long> {

	@Query(value = "SELECT COUNT(*) FROM exam WHERE exam_period_id = :id", nativeQuery = true)
	int examRelatedToExamPeriod(@Param("id") Long id);
	
}
