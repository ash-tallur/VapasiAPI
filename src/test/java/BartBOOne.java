import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class BartBOOne {

    @Test
    public void getDepartureTime(){
        given().
                when().
                get("https://api.bart.gov/api/etd.aspx?cmd=etd&orig=RICH&plat=4&json=y").
                then().
                assertThat().statusCode(200);

    }

    @Test
    public void verifyColor(){
        given().when().
                get("https://api.bart.gov/api/route.aspx?cmd=routes&key=MW9S-E7SL-26DU-VV8V&date=04/15/2021").
                then().
                assertThat().
                body("root.routes.route[1].color", equalTo("BEIGE")).
                body("root.routes.route.name",hasItem("Daly City to Dublin/Pleasanton")).
                body("root.routes.route.direction",not(hasItem("east")));
        
    }

    @Test
    public void checkContent(){
        given().when().
                get("https://api.bart.gov/api/route.aspx?cmd=routes&key=MW9S-E7SL-26DU-VV8V&date=04/15/2021&json=y").
                then().assertThat().contentType(ContentType.JSON);
    }

    @Test
    public void display(){
        given().log().all().
                when().get("https://api.bart.gov/api/route.aspx?cmd=routes&key=MW9S-E7SL-26DU-VV8V&date=04/15/2021&json=y").
                then().log().body();
    }
}
