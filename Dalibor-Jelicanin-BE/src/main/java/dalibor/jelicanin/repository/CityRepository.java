package dalibor.jelicanin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dalibor.jelicanin.entity.CityEntity;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Long>{

}
