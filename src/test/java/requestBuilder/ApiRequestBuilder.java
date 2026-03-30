package requestBuilder;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;

import static common.BaseURIs.baseURI;

import static payloadBuilder.PayloadBuilder.*;

public class ApiRequestBuilder {

    static String authToken;
    static  String registeredUserID;
    public static Response UserLogin(String email, String password) {

        String apiPath = "/APIDEV/login";
        Response response = RestAssured.given()
                .baseUri(baseURI)
                .basePath(apiPath)
                .header("Content-Type", "application/json")
                .body(Loginpayload(email, password))
                .log().all()
                .post().prettyPeek()
                .then().extract().response();
        authToken = response.jsonPath().getString("data.token");
        return response;
    }

    public static Response registerUser(String firstName, String lastName, String email, String password,String confirmPassword, String groupId) {

        String apiPath = "/APIDEV/register";
        Response response = RestAssured.given()
                .baseUri(baseURI)
                .basePath(apiPath)
                .header("Content-Type", "application/json")
                .body(RegisterPayload(firstName,lastName, email, password,confirmPassword, groupId))
                .log().all()
                .post().prettyPeek()
                .then().extract().response();
        registeredUserID= response.jsonPath().getString("data.id");






        return response;
    }


    public static Response approveUser( ) {

        String apiPath = "/APIDEV/admin/users/"+registeredUserID+"/approve";
        return  RestAssured.given()
                .baseUri(baseURI)
                .basePath(apiPath)
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + authToken)
                .log().all()
                .put().prettyPeek()
                .then().extract().response();

    }

}
