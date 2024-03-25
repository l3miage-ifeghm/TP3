package fr.uga.l3miage.tp3.exo1.components;

import fr.uga.l3miage.tp3.exo1.models.UserEntity;
import lombok.*;
import org.springframework.stereotype.Component;
import fr.uga.l3miage.tp3.exo1.repositories.*;

import javax.persistence.*;
import java.util.Set;

@RequiredArgsConstructor
@Component
@Entity
@Builder
@NoArgsConstructor(force = true) //va générer un constructeur sans paramètres
@AllArgsConstructor //génère un constructeur avec tous les attributs de la classe en paramètres
@Setter
@Getter
public class UserComponent {

    private final UserRepository userRepository;

    //Comportement Read (Ici, on a permis la lecture d'une entité user en base grâce à son id.)
    public UserEntity getUser(String name){
        assert userRepository != null;
        return userRepository.findById(name).orElseThrow();//fonction de base d'un repository findById()
    }


    // Comportement Create
    public UserEntity createUser(){
        UserEntity userEntity = UserEntity //Créez une entité UserEntity en utilisant le builder
                .builder()
                .mail("user@user.com")
                .name("user")
                .playlistEntities(Set.of())
                .build();
        assert userRepository != null;
        return userRepository.save(userEntity);
    }

    // Coportement Update
    public UserEntity updateEmail(String name, String newMail){
        assert userRepository != null;
        UserEntity userEntity = userRepository.findById(name).orElseThrow();
        userEntity.setMail(newMail);
        return userRepository.save(userEntity);
    }

    //Comporteement Delete
    public void deleteUser(String name){
        assert userRepository != null;
        userRepository.deleteById(name);
    }

    // Cette fonction pour trouver tous les utilisateurs de Google et cela en regardant leurs mail s'ils contiennent "@gmail.com"
    public Set<UserEntity> getUserIsInDomain(String domaine){
        assert userRepository != null;
        return userRepository.findAllByMailContaining(domaine);
    }

}

