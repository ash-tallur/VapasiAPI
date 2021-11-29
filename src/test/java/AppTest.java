import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class AppTest {

    @Test
    public void createUserDetailsWithResponse(){

        SendingUserDetails sendData = new SendingUserDetails();
        sendData.setName("Ak");
        sendData.setJob("Freelancer");




        Response response =
                given().contentType(ContentType.JSON).
                        body(sendData).log().body().
                        when().post("https://reqres.in/api/users").
                        then().assertThat().statusCode(201).and().extract().response();

        System.out.println("The response created by post is: "+response.asPrettyString());
    }

    @Test
    public void creatingAndReceivingDetails(){

        SendingUserDetails sendData = new SendingUserDetails();
        sendData.setName("Niti");
        sendData.setJob("Painter");

        CreatedUserDetails userCreated =
                given().contentType(ContentType.JSON).
                        body(sendData).log().body().
                        when().post("https://reqres.in/api/users").as(CreatedUserDetails.class);

        System.out.println("The name, id  of the user created is : " + userCreated.getName() + "," + userCreated.getId());

        Assert.assertEquals("Niti",userCreated.getName());



    }
}
