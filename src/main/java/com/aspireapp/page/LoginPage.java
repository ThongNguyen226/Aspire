package com.aspireapp.page;

import com.aspireapp.utils.action.Common;
import com.aspireapp.utils.action.Constant;
import com.aspireapp.utils.drivers.DriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

public class LoginPage extends GeneralPage {

    private By tbEmailPhone = By.xpath("//input[@name='username']");
    private By inputPasswords = By.xpath("//form[@class='q-form auth-form text-center']");

    public void logIn(String user, String pin) {
        //Enter Email or Phone
        enterUser(user);

        //Click Next button
        DriverUtils.click(btnSubmit);

        //Enter pin
        enterPin(pin);
    }

    public void enterUser(String user) {
        DriverUtils.waitForDisplayed(tbEmailPhone, Constant.mediumTimewait);
        DriverUtils.sendKeys(tbEmailPhone,user);
    }

    public void enterPin(String pin) {
        for (int index=0;index<pin.length();index++){
            String chartAt = Character.toString(pin.charAt(index));
            DriverUtils.waitForDisplayed(inputPasswords, Constant.mediumTimewait);
            Actions action = new Actions(DriverUtils.getDriver());
            action.sendKeys(chartAt).build().perform();
            //Common.delay(Constant.shortTimeSleep);
        }
    }


}
