package football.manager.service.impl;

import football.manager.entity.Player;
import football.manager.repository.PlayerRepository;
import football.manager.service.PlayerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;

    @Override
    public Player getById(Long id) {
        return playerRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Player with id " + id + " not found"));
    }

    @Override
    public List<Player> getAllWithoutTeam() {
        return playerRepository.findPlayersWithoutTeams();
    }

    @Override
    public Player save(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public Player update(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public void removeById(Long id) {
        playerRepository.deleteById(id);
    }
}
