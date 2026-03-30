package tests;

import com.github.javafaker.Faker;
import org.testng.annotations.Test;
import requestBuilder.ApiRequestBuilder;

public class registerUser {

    public static String registeredEmail;

    @Test
    public void loginAsAdminTest() {
        ApiRequestBuilder.UserLogin("noms@gmail.com", "Password@2")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);

    }

    @Test(priority = 1)
    public void registerUserTest() {
        registeredEmail = Faker.instance().internet().emailAddress();

        ApiRequestBuilder.registerUser("Simo", "Ndaba", registeredEmail, "Password@3","Password@3", "5328c91e-fc40-11f0-8e00-5000e6331276")
                .then()
                .log().all()
                .assertThat()
                .statusCode(201);
    }

    @Test(priority = 2, dependsOnMethods = "registerUserTest")
    public void approveUserTest() {
        ApiRequestBuilder.approveUser()
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test(priority = 3, dependsOnMethods ="approveUserTest" )
    public void loginRegisteredUser() {
        ApiRequestBuilder.UserLogin(registeredEmail, "Password@3")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }

}
