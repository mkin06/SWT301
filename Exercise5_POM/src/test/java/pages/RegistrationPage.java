package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegistrationPage extends BasePage {

    private final By firstName = By.id("firstName");
    private final By lastName = By.id("lastName");
    private final By email = By.id("userEmail");
    private final By genderMale = By.xpath("//label[text()='Male']");
    private final By mobile = By.id("userNumber");
    private final By dateOfBirthInput = By.id("dateOfBirthInput");
    private final By subjectInput = By.id("subjectsInput");
    private final By uploadPicture = By.id("uploadPicture");
    private final By currentAddress = By.id("currentAddress");
    private final By stateDropdown = By.id("react-select-3-input");
    private final By cityDropdown = By.id("react-select-4-input");
    private final By submitBtn = By.id("submit");


    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public RegistrationPage open() {
        driver.get("https://demoqa.com/automation-practice-form");
        return this;
    }

    public RegistrationPage enterFirstName(String value) {
        type(firstName, value);
        return this;
    }

    public RegistrationPage enterLastName(String value) {
        type(lastName, value);
        return this;
    }

    public RegistrationPage enterEmail(String value) {
        type(email, value);
        return this;
    }

    public RegistrationPage selectGenderMale() {
        click(genderMale);
        return this;
    }

    public RegistrationPage enterMobile(String value) {
        type(mobile, value);
        return this;
    }

    public RegistrationPage selectDate(String dayCss) {
        click(dateOfBirthInput);
        click(By.cssSelector(dayCss));
        driver.findElement(dateOfBirthInput).sendKeys(Keys.TAB);
        return this;
    }

    public RegistrationPage enterSubject(String value) {
        type(subjectInput, value);
        driver.findElement(subjectInput).sendKeys(Keys.ENTER);
        return this;
    }

    public RegistrationPage selectHobby(String hobbyForAttr) {

        click(By.xpath("//label[@for='" + hobbyForAttr + "']"));
        return this;
    }

    public void submit() {
        jsClick(submitBtn);
    }

    public RegistrationPage uploadPicture(String fileName) {
        String path = System.getProperty("user.dir") + "/src/test/resources/" + fileName;
        driver.findElement(uploadPicture).sendKeys(path);
        return this;
    }

    public RegistrationPage enterAddress(String value) {
        type(currentAddress, value);
        return this;
    }

    public RegistrationPage selectState(String state) {
        WebElement stateInput = driver.findElement(stateDropdown);
        stateInput.sendKeys(state);
        stateInput.sendKeys(Keys.ENTER);
        return this;
    }

    public RegistrationPage selectCity(String city) {
        WebElement cityInput = driver.findElement(cityDropdown);
        cityInput.sendKeys(city);
        cityInput.sendKeys(Keys.ENTER);
        return this;
    }
    public boolean isModalVisible() {
        return driver.findElement(By.id("example-modal-sizes-title-lg")).isDisplayed();
    }

    public boolean isFieldError(String fieldName) {
        return driver.getPageSource().contains(fieldName);
    }

    public boolean isFileUploadError() {
        return driver.getPageSource().contains("invalid file");
    }

    public boolean isFormNotSubmitted() {
        return driver.findElements(By.id("example-modal-sizes-title-lg")).isEmpty();
    }

}
