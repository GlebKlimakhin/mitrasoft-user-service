package com.example.userservice;

import com.example.userservice.api.UserApi;
import com.example.userservice.dto.UpdateUserInfoRequestDto;
import com.example.userservice.dto.UpdateUserRolesRequestDto;
import com.example.userservice.dto.UserDto;
import com.example.userservice.model.User;
import com.example.userservice.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class UserControllerTest {

    private MockMvc mockMvc;

    private final MediaType applicationJson = MediaType.APPLICATION_JSON;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserApi userApi;

    UserDto user1 = new UserDto(1L, "test1@aa.ru", "100");
    UserDto user2 = new UserDto(2L, "test2@aa.ru", "200");
    UserDto user3 = new UserDto(3L, "test3@aa.ru", "300");
    User user = new User(1L, "test@bb.ru", "1000");

    @BeforeAll
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userApi).build();
    }

    @Test
    @SneakyThrows
    public void findAll() {
        List<UserDto> users = new ArrayList<>(Arrays.asList(user1, user2, user3));
        Mockito.when(userService.findAll()).thenReturn(users);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/users/")
                .contentType(applicationJson))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].username", is(user3.getUsername())));

    }

    @Test
    @SneakyThrows
    public void findById() {
        Mockito.when(userService.findUserById(1L)).thenReturn(user1);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/users/1")
                .contentType(applicationJson))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", aMapWithSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", is(user1.getUsername())));
    }


    @Test
    @SneakyThrows
    public void save() {
        Mockito.when(userService.createUser(Mockito.any(User.class))).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/users/")
                .content(asJsonString(user1))
                .contentType(applicationJson))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$", aMapWithSize(4)))
                .andExpect(jsonPath("$.username", is(user.getUsername())));
    }

    @Test
    @SneakyThrows
    public void deleteById() {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/users/1")
                        .contentType(applicationJson))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    public void updateInfo() {
        UpdateUserInfoRequestDto updRequest = new UpdateUserInfoRequestDto(1L, "updated@gg.ru","updated");

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/users/info/")
                        .content(asJsonString(updRequest))
                        .contentType(applicationJson))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    public void updateRoles() {
        UpdateUserRolesRequestDto updRequest = new UpdateUserRolesRequestDto(1L, "update@gg.ru", "updated");

        mockMvc.perform(MockMvcRequestBuilders
                .put("/users/roles/")
                .content(asJsonString(updRequest))
                .contentType(applicationJson))
                .andExpect(status().isOk());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
