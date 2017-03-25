package nz.mikhailov.atlas;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static java.lang.System.getenv;

public class ProbesResourceContractTest {

  @Test
  public void shouldReturnExpectedJsonWhenProbeQueriedById() throws Exception {

    given()
        .accept(JSON)
        .pathParam("probeId", getenv("PROBE_ID"))
        .queryParam("key", getenv("API_KEY"))
    .when()
        .get("https://atlas.ripe.net/api/v2/probes/{probeId}")
    .then()
        .statusCode(200)
        .contentType(JSON)
        .body(matchesJsonSchemaInClasspath("probe.schema.json"));
  }

}
