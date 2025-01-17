package football.manager.controller;

import football.manager.dto.player.PlayerResponseDto;
import football.manager.dto.team.TeamRequestDto;
import football.manager.dto.team.TeamResponseDto;
import football.manager.entity.Player;
import football.manager.entity.Team;
import football.manager.service.TeamService;
import football.manager.service.mapper.PlayerMapper;
import football.manager.service.mapper.TeamMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;
    private final TeamMapper teamMapper;
    private final PlayerMapper playerMapper;

    @GetMapping("/{teamId}")
    public ResponseEntity<TeamResponseDto> getById(@PathVariable Long teamId) {
        return new ResponseEntity<>(teamMapper.modelToDto(teamService.getById(teamId)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TeamResponseDto>> getAll() {
        return new ResponseEntity<>(teamService.getAllTeams()
                .stream()
                .map(teamMapper::modelToDto)
                .toList(), HttpStatus.OK);
    }

    @GetMapping("/{teamId}/players")
    public ResponseEntity<List<PlayerResponseDto>> getPlayersByTeamId(@PathVariable Long teamId) {
        return new ResponseEntity<>(teamService.getAllPlayersByTeamId(teamId)
                .stream()
                .map(playerMapper::modelToDto)
                .toList(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TeamResponseDto> create(@RequestBody @Valid TeamRequestDto teamRequestDto) {
        return new ResponseEntity<>(teamMapper.modelToDto(
                teamService.createTeam(teamMapper.dtoToModel(teamRequestDto))), HttpStatus.CREATED);
    }

    @PostMapping("/{teamId}/add/player/{playerId}")
    public ResponseEntity<TeamResponseDto> addPlayer(@PathVariable Long teamId,
                                                     @PathVariable Long playerId) {
        return new ResponseEntity<>(teamMapper.modelToDto(teamService.addPlayerToTeam(teamId, playerId)), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<TeamResponseDto> update(@RequestBody @Valid TeamRequestDto teamRequestDto) {
        return new ResponseEntity<>(teamMapper.modelToDto(
                teamService.update(teamMapper.dtoToModel(teamRequestDto))), HttpStatus.OK);
    }

    @DeleteMapping("/{teamId}")
    public ResponseEntity<Void> delete(@PathVariable Long teamId) {
        teamService.delete(teamId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
