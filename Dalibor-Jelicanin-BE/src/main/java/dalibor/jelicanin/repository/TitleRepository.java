package dalibor.jelicanin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dalibor.jelicanin.entity.TitleEntity;

@Repository
public interface TitleRepository extends JpaRepository<TitleEntity, Long> {

}
