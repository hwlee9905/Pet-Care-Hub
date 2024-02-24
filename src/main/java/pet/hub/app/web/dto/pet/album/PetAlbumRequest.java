package pet.hub.app.web.dto.pet.album;

import pet.hub.app.domain.pet.Pet;

import java.time.LocalDateTime;

public record PetAlbumRequest(
        String title,
        String content,
        Pet pet
) {
}
