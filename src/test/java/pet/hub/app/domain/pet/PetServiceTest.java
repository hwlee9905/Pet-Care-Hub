package pet.hub.app.domain.pet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pet.hub.app.web.dto.pet.PetRequest;
import pet.hub.common.exception.EntityNotFoundException;

import java.util.Optional;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PetServiceTest {

    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private PetService petService;

    @BeforeEach
    public void setUp() { // given
        Pet pet = Pet.builder()
                .id(1L)
                .name("Test")
                .petType(PetType.DOG_POMERANIAN)
                .petBirth(petBirthFactory("2021", "07", "23"))
                .build();

        Mockito.lenient().when(petRepository.findById(1L)).thenReturn(Optional.of(pet));
    }

    @DisplayName("When_Create_Pet")
    @Test
    public void When_Create_Pet() {
        // given
        PetRequest request = requestFactory(
                "Test",
                PetType.DOG_POMERANIAN,
                petBirthFactory("2021", "07", "23")
        );

        when(petRepository.save(any(Pet.class))).then(returnsFirstArg());

        // when
        Pet result = petService.createPet(request);

        // then
        Assertions.assertEquals(petRepository.findById(1L).get().getName(), result.getName());
        Assertions.assertEquals(petRepository.findById(1L).get().getPetType(), result.getPetType());
    }

    @DisplayName("When_Read_Pet")
    @Test
    public void When_Read_Pet() {
        // when
        Pet pet = petService.readPet(1L);

        // then
        Assertions.assertEquals(petRepository.findById(1L).get().getName(), pet.getName());
        Assertions.assertEquals(petRepository.findById(1L).get().getPetType(), pet.getPetType());
    }

    @DisplayName("When_Read_Pet_Throw_EntityNotFoundException")
    @Test
    public void When_Read_Pet_Throw_EntityNotFoundException() {
        // given
        when(petRepository.findById(2L)).thenReturn(Optional.empty());

        // when, then
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            petService.readPet(2L);
        });
    }

    public PetBirth petBirthFactory(String year, String month, String day) {
        return PetBirth.builder()
                .year(year)
                .month(month)
                .day(day)
                .build();
    }

    public PetRequest requestFactory(String name, PetType petType, PetBirth petBirth) {
        return PetRequest.builder()
                .name(name)
                .petType(petType)
                .petBirth(petBirth)
                .build();
    }
}