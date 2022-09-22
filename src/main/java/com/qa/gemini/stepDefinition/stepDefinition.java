package com.qa.gemini.stepDefinition;

import com.qa.gemini.commonUtils.utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.util.HashMap;
import java.util.Map;

import static com.qa.gemini.commonUtils.utils.Gettoken2;
import static com.qa.gemini.commonUtils.utils.token;

public class stepDefinition {
    int status;

    @Given("^Set endpoint and method \"(.*)\" and \"(.*)\"$")
    public void hitApiWithStep(String url, String method) throws Exception {
        status = utils.HitAPI(url, method, "Test for " + method.toUpperCase()).getStatus();
    }

    @Given("^Authenticate endpoint and method \"(.*)\" and \"(.*)\"$")
    public void hitApiWithoutStep(String url, String method) throws Exception {
        status = utils.HitAPI(url, method).getStatus();
    }

    @Then("Verify Status code {int}")
    public void check_status_code(int Expected) {
        utils.VerifyStatusCode(Expected, status);
    }

    @Given("^Set token endpoint and method \"(.*)\" and \"(.*)\"$")
    public void hitApiToken(String url, String method) throws Exception {
        Map<String, String> headers = new HashMap<>();
        String j = token();
        assert j != null;
        String jnew = j.replaceAll("^\"|\"$", "");
        headers.put("Authorization", "Bearer " + jnew);
        status = utils.HitAPIWithToken(url, method, "Token Authentication", headers).getStatus();
    }

    @Given("^Set Wrong token endpoint and method \"(.*)\" and \"(.*)\"$")
    public void hitApiTokenWrong(String url, String method) throws Exception {
        Map<String, String> headers = new HashMap<>();
        String j = token();
        String jnew = j.replaceAll("^\"|\"$", "");
        headers.put("Authorization", "Bearer " + jnew + "pawan");
        status = utils.HitAPIWithToken(url, method, headers).getStatus();
    }

    @Given("^Set Empty token endpoint and method \"(.*)\" and \"(.*)\"$")
    public void hitApiTokenEmpty(String url, String method) throws Exception {
        status = utils.HitAPIWithToken(url, method).getStatus();
    }

    @Given("^Set endpoint and method and SampleName \"(.*)\" and \"(.*)\" and \"(.*)\"$")
    public void Login(String url, String method, String SampleName) throws Exception {
        status = utils.LoginUser(url, method, SampleName, "Login user").getStatus();
    }

    @Given("^Set credentials endpoint and method and SampleName \"(.*)\" and \"(.*)\" and \"(.*)\"$")
    public void Loginwrong(String url, String method, String SampleName) throws Exception {
        status = utils.LoginUser(url, method, SampleName).getStatus();
    }

    @Given("^Set Suite-API endpoint and method and SampleName \"(.*)\" and \"(.*)\" and \"(.*)\"$")
    public void Suite_Api(String url, String method, String SampleName) throws Exception {
        status = utils.CheckAPiWithAuth(url, method, Gettoken2(), SampleName).getStatus();
    }

    @Given("^Set Suite-API using Wrong Authentication endpoint and method and SampleName \"(.*)\" and \"(.*)\" and \"(.*)\"$")
    public void Suite_Api2(String url, String method, String SampleName) throws Exception {
        status = utils.CheckAPiWithAuth(url, method, Gettoken2() + "pawan", SampleName).getStatus();
    }

    @Given("^Set Suite-API when headers not given endpoint and method and SampleName \"(.*)\" and \"(.*)\" and \"(.*)\"$")
    public void Suite_Api3(String url, String method, String SampleName) throws Exception {
        status = utils.CheckAPiWithAuth(url, method, SampleName).getStatus();
    }

    @Given("^Update Suite using endpoint and method and SampleName \"(.*)\" and \"(.*)\" and \"(.*)\"$")
    public void UpdateSuite(String url, String method, String SampleName) throws Exception {
        status = utils.requestSuiteApi(url, method, Gettoken2(), SampleName, "GEMPYP_TEST_PROD_63700467-4D93-46AB-A46E-727B2E85DC3F").getStatus();
    }

