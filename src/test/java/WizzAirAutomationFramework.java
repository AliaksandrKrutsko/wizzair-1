import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.wizzair.steps.Steps;

import junit.framework.Assert;


public class WizzAirAutomationFramework {

    private Steps steps;

    @BeforeMethod(description = "Init browser")
    public void setUp() {
        steps = new Steps();
        steps.initBrowser();
    }

    @Test
    public void flitsSum() {
        steps.findFlight();
        Assert.assertEquals(steps.getFirstPrice(), steps.getSum());
    }
    
    @AfterMethod
    public void close() {
    	steps.closeBrowser();
    }
}
