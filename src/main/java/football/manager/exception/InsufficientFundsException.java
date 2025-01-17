package football.manager.exception;

import java.math.BigDecimal;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(Long teamId, BigDecimal requiredAmount, BigDecimal availableAmount) {
        super("Team with ID " + teamId + " has insufficient funds. Required: " 
                + requiredAmount + ", Available: " + availableAmount);
    }
}
