package com.qa.gemini.stepDefinition;

import com.gemini.generic.api.utils.ApiInvocation;
import com.gemini.generic.api.utils.ApiInvocationImpl;
import com.gemini.generic.api.utils.Request;
import com.gemini.generic.api.utils.Response;
import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.utils.ProjectConfigData;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.qa.gemini.commonUtils.utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.util.HashMap;
import java.util.Map;

import static com.qa.gemini.commonUtils.utils.token;

public class stepDefinition {
    int status;
    String bt;

    @Given("^Set endpoint and method \"(.*)\" and \"(.*)\"$")
    public void hit_the_api(String url, String method) throws Exception {
        status = utils.HitAPI(url, method).getStatus();
    }

    @Given("^Authenticate endpoint and method \"(.*)\" and \"(.*)\"$")
    public void Authenticate(String url, String method) throws Exception {
        status = utils.HitAPIAuthenticate(url, method).getStatus();
    }

    @Then("Verify Status code {int}")
    public void check_status_code(int Expected) {
        utils.VerifyStatusCode(Expected, status);
    }

    @Given("^Set token endpoint and method \"(.*)\" and \"(.*)\"$")
    public void hitApiToken(String url, String method) throws Exception {
        Map<String, String> headers;
        String j = token();
        headers = new HashMap<>();
        String jnew = j.replaceAll("^\"|\"$", "");
        headers.put("Authorization", "Bearer " + jnew);
        status = utils.HitAPIWithToken(url, method, headers).getStatus();
    }

    @Given("^Set Wrong token endpoint and method \"(.*)\" and \"(.*)\"$")
    public void hitApiTokenWrong(String url, String method) throws Exception {
        Map<String, String> headers;
        String j = token();
        headers = new HashMap<>();
        String jnew = j.replaceAll("^\"|\"$", "");
        headers.put("Authorization", "Bearer " + jnew + "op");
        Response res = utils.HitAPIWithWrongToken(url, method, headers);
        status = res.getStatus();
    }

    @Given("^Set Empty token endpoint and method \"(.*)\" and \"(.*)\"$")
    public void hitApiTokenEmpty(String url, String method) throws Exception {
        Map<String, String> headers;
        String j = token();
        headers = new HashMap<>();
        String jnew = j.replaceAll("^\"|\"$", "");
        headers.put("Authorization", "Bearer ");
        Response res = utils.HitAPIWithWrongToken(url, method, headers);
        status = res.getStatus();
    }

    @Given("^Health-Check \"(.*)\"$")
    public void healthCheck(String FilePath) throws Exception {
        ApiInvocationImpl.healthCheck(FilePath);
    }

    @Given("^Set endpoint and method and SampleName \"(.*)\" and \"(.*)\" and \"(.*)\"$")
    public void Login(String url, String method, String SampleName) throws Exception {
        status = utils.LoginUser(url, method, SampleName).getStatus();
    }

    @Given("^Set endpoint and method and wrong SampleName \"(.*)\" and \"(.*)\" and \"(.*)\"$")
    public void Loginwrong(String url, String method, String SampleName) throws Exception {
        status = utils.Login(url, method, SampleName).getStatus();
    }

    @Given("^Change token endpoint and method \"(.*)\" and \"(.*)\"$")
    public void hitApiToken2(String url, String method) throws Exception {
        Map<String, String> headers;
        String j = token();
        headers = new HashMap<>();
        String jnew = j.replaceAll("^\"|\"$", "");
        headers.put("Authorization", "Bearer " + jnew);
        Response res = utils.HitAPIWithToken(url, method, headers);
        status = res.getStatus();
        String ress = res.getResponseBody();
        JsonParser parser = new JsonParser();
        JsonObject resss = (JsonObject) parser.parse(ress);
        JsonObject data = resss.get("data").getAsJsonObject();
        String too = data.get("bridgeToken").getAsString();
        bt = too;
    }

    @Given("^Change token without headers endpoint and method \"(.*)\" and \"(.*)\"$")
    public void hitApiToken3(String url, String method) throws Exception {
        Response res = utils.HitAPIWithoutHeadersToken(url, method);
        status = res.getStatus();
    }

    @Given("^Change token with wrong Authentication endpoint and method \"(.*)\" and \"(.*)\"$")
    public void hitApiToken4(String url, String method) throws Exception {
        Map<String, String> headers;
        String j = token();
        headers = new HashMap<>();
        String jnew = j.replaceAll("^\"|\"$", "");
        headers.put("Authorization", "Bearer " + jnew + "pawan");
        Response res = utils.HitAPIWithWrongToken(url, method, headers);
        status = res.getStatus();
    }

    @Given("^Set Suite-API endpoint and method and SampleName \"(.*)\" and \"(.*)\" and \"(.*)\"$")
    public void Suite_Api(String url, String method, String SampleName) throws Exception {
        status = utils.CheckAPi(url, method, SampleName).getStatus();
    }

    @Given("^Set Suite-API using Wrong Authentication endpoint and method and SampleName \"(.*)\" and \"(.*)\" and \"(.*)\"$")
    public void Suite_Api2(String url, String method, String SampleName) throws Exception {
        status = utils.CheckAPiWrongAuth(url, method, SampleName).getStatus();
    }

