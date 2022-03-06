package ch.epinon.boatappback;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("boat")
@AllArgsConstructor
@Slf4j
@CrossOrigin
public class BoatController {

    private final BoatService boatService;

    @GetMapping({"/{id}"})
    public ResponseEntity<BoatDto> getBoat(@PathVariable Long id) {
        log.info("get boat id {}", id);
        return ResponseEntity.ok(boatService.getBoatById(id));

    }

    @GetMapping({"/", ""})
    public List<BoatDto> getAllBoat() {
        log.info("get all boat id");
        return boatService.getAllBoat();
    }

    @PostMapping({"/", ""})
    public ResponseEntity<BoatDto> addBoat(@RequestBody @Valid CreateBoatDto createBoatDto,
                                           UriComponentsBuilder uriComponentsBuilder,
                                           HttpServletRequest httpServletRequest) {
        log.info("Post new boat with name {}", createBoatDto.getName());
        BoatDto boat = boatService.updateBoat(createBoatDto);
        URI location = uriComponentsBuilder
                .scheme(httpServletRequest.getScheme())
                .path(httpServletRequest.getServletPath() + "/" + boat.getId())
                .build()
                .toUri();

        return ResponseEntity.status(HttpStatus.CREATED).location(location).body(boat);
    }


    @PutMapping({"/{id}"})
    public ResponseEntity<Void> updateBoat(@PathVariable Long id,
                                           @RequestBody @Valid UpdateBoatDto createBoatDto) {
        log.info("update boat id {}", id);
        boatService.updateBoat(id, createBoatDto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> deleteBoat(@PathVariable Long id) {
        log.info("delete boat id {}", id);
        boatService.deleteBoat(id);

        return ResponseEntity.ok().build();
    }


}