    @Given("^Update Suite when S-run-id not present using endpoint and method and SampleName \"(.*)\" and \"(.*)\" and \"(.*)\"$")
    public void UpdateSuite2(String url, String method, String SampleName) throws Exception {
        status = utils.requestSuiteApi(url, method, Gettoken2(), SampleName, "b2f779e7-a4f2-44d8-a557-b3426ea520c14").getStatus();
    }

    @Given("^Update Suite using wrong Authentication using endpoint and method and SampleName \"(.*)\" and \"(.*)\" and \"(.*)\"$")
    public void UpdateSuite3(String url, String method, String SampleName) throws Exception {
        status = utils.requestSuiteApi(url, method, Gettoken2() + "pawan", SampleName, "b2f779e7-a4f2-44d8-a557-b3426ea520c14").getStatus();
    }

    @Given("^Update Suite without Authentication using endpoint and method and SampleName \"(.*)\" and \"(.*)\" and \"(.*)\"$")
    public void UpdateSuite4(String url, String method, String SampleName) throws Exception {
        status = utils.requestSuiteApi(url, method, SampleName, "b2f779e7-a4f2-44d8-a557-b3426ea520c14").getStatus();
    }

    @Given("^Create record using endpoint and method and SampleName \"(.*)\" and \"(.*)\" and \"(.*)\"$")
    public void Create1(String url, String method, String SampleName) throws Exception {
        status = utils.requestSuiteApi(url, method, Gettoken2(), SampleName, "GEMPYP_TEST_PROD_63700467-4D93-46AB-A46E-727B2E85DC3F").getStatus();
    }

    @Given("^Create record when s-id not exists using endpoint and method and SampleName \"(.*)\" and \"(.*)\" and \"(.*)\"$")
    public void Create2(String url, String method, String SampleName) throws Exception {
        status = utils.requestSuiteApi(url, method, Gettoken2(), SampleName, "b2f779e7-a4f2-44d8-a557-b3426ea520c1").getStatus();
    }

    @Given("^Create record when s-id not given using endpoint and method and SampleName \"(.*)\" and \"(.*)\" and \"(.*)\"$")
    public void Create3(String url, String method, String SampleName) throws Exception {
        status = utils.requestSuiteApi(url, method, Gettoken2(), SampleName, "b2f779e7-a4f2-44d8-a557-b3426ea520c1").getStatus();
    }

    @Given("^Create record when TC-id not given using endpoint and method and SampleName \"(.*)\" and \"(.*)\" and \"(.*)\"$")
    public void Create4(String url, String method, String SampleName) throws Exception {
        status = utils.requestSuiteApi(url, method, Gettoken2(), SampleName, "GEMPYP_TEST_PROD_63700467-4D93-46AB-A46E-727B2E85DC3F").getStatus();
    }

    @Given("^Create record with wrong Authentication using endpoint and method and SampleName \"(.*)\" and \"(.*)\" and \"(.*)\"$")
    public void Create5(String url, String method, String SampleName) throws Exception {
        status = utils.requestSuiteApi(url, method, SampleName, "b2f779e7-a4f2-44d8-a557-b3426ea520c1").getStatus();
    }

    @Given("^Create record when authentication not given using endpoint and method and SampleName \"(.*)\" and \"(.*)\" and \"(.*)\"$")
    public void Create6(String url, String method, String SampleName) throws Exception {
        status = utils.requestSuiteApi(url, method, SampleName, "b2f779e7-a4f2-44d8-a557-b3426ea520c1").getStatus();
    }

    @Given("^Update Suite type2 using endpoint and method and SampleName \"(.*)\" and \"(.*)\" and \"(.*)\"$")
    public void UpdateSuite5(String url, String method, String SampleName) throws Exception {
        status = utils.UpdateSuiteApi(url, method, SampleName).getStatus();
    }

    @Given("^Update Suite without s-runid using endpoint and method and SampleName \"(.*)\" and \"(.*)\" and \"(.*)\"$")
    public void UpdateSuite6(String url, String method, String SampleName) throws Exception {
        status = utils.requestSuiteApi(url, method, SampleName).getStatus();
    }

    @Given("^Update Suite without tc-id using endpoint and method and SampleName \"(.*)\" and \"(.*)\" and \"(.*)\"$")
    public void UpdateSuite7(String url, String method, String SampleName) throws Exception {
        status = utils.requestSuiteApi(url, method, SampleName).getStatus();
    }

}
