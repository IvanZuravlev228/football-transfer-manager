package football.manager.config;

import football.manager.entity.Player;
import football.manager.entity.Team;
import football.manager.service.PlayerService;
import football.manager.service.TeamService;
import football.manager.service.TransferService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class DataInitializer {
    private final List<String> footballPositions = Arrays.asList(
            "GK",
            "CB",
            "LB",
            "RB",
            "CDM",
            "CM",
            "CAM",
            "LM",
            "RM",
            "LW",
            "RW",
            "CF",
            "ST",
            "SS"
    );

    private final List<String> countries = Arrays.asList(
            "Ukraine",
            "Germany",
            "Brazil"
    );

    private final Random random = new Random();
    private final List<Player> players = new ArrayList<>();
    private final PlayerService playerService;
    private final TeamService teamService;

    @PostConstruct
    public void init() {
        for (int i = 0; i < 75; i++) {
            Player player = new Player();
            player.setFirstName("Player_" + i);
            player.setLastName("Playerovich_" + i);
            player.setAge(18 + random.nextInt(13));
            player.setExperience(1 + random.nextInt(1200));
            player.setPosition(footballPositions.get(random.nextInt(footballPositions.size())));
            player.setEmail("player" + i + "@gmail.com");
            players.add(player);
            playerService.save(player);
        }

        Team team1 = createTeam("FC Barcelona", "Barça", "1000000000", 1f);
        team1.setPlayers(getPlayersList(0, 12));
        teamService.createTeam(team1);
        Team team2 = createTeam("Real Madrid", "Los Blancos", "1200000000", 1.5f);
        team2.setPlayers(getPlayersList(12, 24));
        teamService.createTeam(team2);
        Team team3 = createTeam("Manchester United", "Red Devils", "800000000", 4f);
        team3.setPlayers(getPlayersList(24, 36));
        teamService.createTeam(team3);
        Team team4 = createTeam("Liverpool FC", "The Reds", "950000000", 2f);
        team4.setPlayers(getPlayersList(36, 48));
        teamService.createTeam(team4);
        Team team5 = createTeam("Bayern Munich", "Die Roten", "1100000000", 3.2f);
        team5.setPlayers(getPlayersList(48, 60));
        teamService.createTeam(team5);
    }

    private Team createTeam(String name, String nickname, String money, Float commission) {
        Team team = new Team();
        team.setName(name);
        team.setNickname(nickname);
        team.setCountry(countries.get(random.nextInt(countries.size())));
        team.setMoney(new BigDecimal(money));
        team.setCommission(commission);
        team.setDescription("Some description about the team with name " + name);
        return team;
    }

    private List<Player> getPlayersList(int indexFrom, int indexTo) {
        List<Player> pl = new ArrayList<>();
        for (int i = indexFrom; i < indexTo; i++) {
            pl.add(players.get(i));
        }
        return pl;
    }

}
