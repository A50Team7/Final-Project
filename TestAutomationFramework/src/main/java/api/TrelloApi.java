package api;

import com.google.gson.Gson;
import com.testframework.PropertiesManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;

public class TrelloApi {
    private RequestSpecification getRestAssured(){
        Gson deserializer = new Gson();
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .log().all()
                .baseUri(PropertiesManager.PropertiesManagerEnum.INSTANCE.getConfigProperties().getProperty("trello.api.baseUrl"))
                .queryParam("key", PropertiesManager.PropertiesManagerEnum.INSTANCE.getConfigProperties().getProperty("trello.users.user.apiKey"))
                .queryParam("token", PropertiesManager.PropertiesManagerEnum.INSTANCE.getConfigProperties().getProperty("trello.users.user.apiToken"));
    }

    // Authenticate
    public List<String> getUserBoards(){
        return getRestAssured()
                .get("/members/me")
                .thenReturn()
                .jsonPath()
                .get("idBoards");
    }

    // API: Create a board
    public BoardModel createBoard(String name, String description){
        return getRestAssured()
                .queryParam("name", name)
                .queryParam("description", description)
                .when()
                .post("/board")
                .then()
                .extract()
                .response()
                .as(BoardModel.class);
    }

    public Response deleteBoard(String boardId){
        return getRestAssured()
                .when()
                .delete("boards/" + boardId)
                .then()
                .extract()
                .response();
    }

    public ListModel createList(String boardId, String name){
        return getRestAssured()
                .when()
                .queryParam("name", name)
                .post("boards/" + boardId + "/lists")
                .then()
                .extract()
                .response()
                .as(ListModel.class);
    }

    public Response createCard(String listId, String name){
        return getRestAssured()
                .when()
                .queryParam("name", name)
                .queryParam("idList", listId)
                .post("/cards")
                .then()
                .extract()
                .response();
    }

    // API: Create 2 lists
    // API: Create a card
}