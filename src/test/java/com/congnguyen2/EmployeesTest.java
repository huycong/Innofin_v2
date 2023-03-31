package com.congnguyen2;

import com.congnguyen2.EmployeeSteps;
import net.thucydides.core.annotations.WithTag;
import org.junit.Test;
import org.junit.runner.RunWith;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;

@RunWith(SerenityRunner.class)
public class EmployeesTest {

    @Steps
    EmployeeSteps employeeSteps;

    @Test
    @Title("Get User")
    @WithTag("sprint_2")
    public void verifyValidUser() {
        System.out.println("Get user");
        employeeSteps.sendUser(1);
        employeeSteps.verifyStatusCode(200);
        employeeSteps.verifyId(1);
        employeeSteps.verifyName("Tiger Nixon");
        employeeSteps.verifyAge(61);
        employeeSteps.verifySalary(320800);
        employeeSteps.verifyMessage("Successfully! Record has been fetched.");

    }

    @Test
    @Title("Create User")
    @WithTag("sprint_2")
    public void createValidUser() {

        employeeSteps.createUser();
        employeeSteps.verifyStatusCode(301);
        //employeeSteps.verifyName("Shawn Test");
        //employeeSteps.verifyAge(30);
        //employeeSteps.verifySalary(11111);
        // employeeSteps.verifyMessage("Successfully! Record has been added.");

    }

}