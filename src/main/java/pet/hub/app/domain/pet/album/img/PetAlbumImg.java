package pet.hub.app.domain.pet.album.img;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pet.hub.app.domain.pet.album.PetAlbum;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "pet_album_img")
public class PetAlbumImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uploadFileUrl;

    private String uploadFilePath;

    private String uploadFileName;

    @ManyToOne
    @JoinColumn(name = "pet_album_id")
    private PetAlbum petAlbum;
}
