package football.manager.controller;

import football.manager.dto.player.PlayerRequestDto;
import football.manager.dto.player.PlayerResponseDto;
import football.manager.entity.Player;
import football.manager.service.PlayerService;
import football.manager.service.mapper.PlayerMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
@RequiredArgsConstructor
public class PlayerController {
    private final PlayerService playerService;
    private final PlayerMapper playerMapper;

    @GetMapping("/{playerId}")
    public ResponseEntity<PlayerResponseDto> getById(@PathVariable Long playerId) {
        return new ResponseEntity<>(playerMapper.modelToDto(playerService.getById(playerId)), HttpStatus.OK);
    }

    @GetMapping("/without-team")
    public ResponseEntity<List<PlayerResponseDto>> getAllWithoutTeam() {
        return new ResponseEntity<>(playerService.getAllWithoutTeam()
                .stream()
                .map(playerMapper::modelToDto)
                .toList(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PlayerResponseDto> create(@RequestBody @Valid PlayerRequestDto playerRequestDto) {
        return new ResponseEntity<>(playerMapper.modelToDto(
                playerService.save(playerMapper.dtoToModel(playerRequestDto))), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<PlayerResponseDto> update(@RequestBody @Valid PlayerRequestDto playerRequestDto) {
        return new ResponseEntity<>(playerMapper.modelToDto(
                playerService.update(playerMapper.dtoToModel(playerRequestDto))), HttpStatus.OK);
    }

    @DeleteMapping("/{playerId}")
    public ResponseEntity<Void> delete(@PathVariable Long playerId) {
        playerService.removeById(playerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
