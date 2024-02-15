package pet.hub.app.domain.pet;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


class PetServiceTest {

    @Autowired
    private PetService petService;

    @BeforeAll
    static void setUp() {
        PetBirth petBirth = PetBirth.builder()
                .year("2021")
                .month("07")
                .day("23")
                .build();

        Pet pet = Pet.builder()
                .id(1L)
                .name("Test")
                .petType(PetType.DOG_POMERANIAN)
                .petBirth(petBirth)
                .build();
    }

    @DisplayName("1. Pet 조회")
    @Test
    void readPet() {

    }

    @DisplayName("2. Pet 업데이트")
    @Test
    void updatePet() {

    }
}