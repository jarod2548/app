package org.example.transactie.api;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpDsl;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;


public class TransactieLoadTests extends Simulation {



    HttpProtocolBuilder httpProtocol =
            http.baseUrl("http://localhost:8080")
                    .contentTypeHeader("application/json")
                    .acceptHeader("application/json");

    String payload = """
        {
          "amount": 100,
          "description": "gatling test"
        }
        """;

    ScenarioBuilder scn =
            scenario("Transactie Load Test")
                    .exec(
                            http("Login")
                                    .post("/login")
                                    .asJson()
                                    .body(StringBody("""
                {
                  "username": "user1",
                  "wachtwoord": "password"
                }
                """))
                                    .check(HttpDsl.status().is(200))
                    )

                    // TEST BUSINESS ENDPOINT MANY TIMES
                    .repeat(100).on(
                            exec(
                                    http("Create Transactie")
                                            .post("/user/transactie")
                                            .asJson()
                                            .body(StringBody(session -> """
                    {
                      "aantal": 100,
                      "beschrijving": "gatling test",
                      "datum": "%s"
                    }
                    """.formatted(java.time.LocalDateTime.now())))
                                            .check(HttpDsl.status().is(201))
                            )
                    );

    {
        setUp(
                scn.injectOpen(
                        rampUsers(20).during(10),
                        constantUsersPerSec(10).during(30)
                )
        ).protocols(httpProtocol);
    }
}
