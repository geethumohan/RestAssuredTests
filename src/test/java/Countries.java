import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class Countries {

    private static final String url = "https://restcountries.eu/rest/v2";
    private static int count;
    private static int status;
    private static String extractedCurrency;
    private static List<String> countries;
    private static List<String> captitals;
    private static List<ArrayList<String>> currencies;

    public void getCountries() throws Exception {

        try {
            RestAssured.baseURI = url;
            Response response = given()
                    .when().get("/all?fields=name;capital;currencies;latlng")
                    .then().extract().response();
            JsonPath jsonPath = new JsonPath(response.asString());
            countries = jsonPath.getList("name");
            captitals = jsonPath.getList("capital");
            currencies = jsonPath.getList("currencies.code");
            count = countries.size();
            status = response.getStatusCode();
            if(status == 200) {
                validateSchema(response, "countrySchema.json");
            }
        } catch (Exception e) {
            System.out.println("There is an error connecting to the API: " + e);
            e.getStackTrace();
        }
    }

    public void getCapitalDetails(String capital) {
        try {
            RestAssured.baseURI = url;
            Response response = given()
                    .when().get("/capital/" + capital + "?filds=name;capital;currencies;latlng;regionalBlocs")
                    .then().extract().response();
            JsonPath jsonPath = new JsonPath(response.asString());
            status = response.getStatusCode();
            extractedCurrency = jsonPath.getString("[0].currencies[0].code");
            if(status == 200) {
                validateSchema(response, "capitalResponseSchema.json");
            }
        } catch (Exception e) {
            System.out.println("There is an error connecting to the API: " + e);
            e.getStackTrace();
        }
    }

    private void validateSchema(Response response, String filePath) {
        response.then().assertThat().body(matchesJsonSchemaInClasspath(filePath));
    }

    public int getCode() {
        return status;
    }

    public int getCountriesCount() {
        return count;
    }

    public String getExtractedCurrency() {
        return extractedCurrency;
    }

    public List<String> getListOfCapitals() {
        return captitals;
    }

    public List<ArrayList<String>> getListOfCurrency() {
        return currencies;
    }

}
