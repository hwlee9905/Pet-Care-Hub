package pet.hub.app.domain.pet;

import jakarta.persistence.*;
import lombok.*;
import pet.hub.app.domain.BaseEntity;
import pet.hub.app.domain.pet.enums.PetType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "pet")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private PetType petType;

    @Embedded
    private PetBirth petBirth;
}
