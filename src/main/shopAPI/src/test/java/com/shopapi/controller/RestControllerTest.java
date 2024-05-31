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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class RestControllerTest {
    @Mock
    private ClientService clientService;
    @InjectMocks
    private RestController restController;
    private MockMvc mockMvc;

    @BeforeEach()
        void setUp(){
            mockMvc = MockMvcBuilders.standaloneSetup(restController).build();
        }

    @Test
    void getClientTest() throws Exception {
        ClientDTO clientDTO1 = ClientDTO.builder()
                .clientName("Ivan")
                .clientSurname("Petrov")
                .birthday(LocalDate.of(2000,  1,  01))
                .gender('M')
                .registrationDate(LocalDate.of(2002,  2,  02))
                .build();

        ClientDTO clientDTO2 = ClientDTO.builder()
                .clientName("Marina")
                .clientSurname("Ivanova")
                .birthday(LocalDate.of(2000,  1,  01))
                .gender('F')
                .registrationDate(LocalDate.of(2002,  2,  02))
                .build();

        Optional<ClientDTO> clientDTOOptional = Optional.of(clientDTO1);
        when(clientService.getClientById(1L)).thenReturn(clientDTOOptional);

        mockMvc.perform(get("/api/v1/client/id)"))
                .andExpect(status().isOk());

        verify(clientService).getClientById(1L);
    }
}
