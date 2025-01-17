package football.manager.repository;

import football.manager.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    @Query("SELECT t FROM Team t JOIN FETCH t.players WHERE t.id = :teamId")
    Optional<Team> findByIdWithPlayers(@Param("teamId") Long teamId);

    @Query("SELECT t FROM Team t JOIN t.players p WHERE p.id = :playerId")
    Optional<Team> findTeamByPlayerId(@Param("playerId") Long playerId);

}
