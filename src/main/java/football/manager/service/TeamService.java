package football.manager.service;

import football.manager.entity.Player;
import football.manager.entity.Team;

import java.util.List;

public interface TeamService {
    Team getById(Long id);

    List<Team> getAllTeams();

    Team createTeam(Team team);

    Team addPlayerToTeam(Long teamId, Long playerId);

    Team update(Team team);

    void delete(Long id);

    List<Player> getAllPlayersByTeamId(Long teamId);
}
