package com.qa.gemini.stepDefinition;

import com.gemini.generic.api.utils.ApiInvocationImpl;
import com.gemini.generic.api.utils.Response;
import com.qa.gemini.commonUtils.utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.util.HashMap;
import java.util.Map;

import static com.qa.gemini.commonUtils.utils.token;

public class stepDefination {

    int status;

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
}
