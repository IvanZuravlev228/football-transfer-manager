package football.manager.service.mapper;

import football.manager.dto.team.TeamRequestDto;
import football.manager.dto.team.TeamResponseDto;
import football.manager.entity.Team;
import org.springframework.stereotype.Component;

@Component
public class TeamMapper {

    public TeamResponseDto modelToDto(Team team) {
        TeamResponseDto dto = new TeamResponseDto();
        dto.setId(team.getId());
        dto.setTeamName(team.getName());
        dto.setMoney(team.getMoney());
        dto.setNickname(team.getNickname());
        dto.setCommission(team.getCommission());
        return dto;
    }

    public Team dtoToModel(TeamRequestDto dto) {
        Team model = new Team();
        model.setName(dto.getTeamName());
        model.setNickname(dto.getNickname());
        model.setCountry(dto.getCountry());
        model.setMoney(dto.getMoney());
        model.setCommission(dto.getCommission());
        model.setDescription(dto.getDescription());
        return model;
    }
}
