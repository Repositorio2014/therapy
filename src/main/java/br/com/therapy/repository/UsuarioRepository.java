package br.com.therapy.repository;

import br.com.therapy.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
    default boolean userExists(Long id){
        Optional<Usuario> user;
        if(id != null){
            return userExists(id);
        }
        return false;
    }
}