package pet.hub.app.web.dto.pet;

import lombok.Data;
import pet.hub.app.domain.pet.PetBirth;
import pet.hub.app.domain.pet.PetType;

@Data
public class PetRequest {
    private String name;
    private PetType petType;
    private PetBirth petBirth;
}
