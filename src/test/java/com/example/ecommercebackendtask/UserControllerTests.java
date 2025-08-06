package com.example.ecommercebackendtask;

import com.example.ecommercebackendtask.controller.UserController;
import com.example.ecommercebackendtask.model.Role;
import com.example.ecommercebackendtask.model.User;
import com.example.ecommercebackendtask.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserControllerTests {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Test
    public void testDeleteUser_IsAdmin_shouldReturn204(){
        Long adminId = 1L;
        Long targetId = 2L;

        User admin = new User();
        admin.setId(adminId);
        admin.setRole(Role.ADMIN);
        when(userService.getUserById(adminId)).thenReturn(Optional.of(admin));

        ResponseEntity<?> response = userController.deleteUser(targetId, adminId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(userService).delete(targetId);
    }

    @Test
    public void testDeleteUser_NotAdmin_shouldReturn403(){
        Long userId = 1L;
        Long targetId = 2L;

        User user = new User();
        user.setId(userId);
        user.setRole(Role.USER);
        when(userService.getUserById(userId)).thenReturn(Optional.of(user));

        ResponseEntity<?> response = userController.deleteUser(targetId, userId);
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        verify(userService, never()).delete(targetId);
    }
}
