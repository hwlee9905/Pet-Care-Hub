package pet.hub.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pet.hub.app.domain.pet.PetService;

@SpringBootTest
class PetServiceTest {

    @Autowired
    private PetService petService;

    @Test
    @DisplayName("1.[Create]")
    void create() {
        petService.create();
    }

    @Test
    @DisplayName("2.[Read]")
    void read() {
        System.out.println(petService.read().getName());
    }
}