import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class CountriesStep {
    public static String capitalName;
    public static String currency;
    Countries util = new Countries();

    @Given("^I trigger the get request for countries$")
    public void iTriggerTheGetRequestForCountries() throws Exception {
        util.getCountries();
    }

    @When("I receive the successful response {int} from request")
    public void iRecieveTheSuccessfulResponseFromRequest(int statusCode) throws Exception {
        Assert.assertEquals(util.getCode(), statusCode);
    }

    @Then("I validate the {int} of countries")
    public void iValidateTheIfCountries(int countOfCountries) throws Exception {
        Assert.assertEquals(util.getCountriesCount(), countOfCountries);
    }

    @When("I extract the capital name from response")
    public void iExtractCountryNameFromTheResponse() throws Exception {
        capitalName = util.getListOfCapitals().get(0);
        currency = util.getListOfCurrency().get(0).get(0);
    }

    @When("I get the capital details")
    public void iExtractCapitalsFromTheResponse() throws Exception {
        util.getCapitalDetails(capitalName);
    }

    @Then("I validate the currency in the response")
    public void iValidateTheCurrencyInTheResponse() {
        Assert.assertEquals(currency, util.getExtractedCurrency());
    }

    @Then("I trigger the get capital details for {string}")
    public void iValidateTheCurrencyInTheResponse(String capitalName) {
        util.getCapitalDetails(capitalName);
    }

}
