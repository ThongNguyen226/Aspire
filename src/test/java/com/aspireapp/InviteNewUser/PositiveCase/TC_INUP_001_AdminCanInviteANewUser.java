package com.aspireapp.InviteNewUser.PositiveCase;

import com.aspireapp.TestBase;
import com.aspireapp.utils.action.Common;
import com.aspireapp.utils.config.Config;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;


public class TC_INUP_001_AdminCanInviteANewUser extends TestBase {

    @DataProvider(name="roleLists")
    public Object[][] testDataExample(){
        return Common.readDataRoles();
    }

    @Test(dataProvider = "roleLists")
    public void TC_INUP_001_AdminCanInviteANewUser(String[] roleLists) {
        System.out.println("Log in with======= "+ Config.getAdminUser() +"========="+Config.getAdminPin());
        loginPage.logIn(Config.getAdminUser(),Config.getAdminPin());

        loginPage.gotoMorePage("Users and Access");
        String fullName = Common.getRandomString("Thong");
        String email = Common.getRandomEmail("Email");

        System.out.println("Invite User======= "+ fullName +"========="+email);
        System.out.println("Role in Company======= "+ roleLists[0]);
        System.out.println("Access Role======= "+ roleLists[1]);

        userAndAccessPage.inviteNewUser(fullName,email, roleLists);
        userAndAccessPage.clickOnPendingTab();
        Assert.assertTrue(userAndAccessPage.isInformationDisplayedCorrect(fullName,email));
    }
}
