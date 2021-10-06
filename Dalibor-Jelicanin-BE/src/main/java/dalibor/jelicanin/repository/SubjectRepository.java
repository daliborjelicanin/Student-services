package dalibor.jelicanin.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dalibor.jelicanin.entity.SubjectEntity;

@Repository
public interface SubjectRepository extends PagingAndSortingRepository<SubjectEntity, Long>{

	@Query(value = "SELECT COUNT(*) FROM professor_engagement WHERE subject_id = :id", nativeQuery = true)
	int engagementRelatedToSubject(@Param("id") Long id);
	
	@Query(value = "SELECT COUNT(*) FROM exam WHERE subject_id = :id", nativeQuery = true)
	int examRelatedToSubject(@Param("id") Long id);
	
}
