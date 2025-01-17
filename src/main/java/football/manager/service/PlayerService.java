package football.manager.service;

import football.manager.entity.Player;

import java.util.List;

public interface PlayerService {
    Player getById(Long id);

    List<Player> getAllWithoutTeam();

    Player save(Player player);

    Player update(Player updatePlayer);

    void removeById(Long id);
}
