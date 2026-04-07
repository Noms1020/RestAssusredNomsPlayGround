package tests;

import Utilities.databaseConnection;
import com.github.javafaker.Faker;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import requestBuilder.ApiRequestBuilder;
import Utilities.databaseConnection;

import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.equalTo;

import java.sql.SQLException;

public class registerUser {

    @BeforeClass
    public void setup() throws SQLException {
        databaseConnection.dbConnection();
    }

    public static String registeredEmail;


    @Test
    public void registerUserTest() {
        registeredEmail = Faker.instance().internet().emailAddress();

        ApiRequestBuilder.registerUser("Simo", "Ndaba", registeredEmail, "Password@3", "Password@3", "1deae17a-c67a-4bb0-bdeb-df0fc9e2e526")
                .then()
                .log().all()
                .assertThat()
                .statusCode(201);
    }

    @Test(priority = 1)
    public void loginAsAdminTest() {
        ApiRequestBuilder.UserLogin(databaseConnection.getEmail, databaseConnection.getPassword)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);

    }


    @Test(priority = 2)
    public void approveUserTest() {
        ApiRequestBuilder.approveUser()
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test(priority = 3)
    public void UpdateUserRoleTest() {
        ApiRequestBuilder.UpdateUserRoleResponse("admin")
                .then()
                .assertThat()
                .statusCode(200)
                .body("message", equalTo("User role updated successfully"));
        System.out.println("user role is updated to admin");


    }

    @Test(priority = 4)
    public void loginRegisteredUser() {
        ApiRequestBuilder.UserLogin(registeredEmail, "Password@3")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("success", equalTo(true));
                 //.body("data.role", equalTo("admin"));
    }

    @Test(priority = 5)
    public void AdminReloginTest() {
        ApiRequestBuilder.UserLogin(databaseConnection.getEmail, databaseConnection.getPassword)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);

    }

    @Test(priority = 6)
    public void deleteUserTest() {
        ApiRequestBuilder.deleteUserResponse()
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("message", equalTo("User deleted successfully"));
    }


}
