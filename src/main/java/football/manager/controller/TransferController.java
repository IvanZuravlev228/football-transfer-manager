package football.manager.controller;

import football.manager.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfers")
@RequiredArgsConstructor
public class TransferController {
    private final TransferService transferService;

    @Transactional
    @GetMapping
    public ResponseEntity<Void> createTransfer(@RequestParam Long fromTeamId,
                                               @RequestParam Long toTeamId,
                                               @RequestParam Long playerId) {

        transferService.movePlayerToOtherTeam(fromTeamId, toTeamId, playerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
