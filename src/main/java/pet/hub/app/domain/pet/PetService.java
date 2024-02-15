package pet.hub.app.domain.pet;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pet.hub.app.web.dto.pet.PetRequest;
import pet.hub.common.exception.EntityNotFoundException;

@RequiredArgsConstructor
@Service
public class PetService {
    private final PetRepository petRepository;

    @Transactional(rollbackFor = Exception.class)
    public Pet createPet(PetRequest request) {
        Pet pet = Pet.builder()
                .name(request.getName())
                .petType(request.getPetType())
                .petBirth(request.getPetBirth())
                .build();

        return petRepository.save(pet);
    }

    public void updatePet(PetRequest petRequest) {

    }

    public void deletePet(Long id) {
        petRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Pet readPet(Long id) {
        return petRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("등록되지 않은 반려동물입니다."));
    }
}
