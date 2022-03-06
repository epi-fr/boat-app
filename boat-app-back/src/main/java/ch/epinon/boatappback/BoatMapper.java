package ch.epinon.boatappback;

import org.mapstruct.Mapper;

@Mapper
public interface BoatMapper {


    Boat mapBoat(CreateBoatDto createBoatDto);

    Boat mapBoat(Long id, UpdateBoatDto updateBoatDto);

    BoatDto mapBoatDto(Boat boat);
}
