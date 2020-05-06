package ru.folkland.web.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.folkland.manager.player.FootballPosition;
import ru.folkland.manager.transfer.ITransferList;
import ru.folkland.manager.transfer.PlayerCost;

import java.util.List;

/**
 * @author folkland
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/transfer")
@CrossOrigin(origins = "http://localhost:8080")
public class TransferControllers {

    private static final String NO_POSITION = "";
    private final ITransferList transferList;

    @GetMapping("/list")
    public ResponseEntity<List<PlayerCost>> getTransferList(@RequestParam(name = "position", defaultValue = NO_POSITION) String position) {
        if (NO_POSITION.equals(position)) return ResponseEntity.ok(transferList.sortAvailablePlayers());
        return ResponseEntity.ok(transferList.getPlayerForPosition(FootballPosition.valueOf(position)));
    }

}
