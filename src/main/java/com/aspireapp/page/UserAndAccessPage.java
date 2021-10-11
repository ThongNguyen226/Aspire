package com.aspireapp.page;

import com.aspireapp.utils.action.Common;
import com.aspireapp.utils.action.Constant;
import com.aspireapp.utils.drivers.DriverUtils;
import org.openqa.selenium.By;

public class UserAndAccessPage extends GeneralPage {

    private String xpathRoles = "//div[@role='radio' and starts-with(@aria-label,'%s')]";
    private String xpathAccessRoles = "//div[@class='q-list']//div[contains(text(),'%s')]";
    private String xpathFinanceOptions = "//div[contains(text(),'%s')]";
    private String xpathNameAndEmail = "//div[starts-with(@class,'invitations-list-item')][.//div[contains(text(),'%1$s')] and .//div[contains(text(),'%2$s')]]";



    private By btnAddNew = By.xpath("//div[contains(@class,'invite-user')]");
    private By tbFullName = By.xpath("//div[@label='Full name']//input[@type='text']");
    private By tbEmail = By.xpath("//div[@label='Email address']//input[@type='email']");
    private By btnContinue = By.xpath("//button[@type='button'][.//span[text()='Continue']]");
    private By btnSignToSend = By.xpath("//button[@type='button'][.//span[text()='Sign to send invite']]");
    private By cbAgree = By.xpath("//div[@role='checkbox']");
    private By btnSignContract= By.xpath("//button[@type='button'][.//span[text()='Sign contract']]");
    private By tabPending = By.xpath("//div[text()='Pending']");



    public boolean isInformationDisplayedCorrect(String fullName, String email) {
        By lbFinanceOption = By.xpath(String.format(xpathNameAndEmail,fullName,email));
        return true;
    }


    public void clickOnPendingTab() {
        DriverUtils.waitForDisplayed(tabPending, Constant.mediumTimewait);
        DriverUtils.click(tabPending);
    }

    public void inviteNewUser(String fullName, String email, String[] roles) {

        String role = roles[0];
        String accessRole = roles[1];

        DriverUtils.waitForDisplayed(btnAddNew, Constant.mediumTimewait);
        DriverUtils.click(btnAddNew);
        Common.delay(Constant.shortTimeSleep);

        enterFullName(fullName);

        enterEmail(email);

        selectRole(role);

        DriverUtils.waitForDisplayed(btnSubmit, Constant.mediumTimewait);
        DriverUtils.click(btnSubmit);

        selectAcessRole(accessRole);

        if (accessRole.equals("Finance")) {
            System.out.println("=====Finance option: " + roles[2]);
            By lbFinanceOption = By.xpath(String.format(xpathFinanceOptions,roles[2]));
            DriverUtils.waitForDisplayed(lbFinanceOption, Constant.mediumTimewait);
            Common.delay(Constant.shortTimeSleep);
            DriverUtils.click(lbFinanceOption);

            By lbFinanceConfirm = By.xpath(String.format(xpathFinanceOptions,"Yes, issue a debit card"));
            DriverUtils.waitForDisplayed(lbFinanceConfirm, Constant.mediumTimewait);
            Common.delay(Constant.shortTimeSleep);
            DriverUtils.click(lbFinanceConfirm);
        }

        if (!accessRole.equals("Admin")) {
            DriverUtils.waitForDisplayed(btnContinue, Constant.mediumTimewait);
            DriverUtils.click(btnContinue);
        }

        DriverUtils.waitForDisplayed(btnSignToSend, Constant.mediumTimewait);
        Common.delay(Constant.shortTimeSleep);
        DriverUtils.click(btnSignToSend);

        DriverUtils.waitForDisplayed(cbAgree, Constant.mediumTimewait);
        Common.delay(Constant.shortTimeSleep);
        DriverUtils.click(cbAgree);

        Common.delay(Constant.shortTimeSleep);
        DriverUtils.waitForDisplayed(btnSignContract, Constant.mediumTimewait);
        DriverUtils.click(btnSignContract);

        DriverUtils.waitForInvisibility(btnSignContract,Constant.mediumTimewait);
    }

    public void enterFullName(String fullName) {
        DriverUtils.waitForDisplayed(tbFullName, Constant.mediumTimewait);
        DriverUtils.sendKeys(tbFullName,fullName);
    }

    public void enterEmail(String email) {
        DriverUtils.waitForDisplayed(tbEmail, Constant.mediumTimewait);
        DriverUtils.sendKeys(tbEmail,email);
    }

    public void selectRole(String role) {
        By rdRole = By.xpath(String.format(xpathRoles,role));
        DriverUtils.waitForDisplayed(rdRole, Constant.mediumTimewait);
        DriverUtils.click(rdRole);
    }

    public void selectAcessRole(String acessRole) {
        Common.delay(Constant.shortTimeSleep);
        By lbAcessRole = By.xpath(String.format(xpathAccessRoles,acessRole));
        DriverUtils.waitForDisplayed(lbAcessRole, Constant.mediumTimewait);
        DriverUtils.click(lbAcessRole);
    }

}
