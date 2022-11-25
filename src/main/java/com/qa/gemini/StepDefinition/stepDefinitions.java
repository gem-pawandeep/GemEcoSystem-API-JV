package com.qa.gemini.StepDefinition;

import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.RestAssured;
import com.gemini.generic.utils.ProjectConfigData;
import com.qa.gemini.CommonUtils.utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static com.qa.gemini.CommonUtils.utils.*;

public class stepDefinitions {
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
        status = utils.CheckAPiWithAuth(url, method, utils.Gettoken2(), SampleName).getStatus();
    }

    @Given("^Set Suite-API using Wrong Authentication endpoint and method and SampleName \"(.*)\" and \"(.*)\" and \"(.*)\"$")
    public void Suite_Api2(String url, String method, String SampleName) throws Exception {
        status = utils.CheckAPiWithAuth(url, method, utils.Gettoken2() + "pawan", SampleName).getStatus();
    }

    @Given("^Set Suite-API when headers not given endpoint and method and SampleName \"(.*)\" and \"(.*)\" and \"(.*)\"$")
    public void Suite_Api3(String url, String method, String SampleName) throws Exception {
        status = utils.CheckAPiWithAuth(url, method, SampleName).getStatus();
    }

    @Given("^Update Suite using endpoint and method and SampleName \"(.*)\" and \"(.*)\" and \"(.*)\"$")
    public void UpdateSuite(String url, String method, String SampleName) throws Exception {
        status = utils.requestSuiteApi(url, method, utils.Gettoken2(), SampleName, "GEMPYP_TEST_PROD_63700467-4D93-46AB-A46E-727B2E85DC3F").getStatus();
    }

    @Given("^Update Suite when S-run-id not present using endpoint and method and SampleName \"(.*)\" and \"(.*)\" and \"(.*)\"$")
    public void UpdateSuite2(String url, String method, String SampleName) throws Exception {
        status = utils.requestSuiteApi(url, method, utils.Gettoken2(), SampleName, "b2f779e7-a4f2-44d8-a557-b3426ea520c14").getStatus();
    }

    @Given("^Update Suite using wrong Authentication using endpoint and method and SampleName \"(.*)\" and \"(.*)\" and \"(.*)\"$")
    public void UpdateSuite3(String url, String method, String SampleName) throws Exception {
        status = utils.requestSuiteApi(url, method, utils.Gettoken2() + "pawan", SampleName, "b2f779e7-a4f2-44d8-a557-b3426ea520c14").getStatus();
    }

    @Given("^Update Suite without Authentication using endpoint and method and SampleName \"(.*)\" and \"(.*)\" and \"(.*)\"$")
    public void UpdateSuite4(String url, String method, String SampleName) throws Exception {
        status = utils.requestSuiteApi(url, method, SampleName, "b2f779e7-a4f2-44d8-a557-b3426ea520c14").getStatus();
    }

    @Given("^Create record using endpoint and method and SampleName \"(.*)\" and \"(.*)\" and \"(.*)\"$")
    public void Create1(String url, String method, String SampleName) throws Exception {
        status = utils.requestSuiteApi(url, method, utils.Gettoken2(), SampleName, "GEMPYP_TEST_PROD_63700467-4D93-46AB-A46E-727B2E85DC3F").getStatus();
    }

    @Given("^Create record when s-id not exists using endpoint and method and SampleName \"(.*)\" and \"(.*)\" and \"(.*)\"$")
    public void Create2(String url, String method, String SampleName) throws Exception {
        status = utils.requestSuiteApi(url, method, utils.Gettoken2(), SampleName, "b2f779e7-a4f2-44d8-a557-b3426ea520c1").getStatus();
    }

    @Given("^Create record when s-id not given using endpoint and method and SampleName \"(.*)\" and \"(.*)\" and \"(.*)\"$")
    public void Create3(String url, String method, String SampleName) throws Exception {
        status = utils.requestSuiteApi(url, method, utils.Gettoken2(), SampleName, "b2f779e7-a4f2-44d8-a557-b3426ea520c1").getStatus();
    }

    @Given("^Create record when TC-id not given using endpoint and method and SampleName \"(.*)\" and \"(.*)\" and \"(.*)\"$")
    public void Create4(String url, String method, String SampleName) throws Exception {
        status = utils.requestSuiteApi(url, method, utils.Gettoken2(), SampleName, "GEMPYP_TEST_PROD_63700467-4D93-46AB-A46E-727B2E85DC3F").getStatus();
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

    @Given("^Set endpoint \"(.*)\"$")
    public void fileUpload(String url) throws Exception {
        status = utils.FileUpload(url, "C:/Users/Pawan.Deep/Desktop/command.txt", ProjectConfigData.getProperty("username"), utils.Gettoken2());
    }

    @Given("^Set endpoint with incorrect bridgetoken \"(.*)\"$")
    public void fileUpload2(String url) throws Exception {
        status = utils.FileUpload(url, "C:/Users/Pawan.Deep/Desktop/command.txt", ProjectConfigData.getProperty("username"), utils.Gettoken2() + "pawan");
    }

    @Given("^Set endpoint without username \"(.*)\"$")
    public void fileUpload3(String url) throws Exception {
        String j = token();
        assert j != null;
        String jnew = j.replaceAll("^\"|\"$", "");
        status = utils.FileUpload2(url, "C:/Users/Pawan.Deep/Desktop/command.txt", jnew);
    }

    @Given("^Set endpoint without username in Bearer Token \"(.*)\"$")
    public void fileUpload5(String url) throws Exception {
        String jnew = "";
        status = utils.FileUpload2(url, "C:/Users/Pawan.Deep/Desktop/command.txt", jnew);
    }

    @Given("^Set endpoint with username not present in db \"(.*)\"$")
    public void fileUpload4(String url) throws Exception {
        status = utils.FileUpload(url, "C:/Users/Pawan.Deep/Desktop/command.txt", "", utils.Gettoken2());
    }

    @Given("^Set endpoint with text \"(.*)\"$")
    public void fileUpload6(String url) throws Exception {
        status = utils.TextUpload(url, "Hello World", ProjectConfigData.getProperty("username"), utils.Gettoken2()).getStatus();
    }

    @Given("^Set endpoint with text wrong tag \"(.*)\"$")
    public void fileUpload7(String url) throws Exception {
        status = utils.TextUpload2(url, "Hello World", ProjectConfigData.getProperty("username"), utils.Gettoken2()).getStatus();
    }

    @Given("^Set endpoint with text with wrong BridgeToken \"(.*)\"$")
    public void fileUpload8(String url) throws Exception {
        status = utils.TextUpload2(url, "Hello World", ProjectConfigData.getProperty("username"), utils.Gettoken2() + "hello").getStatus();
    }

    @Given("^Set endpoint with text when BT is not present \"(.*)\"$")
    public void fileUpload9(String url) throws Exception {
        status = utils.TextUpload3(url, "Hello World", "").getStatus();
    }

    @Given("^Set endpoint with text without valid username \"(.*)\"$")
    public void fileUploads(String url) throws Exception {
        status = utils.TextUpload2(url, "Hello World", "abcdefghijk", utils.Gettoken2()).getStatus();
    }

    @Given("^Set endpoint with text without write access \"(.*)\"$")
    public void fileUploads2(String url) throws Exception {
        status = utils.TextUpload2(url, "Hello World", ProjectConfigData.getProperty("username"), utils.Gettoken2()).getStatus();
    }

    @Given("^Post Suite-API endpoint and method and SampleName and step \"(.*)\" and \"(.*)\" and \"(.*)\" and \"(.*)\"$")
    public void Suite_Api1(String url, String method, String SampleName, String stepname) throws Exception {
        Map<String, String> headers = new HashMap<>();
        String username = ProjectConfigData.getProperty("username");
        headers.put("username", username);
        headers.put("bridgeToken", Gettoken2());
        status = utils.Access(url, method, SampleName, headers, stepname).getStatus();
    }

    @Given("^Post Suite-API endpoint and method and SampleName \"(.*)\" and \"(.*)\" and \"(.*)\"$")
    public void Suite_Api12(String url, String method, String SampleName) throws Exception {
        Map<String, String> headers = new HashMap<>();
        String username = ProjectConfigData.getProperty("username");
        headers.put("username", username);
        headers.put("bridgeToken", Gettoken2());
        status = utils.Access(url, method, SampleName, headers).getStatus();
    }

    @Given("^Set Post API endpoint and method and SampleName \"(.*)\" and \"(.*)\" and \"(.*)\"$")
    public void Suite_Api13(String url, String method, String SampleName) throws Exception {
        Map<String, String> headers = new HashMap<>();
        String username = ProjectConfigData.getProperty("username");
        headers.put("username", username);
        headers.put("bridgeToken", Gettoken2() + "pawan");
        status = utils.Access(url, method, SampleName, headers).getStatus();
    }

    @Given("^Set Post API without BT endpoint and method and SampleName \"(.*)\" and \"(.*)\" and \"(.*)\"$")
    public void Suite_Api14(String url, String method, String SampleName) throws Exception {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + "");
        status = utils.Access(url, method, SampleName, headers).getStatus();
    }

    @Given("^Set endpoint and method without bearer token \"(.*)\" and \"(.*)\"$")
    public void Suite_Api14(String url, String method) throws Exception {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + "");
        status = utils.Access(url, method, headers).getStatus();
    }

    @Given("^Setting token endpoint and method \"(.*)\" and \"(.*)\"$")
    public void hitApiTokenWrong1(String url, String method) throws Exception {
        Map<String, String> headers = new HashMap<>();
        String j = token();
        assert j != null;
        String jnew = j.replaceAll("^\"|\"$", "");
        headers.put("Authorization", "Bearer " + jnew);
        status = utils.HitAPIWithToken(url, method, headers).getStatus();
    }

    @Given("^Get file by setting Authentication, endpoint and method \"(.*)\" and \"(.*)\"$")
    public void getFile(String url, String method) throws Exception {
        try {
            Map<String, String> headers = new HashMap<>();
            String j = token();
            assert j != null;
            String jnew = j.replaceAll("^\"|\"$", "");
            headers.put("Authorization", "Bearer " + jnew);
            Map<String, String> parameters = new HashMap<>();
            parameters.put("id", "gem-np:pawan:command.txt");
            RestAssured.baseURI = ProjectConfigData.getProperty(url);
            GemTestReporter.addTestStep("Request URL", ProjectConfigData.getProperty(url), STATUS.INFO);
          //  status = RestAssured.given().params(parameters).headers(headers).when().get().getStatusCode();
            Response response=RestAssured.given().params(parameters).headers(headers).when().get();
            status=response.statusCode();
            String s=response.body().print();
            JsonObject js = (JsonObject) JsonParser.parseString(s);
            GemTestReporter.addTestStep("Response Body",s,STATUS.INFO);
            GemTestReporter.addTestStep("Message",js.get("message").getAsString(),STATUS.INFO);
            GemTestReporter.addTestStep("Operation",js.get("operation").getAsString(),STATUS.INFO);
            GemTestReporter.addTestStep("Request Verification", "Request executed successfully", STATUS.PASS);
        }catch (Exception e){
            GemTestReporter.addTestStep("Request Verification", "Request not executed", STATUS.FAIL);
        }
    }

    @Given("^Get file by setting endpoint and method \"(.*)\" and \"(.*)\"$")
    public void getFile2(String url, String method) throws Exception {
        try {
            Map<String, String> headers = new HashMap<>();
            headers.put("Authorization", "Bearer " + "");
            Map<String, String> parameters = new HashMap<>();
            parameters.put("id", "gem-np:pawan:command.txt");
            RestAssured.baseURI = ProjectConfigData.getProperty(url);
            GemTestReporter.addTestStep("Request URL", ProjectConfigData.getProperty(url), STATUS.INFO);
            status=RestAssured.given().params(parameters).when().get().statusCode();
            GemTestReporter.addTestStep("Request Verification", "Request executed successfully", STATUS.PASS);
        }catch (Exception e){
            GemTestReporter.addTestStep("Request Verification", "Request not executed", STATUS.FAIL);
        }
    }

    @Given("^Get file by setting Authentication: endpoint and method \"(.*)\" and \"(.*)\"$")
    public void getFile3(String url, String method) throws Exception {
        try {
            Map<String, String> headers = new HashMap<>();
            String j = token();
            assert j != null;
            String jnew = j.replaceAll("^\"|\"$", "");
            headers.put("Authorization", "Bearer " + jnew);
            Map<String, String> parameters = new HashMap<>();
            parameters.put("id", "gem-np:pawan:command.txt");
            RestAssured.baseURI = ProjectConfigData.getProperty(url);
            GemTestReporter.addTestStep("Request URL", ProjectConfigData.getProperty(url), STATUS.INFO);
            status=RestAssured.given().params(parameters).headers(headers).when().get().statusCode();
            GemTestReporter.addTestStep("Request Verification", "Request executed successfully", STATUS.PASS);
        }catch (Exception e){
            GemTestReporter.addTestStep("Request Verification", "Request not executed", STATUS.FAIL);
        }
    }


}
