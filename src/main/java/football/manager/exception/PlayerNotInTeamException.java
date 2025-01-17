package football.manager.exception;

public class PlayerNotInTeamException extends RuntimeException {
    public PlayerNotInTeamException(Long playerId, Long teamId) {
        super("Player " + playerId + " is not present in team " + teamId);
    }
}
