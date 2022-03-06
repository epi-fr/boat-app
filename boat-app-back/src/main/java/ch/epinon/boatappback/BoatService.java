package ch.epinon.boatappback;

import ch.epinon.boatappback.exception.ResourceNotFoundException;
import ch.epinon.boatappback.exception.ResourceToDeleteNotFoundException;
import ch.epinon.boatappback.exception.ResourceToUpdateNotFoundException;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.text.MessageFormat.format;

@Service
@RequiredArgsConstructor
public class BoatService {

    private final BoatRepository boatRepository;
    private final BoatMapper boatMapper = Mappers.getMapper(BoatMapper.class);

    public List<BoatDto> getAllBoat() {
        return StreamSupport.stream(boatRepository.findAll().spliterator(), false)
                .map(boatMapper::mapBoatDto)
                .collect(Collectors.toList());
    }

    public BoatDto updateBoat(CreateBoatDto createBoatDto) {
        Boat savedBoat = boatRepository.save(boatMapper.mapBoat(createBoatDto));
        return boatMapper.mapBoatDto(savedBoat);
    }

    public void updateBoat(Long id, UpdateBoatDto updateBoatDto) {
        if (!boatRepository.existsById(id)) {
            throw new ResourceToUpdateNotFoundException(format("Invalid boat id to update, id {0} does not exist", id));
        }

        boatRepository.save(boatMapper.mapBoat(id, updateBoatDto));
    }

    public void deleteBoat(Long id) {
        if (!boatRepository.existsById(id)) {
            throw new ResourceToDeleteNotFoundException(format("Invalid boat id to delete, id {0} does not exist", id));
        }

        boatRepository.deleteById(id);
    }


    public BoatDto getBoatById(Long id) {
        return boatRepository.findById(id).map(boatMapper::mapBoatDto)
                .orElseThrow(() -> new ResourceNotFoundException(format("There is no boat with id {0}", id)));
    }
}
