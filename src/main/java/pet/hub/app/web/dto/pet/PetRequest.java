package pet.hub.app.web.dto.pet;

import lombok.*;
import pet.hub.app.domain.pet.PetBirth;
import pet.hub.app.domain.pet.PetType;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetRequest {
    private String name;
    private PetType petType;
    private PetBirth petBirth;
}
