package pet.hub.app.domain.pet.album;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PetAlbumServiceTest {

    @Mock
    private PetAlbumRepository petAlbumRepository;

    @InjectMocks
    private PetAlbumService petAlbumService;


}