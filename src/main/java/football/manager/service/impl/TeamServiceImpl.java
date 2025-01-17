package football.manager.service.impl;

import football.manager.entity.Player;
import football.manager.entity.Team;
import football.manager.exception.PlayerAlreadyInTeamException;
import football.manager.repository.TeamRepository;
import football.manager.service.PlayerService;
import football.manager.service.TeamService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final PlayerService playerService;

    @Override
    public Team getById(Long id) {
        return teamRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Can't find team by id: " + id));
    }

    @Override
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @Override
    public Team createTeam(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public Team addPlayerToTeam(Long teamId, Long playerId) {
        if (teamRepository.findTeamByPlayerId(playerId).isPresent()) {
            throw new PlayerAlreadyInTeamException("The player with id: " + playerId + " is already on the team");
        }

        Team team = getById(teamId);

        List<Player> allPlayersByTeamId = getAllPlayersByTeamId(teamId);
        allPlayersByTeamId.add(playerService.getById(playerId));

        team.setPlayers(allPlayersByTeamId);
        return teamRepository.save(team);
    }

    @Override
    public Team update(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public void delete(Long id) {
        teamRepository.deleteById(id);
    }

    @Override
    public List<Player> getAllPlayersByTeamId(Long teamId) {
        return teamRepository.findByIdWithPlayers(teamId)
                .orElseThrow(() -> new EntityNotFoundException("Team with id: " + teamId + " now fount"))
                .getPlayers();
    }
}
