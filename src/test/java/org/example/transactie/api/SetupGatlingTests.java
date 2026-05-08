package org.example.transactie.api;

import org.app.Account.infrastructure.UserDBO;
import org.app.Account.infrastructure.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = org.app.Main.class)
@ActiveProfiles("it") // Uses your real DB / Testcontainers
class SetupGatlingTests
{

    @Autowired
    private UserRepository userRepository;

    @Test
    void prepareGatlingCsv() throws IOException {
        int userCount = 100;
        List<String> lines = new ArrayList<>();
        lines.add("userId,username"); // CSV Header

        for (int i = 0; i < userCount; i++) {
            UserDBO user = new UserDBO();
            user.setUsername("user_" + i);
            // Save to real DB so Foreign Keys will work
            user = userRepository.save(user);

            lines.add(user.getId() + "," + user.getUsername());
        }

        // Save to the folder Gatling uses
        Path path = Paths.get("src/test/resources/data/users.csv");
        Files.createDirectories(path.getParent());
        Files.write(path, lines);
    }
}