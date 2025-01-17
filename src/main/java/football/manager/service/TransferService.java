package football.manager.service;

import football.manager.entity.Player;
import football.manager.entity.Team;

import java.math.BigDecimal;

public interface TransferService {
    void movePlayerToOtherTeam(Long fromTeamId, Long toTeamId, Long playerId);
}
