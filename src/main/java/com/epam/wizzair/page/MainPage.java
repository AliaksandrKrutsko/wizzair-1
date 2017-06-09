package com.epam.wizzair.page;

import com.epam.wizzair.driver.DriverSingleton;
import com.epam.wizzair.helper.DriverConfig;
import com.epam.wizzair.bean.enumeration.PassengerSetting;
import com.epam.wizzair.page.exception.NotSignedException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.epam.wizzair.driver.DriverSingleton.getDriver;

public class MainPage extends AbstractPage{

    private WebDriverWait wait = new WebDriverWait(getDriver(), 10, 1000);

    private final String PATH_DATE = "//div[@class='calendar']//td[@data-day='";
    private final String PATH_CITY = "//strong[text()='";

    @FindBy(id= "search-departure-station")
    private WebElement inputOriginName;

    @FindBy(id = "search-arrival-station")
    private WebElement inputDestinationName;

    @FindBy(xpath = "//*[@id='search-departure-date']")
    private WebElement departureDateName;

    @FindBy(xpath = "//*[@id='search-return-date']")
    private WebElement returnDateName;

    @FindBy(xpath = "//form[@name='flight-search']//button[text()='Search']")
    private WebElement searchButton;

    @FindBy(xpath = "//ul[@class='navigation__container']//button[text()[contains(.,'Sign in')]]")
    private WebElement loginButton;

    @FindBy(xpath = "//ul[@class='navigation__container']//button[text()[contains(., 'Information & Services')]]")
    private WebElement servicesButton;

    //@FindBy(linkText = "Timetable")
    @FindBy (xpath = "//div[@id='footer_TAC12ECC3001_Col02']//a[@class='sitemap__link' and text()='Timetable']")
    private WebElement timetableButton;

    @FindBy(css = "[class='cookie-policy__button'")
    private WebElement stickyBar;

    @FindBy(linkText = "Map")
    private WebElement mapButton;


    @FindBy(id = "search-passenger")
    private WebElement passengerField;

    @FindBy(xpath = "//div[@class='flight-search__panel flight-search__panel--sub flight-search__panel--sub--people flight-search__panel--sub-transition']" +
            "/div[3]/button[@class='stepper__button stepper__button--add']")
    private WebElement addAdult;

    @FindBy(xpath = "//div[@class='flight-search__panel flight-search__panel--sub flight-search__panel--sub--people flight-search__panel--sub-transition']" +
            "/div[4]/button[@class='stepper__button stepper__button--add']")
    private WebElement addChild;

    @FindBy(xpath = "//div[@class='flight-search__panel flight-search__panel--sub flight-search__panel--sub--people flight-search__panel--sub-transition']" +
            "/div[5]/button[@class='stepper__button stepper__button--add']") //TODO: refactor locators
    private WebElement addInfant;

    @FindBy(css = "[class=\"booking-flow__itinerary__logo__image\"")
    private WebElement returnToMainPage;

    public void openPage(){
        DriverSingleton.openNewWindowJS();
        getDriver().navigate().to(DriverConfig.urlMain());
    }

    public void closeWindow() {
        DriverSingleton.closeWindow();
    }

    public MainPage fillOrigin(String origin){
        inputOriginName.click();
        inputOriginName.clear();
        inputOriginName.sendKeys(origin);
        WebElement originCity = getDriver().findElement(By.xpath(PATH_CITY + origin + "']"));
        originCity.click();
        return this;
    }

    public MainPage fillDestination(String destination){
        wait.until(ExpectedConditions.elementToBeClickable(inputDestinationName));
        inputDestinationName.click();
        inputDestinationName.clear();
        inputDestinationName.sendKeys(destination);
        WebElement destinationCity = getDriver().findElement(By.xpath(PATH_CITY + destination + "']"));
        destinationCity.click();
        return this;
    }

    public MainPage fillDepartureDate(int day) {
        departureDateName.click();
        WebElement calendarDepartureDate = getDriver().findElement(By.xpath(PATH_DATE + day + "']"));
        calendarDepartureDate.click();
        return this;
    }

    public MainPage fillReturnDate(int day) {
        returnDateName.click();
        WebElement calendarReturnDate = getDriver().findElement(By.xpath(PATH_DATE + day + "']"));
        calendarReturnDate.click();
        return this;
    }

    public MainPage setPassenger(PassengerSetting passengerSetting, int numberOfPassengers) {
        int i = 0;
        do
        {
            switch (passengerSetting) {
                case ADULT:
                    numberOfPassengers=numberOfPassengers-1;
                    passengerField.click();
                    addAdult.click();
                    break;
                case CHILD:
                    passengerField.click();
                    addChild.click();
                    break;
                case INFANT:
                    passengerField.click();
                    passengerField.click();
                    addInfant.click();
                    break;
                case NO:
                    break;
            }
            i++;
        }
        while (i<=numberOfPassengers-1);
        return this;
    }

    public SearchResult search() {
        searchButton.click();
        return new SearchResult();
    }

    public LoginPage signIn(){
        wait.until(ExpectedConditions.visibilityOf(loginButton));
        loginButton.click();
        return new LoginPage();
    }

    public ProfilePage gotoProfile() throws NotSignedException {
        //note that variable loginButton is just 3rd in the container
        if(loginButton.getText().equalsIgnoreCase("sign in")){
            throw new NotSignedException();
        } else {
            DriverSingleton.getDriver().get("https://wizzair.com/en-gb/profile");
        }
        return new ProfilePage();
    }

    public MainPage servicesClick() {
        wait.until(ExpectedConditions.visibilityOf(servicesButton));
        servicesButton.click();
        return this;
    }

    public TimetablePage timetableClick() {
        wait.until(ExpectedConditions.elementToBeClickable(timetableButton));
        timetableButton.click();
        return new TimetablePage();
    }

    public MapPage mapClick() {
        wait.until(ExpectedConditions.elementToBeClickable(mapButton));
        mapButton.click();
        return new MapPage();
    }

    public MainPage stickyBarClose() {

        if(stickyBar.isDisplayed()){
            stickyBar.click();
        }
        return this;
    }

    public MainPage returnToMainPage() {
        returnToMainPage.click();
        return this;
    }


}