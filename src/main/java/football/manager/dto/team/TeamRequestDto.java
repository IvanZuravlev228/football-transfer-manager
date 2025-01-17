package football.manager.dto.team;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class TeamRequestDto {
    @NotBlank(message = "Team name cannot be blank")
    private String teamName;

    @NotBlank(message = "Nickname cannot be blank")
    private String nickname;

    @NotBlank(message = "Country cannot be blank")
    private String country;

    @NotNull(message = "Money cannot be null")
    @DecimalMin(value = "0.0", inclusive = true, message = "Money must be non-negative")
    private BigDecimal money;

    @NotNull(message = "Commission cannot be null")
    @DecimalMin(value = "0.0", inclusive = true, message = "Commission must be non-negative")
    private Float commission;

    @NotBlank(message = "Description cannot be blank")
    private String description;
}