    @Given("^Set Suite-API when headers not given endpoint and method and SampleName \"(.*)\" and \"(.*)\" and \"(.*)\"$")
    public void Suite_Api3(String url, String method, String SampleName) throws Exception {
        status = utils.CheckAPiWithoutHeaders(url, method, SampleName).getStatus();
    }

    @Given("^Update Suite using endpoint and method and SampleName \"(.*)\" and \"(.*)\" and \"(.*)\"$")
    public void UpdateSuite(String url, String method, String SampleName) throws Exception {
        status = utils.UpdateSuiteApi(url, method, SampleName,"GEMPYP_TEST_PROD_63700467-4D93-46AB-A46E-727B2E85DC3F").getStatus();
    }
    @Given("^Update Suite when S-run-id not present using endpoint and method and SampleName \"(.*)\" and \"(.*)\" and \"(.*)\"$")
    public void UpdateSuite2(String url, String method, String SampleName) throws Exception {
        status = utils.UpdateSuiteApi(url, method, SampleName,"b2f779e7-a4f2-44d8-a557-b3426ea520c14").getStatus();
    }
    @Given("^Update Suite using wrong Authentication using endpoint and method and SampleName \"(.*)\" and \"(.*)\" and \"(.*)\"$")
    public void UpdateSuite3(String url, String method, String SampleName) throws Exception {
        status = utils.UpdateSuiteApi1(url, method, SampleName,"b2f779e7-a4f2-44d8-a557-b3426ea520c14").getStatus();
    }
    @Given("^Update Suite without Authentication using endpoint and method and SampleName \"(.*)\" and \"(.*)\" and \"(.*)\"$")
    public void UpdateSuite4(String url, String method, String SampleName) throws Exception {
        status = utils.UpdateSuiteApi2(url, method, SampleName,"b2f779e7-a4f2-44d8-a557-b3426ea520c14").getStatus();
    }

    @Given("^Create record using endpoint and method and SampleName \"(.*)\" and \"(.*)\" and \"(.*)\"$")
    public void Create1(String url, String method, String SampleName) throws Exception {
        status = utils.Create(url, method, SampleName,"GEMPYP_TEST_PROD_63700467-4D93-46AB-A46E-727B2E85DC3F").getStatus();
    }

    @Given("^Create record when s-id not exists using endpoint and method and SampleName \"(.*)\" and \"(.*)\" and \"(.*)\"$")
    public void Create2(String url, String method, String SampleName) throws Exception {
        status = utils.Create(url, method, SampleName,"b2f779e7-a4f2-44d8-a557-b3426ea520c1").getStatus();
    }

    @Given("^Create record when s-id not given using endpoint and method and SampleName \"(.*)\" and \"(.*)\" and \"(.*)\"$")
    public void Create3(String url, String method, String SampleName) throws Exception {
        status = utils.Create(url, method, SampleName,"b2f779e7-a4f2-44d8-a557-b3426ea520c1").getStatus();
    }

    @Given("^Create record when TC-id not given using endpoint and method and SampleName \"(.*)\" and \"(.*)\" and \"(.*)\"$")
    public void Create4(String url, String method, String SampleName) throws Exception {
        status = utils.Create(url, method, SampleName,"GEMPYP_TEST_PROD_63700467-4D93-46AB-A46E-727B2E85DC3F").getStatus();
    }

    @Given("^Create record with wrong Authentication using endpoint and method and SampleName \"(.*)\" and \"(.*)\" and \"(.*)\"$")
    public void Create5(String url, String method, String SampleName) throws Exception {
        status = utils.UpdateSuiteApi2(url, method, SampleName,"b2f779e7-a4f2-44d8-a557-b3426ea520c1").getStatus();
    }

    @Given("^Create record when authentication not given using endpoint and method and SampleName \"(.*)\" and \"(.*)\" and \"(.*)\"$")
    public void Create6(String url, String method, String SampleName) throws Exception {
              status = utils.UpdateSuiteApi2(url, method, SampleName,"b2f779e7-a4f2-44d8-a557-b3426ea520c1").getStatus();
    }

    @Given("^Update Suite type2 using endpoint and method and SampleName \"(.*)\" and \"(.*)\" and \"(.*)\"$")
    public void UpdateSuite5(String url, String method, String SampleName) throws Exception {
        status = utils.UpdateSuiteApi5(url, method, SampleName,"").getStatus();
    }

    @Given("^Update Suite without s-runid using endpoint and method and SampleName \"(.*)\" and \"(.*)\" and \"(.*)\"$")
    public void UpdateSuite6(String url, String method, String SampleName) throws Exception {
        status = utils.UpdateSuiteWithoutS_id(url, method, SampleName).getStatus();
    }

    @Given("^Update Suite without tc-id using endpoint and method and SampleName \"(.*)\" and \"(.*)\" and \"(.*)\"$")
    public void UpdateSuite7(String url, String method, String SampleName) throws Exception {
        status = utils.UpdateSuiteWithoutS_id(url, method, SampleName).getStatus();
    }

}
