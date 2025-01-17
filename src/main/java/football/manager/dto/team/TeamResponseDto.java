package football.manager.dto.team;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TeamResponseDto {
    private Long id;
    private String teamName;
    private BigDecimal money;
    private String nickname;
    private Float commission;
}