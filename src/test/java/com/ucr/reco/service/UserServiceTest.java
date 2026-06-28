package com.ucr.reco.service;

import com.ucr.reco.model.User;
import com.ucr.reco.model.dto.UserDTO;
import com.ucr.reco.repository.UserJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserJpaRepository pruebas;

    @InjectMocks
    private UserService userService;

    @Test
    void agregarUsuario() {

        UserDTO dto = new UserDTO();
        dto.setName("Ian");
        dto.setEmail("ian123@gmail.com");
        dto.setPassword("1234");
        dto.setRole("Usuario");

        User user = new User();

        when(pruebas.existsByEmail(dto.getEmail()))
                .thenReturn(false);

        when(pruebas.save(any(User.class)))
                .thenReturn(user);

        User resultado = userService.add(dto);

        assertNotNull(resultado);

        verify(pruebas).existsByEmail(dto.getEmail());
        verify(pruebas).save(any(User.class));
    }

    @Test
    void correoYaRegistrado() {
        UserDTO dto = new UserDTO();
        dto.setEmail("ian@gmail.com");

        when(pruebas.existsByEmail(dto.getEmail())).thenReturn(true);

        User resultado = userService.add(dto);

        assertNull(resultado);

        verify(pruebas).existsByEmail(dto.getEmail());
    }

//    @Test
//    void datosIncompletos() {
//        UserDTO dto = new UserDTO();
//        dto.setName(null);
//        dto.setEmail("ian@gmail.com");
//        dto.setPassword("1234");
//        dto.setRole("USER");
//
//        when(pruebas.existsByEmail(dto.getEmail())).thenReturn(false);
//
//        User resultado = userService.add(dto);
//
//        assertNull(resultado);
//
//        verify(pruebas).getByEmail(dto.getEmail());
//    }

    @Test
    void retornarLista() {
        List<User> usuarios = List.of(
                new User(),
                new User()
        );

        when(pruebas.findAll()).thenReturn(usuarios);

        List<User> resultado = userService.findAll();

        assertEquals(2, resultado.size());

        verify(pruebas).findAll();
    }

    @Test
    void exepcion() {
        when(pruebas.findAll()).thenThrow(new
                RuntimeException("Error BD"));

        assertThrows(RuntimeException.class, () -> {
            userService.findAll();
        });

        verify(pruebas).findAll();

    }

}
