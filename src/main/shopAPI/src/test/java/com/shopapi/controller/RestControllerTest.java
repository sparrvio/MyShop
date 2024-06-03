package com.shopapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.shopapi.dto.AddressDTO;
import com.shopapi.dto.ClientDTO;
import com.shopapi.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RestControllerTest {
    @Mock
    private ClientService clientService;
    @InjectMocks
    private RestController restController;
    private MockMvc mockMvc;

    private ClientDTO clientDTO;
    private ClientDTO clientDTO1;
    private ClientDTO clientDTO2;
    private AddressDTO addressDTO;
    private List<ClientDTO> clientDTOList;

    @BeforeEach()
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(restController).build();
        addressDTO  = AddressDTO.builder()
                .country("Russia")
                .city("Moscow")
                .street("Tverskaya")
                .build();

        clientDTO = ClientDTO.builder()
                .clientName("Ivan")
                .clientSurname("Petrov")
                .birthday(LocalDate.of(2000, 1, 1))
                .gender('M')
                .registrationDate(LocalDate.of(2002, 2, 2))
                .build();

        clientDTO1 = ClientDTO.builder()
                .clientName("Elena")
                .clientSurname("Smirnova")
                .birthday(LocalDate.of(1972, 1, 23))
                .gender('F')
                .registrationDate(LocalDate.of(2002, 2, 2))
                .build();

        clientDTO2 = ClientDTO.builder()
                .clientName("Marina")
                .clientSurname("Kozlova")
                .birthday(LocalDate.of(1990, 10, 21))
                .gender('F')
                .registrationDate(LocalDate.of(2020, 12, 31))
                .build();

        clientDTO.setAddress(addressDTO);

        clientDTOList = Arrays.asList(clientDTO, clientDTO1, clientDTO2);
    }

    @Test
    void getClientIsTrueTest() throws Exception {

        Optional<ClientDTO> clientDTOOptional = Optional.of(clientDTO);
        when(clientService.getClientById(1L)).thenReturn(clientDTOOptional);

        mockMvc.perform(get("/api/v1/client/id").param("clientID", "1"))
                .andExpect(status().isOk());

        verify(clientService).getClientById(1L);
        verify(clientService, times(1)).getClientById(1L);
    }

    @Test
    void getClientIsFalseTest() throws Exception {
        Optional<ClientDTO> clientDTOOptional = Optional.empty();
        when(clientService.getClientById(1L)).thenReturn(clientDTOOptional);

        mockMvc.perform(get("/api/v1/client/id").param("clientID", "1"))
                .andExpect(status().isNotFound());

        verify(clientService).getClientById(1L);
        verify(clientService, times(1)).getClientById(1L);
    }

    @Test
    void getAllClientsIsTrueTest() throws Exception  {
        when(clientService.getAllClients()).thenReturn(clientDTOList);
        mockMvc.perform(get("/api/v1/client/allClients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].clientName").value("Ivan"))
                .andExpect(jsonPath("$[1].clientName").value("Elena"))
                .andExpect(jsonPath("$[2].gender").value("F"));
    }

    @Test
    void getClientByFullNameIsTrueTest() throws Exception {
        when(clientService.getClientByNameAndSurname("Ivan Petrov")).thenReturn(clientDTOList);
        mockMvc.perform(get("/api/v1/client/search?fullName=Ivan Petrov"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].clientName").value("Ivan"))
                .andExpect(jsonPath("$[0].clientSurname").value("Petrov"))
                .andExpect(jsonPath("$[0].gender").value("M"))
                .andExpect(jsonPath("$[0].address.country").value("Russia"))
                .andExpect(jsonPath("$[0].address.city").value("Moscow"))
                .andExpect(jsonPath("$[0].address.street").value("Tverskaya"));
    }

    @Test
    void getClientByFullNameIsFalseTest() throws Exception  {
        when(clientService.getClientByNameAndSurname("Name Surname")).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/v1/client/search?fullName=Name Surname"))
                .andExpect(status().isOk());
    }

    @Test
    void createClientIsTrueTest() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String clientDTOJson = objectMapper.writeValueAsString(clientDTO1);
        mockMvc.perform(post("/api/v1/client/createClientForPostman")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(clientDTOJson))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Data received successfully")));
    }

    @Test
    void createClientIsFalseGenderTest() throws Exception  {
        clientDTO.setGender('X');
        ObjectMapper objectMapper  = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String clientDTOJson  = objectMapper.writeValueAsString(clientDTO);

        mockMvc.perform(post("/api/v1/client/createClientForPostman")
                .contentType(MediaType.APPLICATION_JSON)
                .content(clientDTOJson))
                .andExpect(status().isBadRequest());
    }
}
