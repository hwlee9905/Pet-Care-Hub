package pet.hub.app.domain.pet.album;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pet.hub.app.domain.BaseEntity;
import pet.hub.app.domain.pet.Pet;
import pet.hub.app.domain.pet.album.img.PetAlbumImg;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pet_album")
public class PetAlbum extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @OneToMany(mappedBy = "petAlbum")
    private List<PetAlbumImg> petAlbumImgs = new ArrayList<>();

    protected void setTitle(final String title) {
        if (title != null) {
            this.title = title;
        }
    }

    protected void setContent(final String content) {
        if (content != null) {
            this.content = content;
        }
    }

    protected void setPet(final Pet pet) {
        if (this.pet != null) {
            this.pet.getPetAlbums().remove(this);
        }

        this.pet = pet;

        if (!pet.getPetAlbums().contains(this)) {
            pet.getPetAlbums().add(this);
        }
    }
}
