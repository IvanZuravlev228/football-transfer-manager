package football.manager.service.impl;

import football.manager.entity.Player;
import football.manager.entity.Team;
import football.manager.exception.InsufficientFundsException;
import football.manager.exception.PlayerNotInTeamException;
import football.manager.service.PlayerService;
import football.manager.service.TeamService;
import football.manager.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {
    private final static int TRANSFER_COEFFICIENT = 100000;
    private final TeamService teamService;
    private final PlayerService playerService;

    @Override
    public void movePlayerToOtherTeam(Long fromTeamId, Long toTeamId, Long playerId) {
        Team fromTeam = teamService.getById(fromTeamId);
        Team toTeam = teamService.getById(toTeamId);
        Player player = playerService.getById(playerId);

        if (!fromTeam.getPlayers().contains(player)) {
            throw new PlayerNotInTeamException(playerId, fromTeamId);
        }

        BigDecimal playerCost = calculatePlayerCost(player, fromTeam);

        if (toTeam.getMoney().compareTo(playerCost) < 0) {
            throw new InsufficientFundsException(toTeam.getId(), playerCost, toTeam.getMoney());
        }

        toTeam.setMoney(toTeam.getMoney().subtract(playerCost));
        fromTeam.setMoney(fromTeam.getMoney().add(playerCost));

        fromTeam.getPlayers().remove(player);
        toTeam.getPlayers().add(player);

        teamService.update(fromTeam);
        teamService.update(toTeam);
    }

    private BigDecimal calculatePlayerCost(Player player, Team fromTeam) {
        BigDecimal baseTransferCost = BigDecimal.valueOf(player.getExperience())
                .multiply(BigDecimal.valueOf(TRANSFER_COEFFICIENT))
                .divide(BigDecimal.valueOf(player.getAge()), 2, RoundingMode.HALF_UP);

        BigDecimal commissionFee = baseTransferCost.multiply(BigDecimal.valueOf(fromTeam.getCommission()))
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

        return baseTransferCost.add(commissionFee);
    }
}
