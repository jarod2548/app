package org.example.transactie.api;


import org.app.config.UserPrincipal;
import org.app.transaction.api.TransactieController;
import org.app.transaction.service.TransactieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactieController.class)
public class TransactieAPITests {
    @Autowired
    private MockMvc mockMvc;



    @MockitoBean
    private TransactieService service;

    @Test
    void shouldReturn201() throws Exception {

        UserPrincipal principal = new UserPrincipal(
                UUID.fromString("c3f1c2b6-7a2e-4c9d-9c3b-8d6f2a1e5b4c"),
                "naam",
                "USER"
        );

        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(
                        principal,
                        null,
                        principal.getAuthorities()
                );

        String json = """
        {
                      "aantal": 100.00,
                      "beschrijving": "test",
                      "datum": "2026-04-12T10:00:00"
                    }
    """;

        mockMvc.perform(post("/user/transactie")
                        .with(authentication(auth))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());
    }
}
