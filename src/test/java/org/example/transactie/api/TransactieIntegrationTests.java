package org.example.transactie.api;


import org.app.Account.infrastructure.UserDBO;
import org.app.Account.infrastructure.UserRepository;
import org.app.Main;
import org.app.config.UserPrincipal;

import org.app.transaction.repository.TransactieDBO;
import org.app.transaction.repository.TransactieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TransactieIntegrationTests {

    private UUID userID;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactieRepository transactieRepository;

    @BeforeEach
    void Setup()
    {
        transactieRepository.deleteAll();
        userRepository.deleteAll();
        UserDBO dbo = new UserDBO("naam", "wachtwoord", "USER", "Email");
        UserDBO saved = userRepository.save(dbo);
        userID = saved.getId();
    }

    @Test
    void shouldCreateTransactie() throws Exception {


        UserPrincipal principal = new UserPrincipal(
                userID,
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

        List<TransactieDBO> transactieDBOS = transactieRepository.findAll();

        assertThat(transactieDBOS).hasSize(1);

        TransactieDBO t = transactieDBOS.stream().findFirst().orElseThrow();

        assertThat(t.getAantal()).isEqualTo(new BigDecimal("100.00"));
        assertThat(t.getBeschrijving()).isEqualTo("test");
        assertThat(t.getCreatieDatum()).isEqualTo(LocalDateTime.of(2026, 4, 12, 10, 0));
        assertThat(t.getUser().getId()).isEqualTo(userID);
    }
}


