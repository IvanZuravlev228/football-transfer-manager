package football.manager.repository;

import football.manager.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    @Query("SELECT p FROM Player p WHERE p.id NOT IN (SELECT tp.id FROM Team t JOIN t.players tp)")
    List<Player> findPlayersWithoutTeams();

}
