package pet.hub.app.domain.pet;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PetService {
    private final PetRepository petRepository;

    @Transactional
    public Pet create() {
        Pet pet = Pet.builder()
                .name("Love")
                .build();

        return petRepository.save(pet);
    }

    @Transactional(readOnly = true)
    public Pet read() {
        return petRepository.findById(1L).orElse(null);
    }
}
