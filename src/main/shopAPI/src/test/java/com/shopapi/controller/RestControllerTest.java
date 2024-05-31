package com.shopapi.controller;

import com.shopapi.dto.ClientDTO;
import com.shopapi.model.Client;
import com.shopapi.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class RestControllerTest {
    @Mock
    private ClientService clientService;
    @InjectMocks
    private RestController restController;
    private MockMvc mockMvc;

    private ClientDTO clientDTO;
    private ClientDTO clientDTO1;
    private ClientDTO clientDTO2;
    private List<ClientDTO> clientDTOList;

    @BeforeEach()
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(restController).build();
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
//                .andExpect(jsonPath("$[2].birthday").value(LocalDate.of(1990, 10, 21)))
                .andExpect(jsonPath("$[1].gender").value("F"));
    }
}
