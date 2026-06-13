package org.example.transactie.api;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class TransactieCapacityTests extends Simulation {
    HttpProtocolBuilder httpProtocol =
            http.baseUrl("http://localhost:8080")
                    .contentTypeHeader("application/json")
                    .acceptHeader("application/json");

    ScenarioBuilder scn =
            scenario("Capacity Test - Transactie API")
                    .exec(
                            http("Login")
                                    .post("/login")
                                    .body(StringBody("""
                                        {
                                          "username": "user1",
                                          "wachtwoord": "password"
                                        }
                                    """))
                                    .check(status().is(200))
                    )
                    .exec(
                                    http("Create Transactie")
                                            .post("/user/transactie")
                                            .body(StringBody("""
                                                {
                                                  "aantal": 100,
                                                  "beschrijving": "capacity test",
                                                  "datum": "2026-01-01T10:00:00"
                                                }
                                            """))
                                            .check(status().is(201))
                            );

    {
        setUp(
                scn.injectOpen(
                        rampUsersPerSec(1).to(50).during(60),
                        rampUsersPerSec(50).to(150).during(120),
                        rampUsersPerSec(150).to(300).during(120)
                )
        ).protocols(httpProtocol)
                .assertions(
                        global().failedRequests().percent().lt(1.0),
                        global().responseTime().mean().lt(500)
                );
    }
}
