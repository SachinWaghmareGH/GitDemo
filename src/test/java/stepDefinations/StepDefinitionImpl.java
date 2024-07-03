package stepDefinations;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import FW24.baseTest.BaseTest;
import FW24.pageObjects.CartPage;
import FW24.pageObjects.CheckOut;
import FW24.pageObjects.LandingPage;
import FW24.pageObjects.OrderConfirmation;
import FW24.pageObjects.ProductCatalog;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl  extends BaseTest{
	public LandingPage landingPage;
	public ProductCatalog productCatalog;
	public OrderConfirmation orderConfirmation;
	public CartPage cartPage;
	
	@Given("I landed on the ecommerce page") 
	public void I_landed_on_the_ecommerce_page() throws IOException {
		landingPage=launchApplication(); 
	}
	
	@Given("^Logged in with username (.+) and password (.+)$") 
	public void logged_in_with_username_username_and_password(String username, String password) {
		productCatalog = landingPage.loginApplication(username, password);
	}
	
	@When("^I add product (.+) to cart$")
	public void i_add_product_to_cart(String productName) throws InterruptedException {
		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addToCart(productName);
	}
	 @When("^checkout (.+) and submit the order$")
	 public void checkout_product_and_submit_the_order(String productName) {
		 cartPage = productCatalog.goToCartPage();	 
			Boolean match = cartPage.verifyCartProduct(productName);
			Assert.assertTrue(match);
			CheckOut checkOut = cartPage.goTocheckOut();
			checkOut.selectCountry("india");
			orderConfirmation = checkOut.submitOrder();
	 }
	 
	 @Then("{string} message is displayed on confirmationPage") 
	 public void message_displayed_confirmationPage(String string) {
		 String cnfmMsg = orderConfirmation.getconfirmMessage();
			Assert.assertTrue(cnfmMsg.equalsIgnoreCase(string));
	 }

}
