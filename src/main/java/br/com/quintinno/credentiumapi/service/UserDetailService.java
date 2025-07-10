package br.com.quintinno.credentiumapi.service;

import java.util.List;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.quintinno.credentiumapi.entity.UsuarioEntity;
import br.com.quintinno.credentiumapi.repository.UsuarioRepository;

public class UserDetailService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UserDetailService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioEntity usuarioEntity = this.usuarioRepository
            .findByIdentificador(username).stream().findFirst()
            .orElseThrow( () -> new UsernameNotFoundException("Usuário não encontrado!"));
        return new User(usuarioEntity.getIdentificador(), usuarioEntity.getSenha(), List.of());
    }

}
