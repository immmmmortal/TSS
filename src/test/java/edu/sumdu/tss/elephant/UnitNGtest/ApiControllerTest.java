package edu.sumdu.tss.elephant.UnitNGtest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.sumdu.tss.elephant.controller.api.ApiController;
import edu.sumdu.tss.elephant.helper.Hmac;
import edu.sumdu.tss.elephant.helper.exception.AccessRestrictedException;
import edu.sumdu.tss.elephant.helper.exception.BackupException;
import edu.sumdu.tss.elephant.helper.exception.NotFoundException;
import edu.sumdu.tss.elephant.helper.utils.ResponseUtils;
import edu.sumdu.tss.elephant.model.Backup;
import edu.sumdu.tss.elephant.model.BackupService;
import edu.sumdu.tss.elephant.model.Database;
import edu.sumdu.tss.elephant.model.DatabaseService;
import edu.sumdu.tss.elephant.model.User;
import edu.sumdu.tss.elephant.model.UserService;
import io.javalin.http.Context;
import io.javalin.plugin.openapi.jackson.JacksonModelConverterFactory;
import io.javalin.plugin.openapi.ui.ReDocOptions;
import io.javalin.plugin.openapi.ui.SwaggerOptions;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import static org.hamcrest.Matchers.equalTo;

class ApiControllerTest {

    private RequestSpecification requestSpec;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 7000;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        requestSpec = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("publickey", "1234567890") // replace with actual public key
                .header("signature", "1234567890"); // replace with actual signature
    }

    @Test
    void backup_whenValidInput_shouldReturnNoContent() throws JsonProcessingException {
        // Arrange
        String dbName = "testdb";
        String pointName = "testpoint";
        Backup backup = new Backup();
        backup.setName(pointName);
        backup.setData("testdata");

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(backup);

        // Act & Assert
        requestSpec
                .body(requestBody)
                .when()
                .post("/api/v1/database/{name}/create/{point}", dbName, pointName)
                .then()
                .statusCode(ApiController.HTTP_STATUS_NO_CONTENT);
    }

    @Test
    void backup_whenInvalidPublicKey_shouldReturnForbidden() {
        // Arrange
        String dbName = "testdb";
        String pointName = "testpoint";
        Backup backup = new Backup();
        backup.setName(pointName);
        backup.setData("testdata");

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = null;
        try {
            requestBody = objectMapper.writeValueAsString(backup);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        // Act & Assert
        requestSpec
                .header("publickey", "invalid")
                .body(requestBody)
                .when()
                .post("/api/v1/database/{name}/create/{point}", dbName, pointName)
                .then()
                .statusCode(ApiController.HTTP_STATUS_FORBIDDEN);
    }

    @Test
    void testBackupSuccess() throws Exception {
        // Arrange
        var ctx = mock(Context.class);
        var database = new Database("testDb", "testUser", false);
        var backup = new Backup("testBackup", "testUser");
        var backupJson = new ObjectMapper().writeValueAsString(backup);

        when(ctx.pathParam("name")).thenReturn(database.getName());
        when(ctx.header("publickey")).thenReturn("testPublicKey");
        when(ctx.header("signature")).thenReturn("testSignature");
        when(UserService.byPublicKey(anyString())).thenReturn(new User("testUser", "testPrivateKey"));
        when(Hmac.calculate(anyString(), anyString())).thenReturn("testSignature");
        when(DatabaseService.activeDatabase(anyString(), anyString())).thenReturn(database);

        // Act
        ApiController.backup(ctx);

        // Assert
        verify(ctx).status(ApiController.HTTP_STATUS_NO_CONTENT);
        verify(DatabaseService).backup(anyString(), anyString(), anyString());
    }

    @Test
    void testBackupAccessRestrictedException() throws Exception {
        // Arrange
        var ctx = mock(Context.class);
        var database = new Database("testDb", "testUser", false);

        when(ctx.pathParam("name")).thenReturn(database.getName());
        when(ctx.header("publickey")).thenReturn("testPublicKey");
        when(UserService.byPublicKey(anyString())).thenThrow(new AccessRestrictedException("Invalid public key"));

        // Act
        ApiController.backup(ctx);

        // Assert
        verify(ctx).json(ResponseUtils.error("Can't validate user"));
        verify(ctx).status(ApiController.HTTP_STATUS_FORBIDDEN);
    }

    @Test
    void testBackupBackupException() throws Exception {
        // Arrange
        var ctx = mock(Context.class);
        var database = new Database("testDb", "testUser", false);

        when(ctx.pathParam("name")).thenReturn(database.getName());
        when(ctx.header("publickey")).thenReturn("testPublicKey");
        when(UserService.byPublicKey(anyString())).thenReturn(new User("testUser", "testPrivateKey"));
        when(DatabaseService.activeDatabase(anyString(), anyString())).thenReturn(database);
        doThrow(new BackupException("Error")).when(DatabaseService).backup(anyString(), anyString(), anyString());

        // Act
        ApiController.backup(ctx);

        // Assert
        verify(ctx).json(ResponseUtils.error("Backup errorError"));
        verify(ctx).status(ApiController.HTTP_STATUS_INTERNAL_SERVER_ERROR);
    }

    @Test
    void testBackupNotFoundException() throws Exception {
        // Arrange
        var ctx = mock(Context.class);
        var database = new Database("testDb", "testUser", false);

        when(ctx.pathParam("name")).thenReturn(database.getName());
        when(ctx.header("publickey")).thenReturn("testPublicKey");
        when(UserService.byPublicKey(anyString())).thenReturn(new User("testUser", "testPrivateKey"));
        when(DatabaseService.activeDatabase(anyString(), anyString())).thenThrow(new NotFoundException("Database not found"));

        // Act
        ApiController.backup(ctx);

        // Assert
        verify(ctx).json(ResponseUtils.error("Database not found"));
        verify(ctx).status(ApiController.HTTP_STATUS_NOT_FOUND);
    }

}



