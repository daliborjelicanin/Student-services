package dalibor.jelicanin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dalibor.jelicanin.entity.ProfessorEntity;

@Repository
public interface ProfessorRepository extends PagingAndSortingRepository<ProfessorEntity, Long> {

	@Query(value = "SELECT p.id, p.first_name, p.last_name, p.email, p.address, p.phone, p.reelection_date, p.postal_code, p.title "
			+ "FROM Professor_engagement pe LEFT JOIN Professor p ON(pe.professor_id = p.id) "
			+ "WHERE pe.subject_id = :id", nativeQuery = true)
	List<ProfessorEntity> findAllByEngagement(@Param("id") Long id);
	

	@Query(value = "SELECT COUNT(*) FROM professor_engagement WHERE professor_id = :id", nativeQuery = true)
	int engagementRelatedToProfessor(@Param("id") Long id);
	
	@Query(value = "SELECT COUNT(*) FROM exam WHERE professor_id = :id", nativeQuery = true)
	int examRelatedToProfessor(@Param("id") Long id);

}
