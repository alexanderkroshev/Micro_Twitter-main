package all.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query(value = "select exists(select 1 from person where nickname=:nickname)", nativeQuery = true)
    Boolean existsByNickname(@Param("nickname") String nickname);

    @Query(value = "select * from person where nickname=:nickname", nativeQuery = true)
    Person findByNickname(@Param("nickname") String nickname);


}