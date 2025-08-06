package com.example.ecommercebackendtask;

import com.example.ecommercebackendtask.controller.AuthController;
import com.example.ecommercebackendtask.requests.LoginRequest;
import com.example.ecommercebackendtask.requests.RegisterRequest;
import com.example.ecommercebackendtask.responses.AuthResponse;
import com.example.ecommercebackendtask.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@ExtendWith(MockitoExtension.class)
public class AuthControllerTests {

    @InjectMocks
    private AuthController authController;

    @Mock
    private UserService userService;

    @Test
    public void testRegister_shouldReturn201AndToken(){
        RegisterRequest request = new RegisterRequest("npantos", "123");
        AuthResponse response = new AuthResponse("token");

        when(userService.register(request)).thenReturn(response);
        ResponseEntity<AuthResponse> response2 = authController.register(request);

        assertEquals(HttpStatus.CREATED, response2.getStatusCode());
        assertEquals("token", response2.getBody().getToken());
        verify(userService).register(request);
    }

    @Test
    public void testLogin_shouldReturn200AndToken(){
        LoginRequest request = new LoginRequest("npantos", "123");
        AuthResponse response = new AuthResponse("token");

        when(userService.login(request)).thenReturn(response);
        ResponseEntity<AuthResponse> response2 = authController.login(request);

        assertEquals(HttpStatus.OK, response2.getStatusCode());
        assertEquals("token", response2.getBody().getToken());
        verify(userService).login(request);
    }
}
