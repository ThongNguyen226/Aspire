package com.aspireapp.page;

import com.aspireapp.utils.action.Common;
import com.aspireapp.utils.action.Constant;
import com.aspireapp.utils.drivers.DriverUtils;
import org.openqa.selenium.By;

public class GeneralPage {

    protected By btnSubmit = By.xpath("//button[@type='submit']");

    private String xpathLeftNav = "//a/div[text()='%s']";
    private String xpathMoreIncluding = "//div[@class='flex items-center' and contains(text(),'%s')]";

    public void gotoMorePage(String menu) {
        clickOnLeftNav("More");
        By linkMoreIncluding = By.xpath(String.format(xpathMoreIncluding,menu));
        DriverUtils.waitForDisplayed(linkMoreIncluding,Constant.mediumTimewait);
        DriverUtils.click(linkMoreIncluding);
    }

    public void clickOnLeftNav(String menu) {
        By linkLeftNav = By.xpath(String.format(xpathLeftNav,menu));
        DriverUtils.waitForDisplayed(linkLeftNav,Constant.mediumTimewait);
        DriverUtils.click(linkLeftNav);
    }


}
