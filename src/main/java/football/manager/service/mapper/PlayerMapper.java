package football.manager.service.mapper;

import football.manager.dto.player.PlayerRequestDto;
import football.manager.dto.player.PlayerResponseDto;
import football.manager.entity.Player;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper {
    public PlayerResponseDto modelToDto(Player player) {
        PlayerResponseDto dto = new PlayerResponseDto();
        dto.setId(player.getId());
        dto.setFirstName(player.getFirstName());
        dto.setLastName(player.getLastName());
        dto.setAge(player.getAge());
        dto.setExperience(player.getExperience());
        return dto;
    }

    public Player dtoToModel(PlayerRequestDto dto) {
        Player model = new Player();
        model.setFirstName(dto.getFirstName());
        model.setLastName(dto.getLastName());
        model.setAge(dto.getAge());
        model.setExperience(dto.getExperience());
        return model;
    }
}
