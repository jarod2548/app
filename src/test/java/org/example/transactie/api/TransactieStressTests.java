package org.example.transactie.api;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.core.CoreDsl.StringBody;
import static io.gatling.javaapi.core.CoreDsl.constantUsersPerSec;
import static io.gatling.javaapi.core.CoreDsl.global;
import static io.gatling.javaapi.core.CoreDsl.rampUsers;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class TransactieStressTests extends Simulation {
    HttpProtocolBuilder httpProtocol =
            http.baseUrl("http://localhost:8080")
                    .contentTypeHeader("application/json")
                    .acceptHeader("application/json");

    ScenarioBuilder scn =
            scenario("Stress Test - Transactie API")
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
                    .repeat(50).on(
                            exec(
                                    http("Create Transactie")
                                            .post("/user/transactie")
                                            .body(StringBody("""
                                                {
                                                  "aantal": 100,
                                                  "beschrijving": "stress test",
                                                  "datum": "2026-01-01T10:00:00"
                                                }
                                            """))
                                            .check(status().in(201, 200))
                            )
                    );

    {
        setUp(
                scn.injectOpen(
                        rampUsers(50).during(10),
                        rampUsers(100).during(10),
                        rampUsers(200).during(20),
                        constantUsersPerSec(50).during(60)
                )
        ).protocols(httpProtocol)
                .assertions(
                        global().failedRequests().percent().lt(20.0)
                );
    }
}
