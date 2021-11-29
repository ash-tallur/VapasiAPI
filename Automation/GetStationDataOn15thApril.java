import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetStationDataOn15thApril {

    @Test
    public void testGetStationDataOn15thAprilAndValidateForStatusCode(){
        given().
        when().
            get("https://api.bart.gov/api/route.aspx?cmd=routes&key=MW9S-E7SL-26DU-VV8V&date=04/15/2021&json=y").
        then().
            assertThat().statusCode(200);
    }

}
