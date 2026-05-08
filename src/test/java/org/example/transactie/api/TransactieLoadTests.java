package org.example.transactie.api;

import io.gatling.javaapi.core.FeederBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;
import org.springframework.test.context.ActiveProfiles;


import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;

@ActiveProfiles("it")
public class TransactieLoadTests extends Simulation {




    FeederBuilder<String> userFeeder = csv("data/users.csv").circular();

    HttpProtocolBuilder httpProtocol =
            http.baseUrl("http://localhost:8080")
                    .acceptHeader("application/json")
                    .userAgentHeader(
                            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) " +
                                    "AppleWebKit/537.36 (KHTML, like Gecko) " +
                                    "Chrome/134.0.0.0 Safari/537.36");

    ScenarioBuilder scn =
            scenario("Transactie ophalen").pause(10)
                    .feed(userFeeder)
                    .exec(http("Login")
                            .post("/api/auth/login")
                            .body(StringBody("{ \"username\": \"#{username}\", \"password\": \"password\" }")).asJson()
                            .check(jsonPath("$.token").saveAs("jwt")))

                    .pause(1)

                    .exec(http("Transactie ophalen")
                            .get("/user/transactie")
                            .header("Authorization", "Bearer #{jwt}"));

    {
        setUp(scn.injectOpen(constantUsersPerSec(2).during(60))).protocols(httpProtocol);
    }
}
