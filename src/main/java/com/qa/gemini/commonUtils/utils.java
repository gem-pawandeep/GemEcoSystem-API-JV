package com.qa.gemini.commonUtils;

import com.gemini.generic.api.utils.*;
import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.utils.GemJarGlobalVar;
import com.gemini.generic.utils.ProjectConfigData;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.qa.gemini.stepDefinition.stepDefinition;

import java.util.HashMap;
import java.util.Map;

public class utils {
    public static Response HitAPI(String UrlNameFromConfig, String method) throws Exception {
        Request request = new Request();
        String url = ProjectConfigData.getProperty(UrlNameFromConfig);
        request.setURL(url);
        request.setMethod(method);
        request.setStep("Test for " + method.toUpperCase() + " API");
        Response response = ApiInvocation.handleRequest(request);
        return response;
    }

    public static Response HitAPIAuthenticate(String UrlNameFromConfig, String method) throws Exception {
        Request request = new Request();
        String url = ProjectConfigData.getProperty(UrlNameFromConfig);
        GemTestReporter.addTestStep("Url of the test case", url, STATUS.INFO);
        request.setURL(url);
        request.setMethod(method);
        Response response = ApiInvocation.handleRequest(request);
        return response;
    }

    public static void VerifyStatusCode(int expected, int actual) {
        if (expected == actual) {
            GemTestReporter.addTestStep("Status Verification", "Expected Status :" + expected + ",<br>Actual :" + actual, STATUS.PASS);
        } else {
            GemTestReporter.addTestStep("Status Verification", "Expected Status :" + expected + ",<br>Actual :" + actual, STATUS.FAIL);
        }
    }

    public static String token() {
        try {
            String Token;
            String urlss = ProjectConfigData.getProperty("Login");
            String payload = ProjectSampleJson.getSampleDataString("Login_sampleJson");
            Request request = new Request();
            request.setURL(urlss);
            request.setMethod("Post");
            request.setRequestPayload(payload);
            Response response = ApiInvocation.handleRequest(request);
            String Bodd = response.getResponseBody();
            JsonParser parser = new JsonParser();
            JsonObject Boddy = (JsonObject) parser.parse(Bodd);
            JsonObject to = (JsonObject) Boddy.get("data");
            String tokenss = String.valueOf(to.get("token"));
            Token = tokenss;
            return Token;
        } catch (Exception e) {
            GemTestReporter.addTestStep("Final token", "Some error occured while fetching token", STATUS.FAIL);
            e.printStackTrace();
            return null;
        }
    }

    public static Response HitAPIWithToken(String UrlNameFromConfig, String method, Map<String, String> headers) throws Exception {
        Request request = new Request();
        String url = ProjectConfigData.getProperty(UrlNameFromConfig);
        GemTestReporter.addTestStep("Url for "+ method.toUpperCase() + " Request", url, STATUS.INFO);
        request.setURL(url);
        request.setMethod(method);
        request.setHeaderMap(headers);
        request.setStep("Token Authentication");
        Response response = ApiInvocation.handleRequest(request);
        try {
            GemTestReporter.addTestStep(method.toUpperCase() + " Request Verification ",  method.toUpperCase() + " Request Executed Successfully", STATUS.PASS);
        } catch (Exception e) {
            GemTestReporter.addTestStep(method.toUpperCase() + " Request Verification ", method.toUpperCase() + " Request Did not Executed Successfully", STATUS.FAIL);
        }
        return response;
    }

    public static Response HitAPIWithWrongToken(String UrlNameFromConfig, String method, Map<String, String> headers) throws Exception {
        Request request = new Request();
        String url = ProjectConfigData.getProperty(UrlNameFromConfig);
        GemTestReporter.addTestStep("Url for "+method.toUpperCase() + " Request", url, STATUS.INFO);
        request.setURL(url);
        request.setMethod(method);
        request.setHeaderMap(headers);
        Response response = ApiInvocation.handleRequest(request);
        try {
            GemTestReporter.addTestStep(method.toUpperCase() + " Request Verification ", method.toUpperCase() + " Request Executed Successfully", STATUS.PASS);
        } catch (Exception e) {
            GemTestReporter.addTestStep(method.toUpperCase() + " Request Verification ", method.toUpperCase() + " Request Did not Executed Successfully", STATUS.FAIL);
        }
        return response;
    }

    public static Response LoginUser(String UrlNameFromConfig, String method, String sampleName) throws Exception {
        Request request = new Request();
        String url = ProjectConfigData.getProperty(UrlNameFromConfig);
        GemTestReporter.addTestStep("Url for " + method.toUpperCase() + " Request", url, STATUS.INFO);
        request.setURL(url);
        request.setMethod(method);
        request.setStep("Login user");
        String payload = ProjectSampleJson.getSampleDataString(sampleName);
        request.setRequestPayload(payload);
        Response response = ApiInvocation.handleRequest(request);
        return response;
    }

    public static Response Login(String UrlNameFromConfig, String method, String sampleName) throws Exception {
        String url = ProjectConfigData.getProperty(UrlNameFromConfig);
        GemTestReporter.addTestStep("Url for " + method.toUpperCase() + " Request", url, STATUS.INFO);
        String payload = ProjectSampleJson.getSampleDataString(sampleName);
        Request request = new Request();
        request.setURL(url);
        request.setMethod(method);
        request.setRequestPayload(payload);
        Response response = ApiInvocation.handleRequest(request);
        return response;
    }

    public static Response HitAPIWithoutHeadersToken(String UrlNameFromConfig, String method) throws Exception {
        Request request = new Request();
        String url = ProjectConfigData.getProperty(UrlNameFromConfig);
        GemTestReporter.addTestStep("Url for "+method.toUpperCase() + " Request", url, STATUS.INFO);
        request.setURL(url);
        request.setMethod(method);
        Response response = ApiInvocation.handleRequest(request);
        try {
            GemTestReporter.addTestStep(method.toUpperCase() + " Request Verification ", method.toUpperCase() + " Request Executed Successfully", STATUS.PASS);
        } catch (Exception e) {
            GemTestReporter.addTestStep(method.toUpperCase() + " Request Verification ", method.toUpperCase() + " Request Did not Executed Successfully", STATUS.FAIL);
        }
        return response;
    }

    public static String Gettoken2() throws Exception {
        String url = ProjectConfigData.getProperty("Gettoken");
        String too = null;
        String j = token();
        Response res = null;
        Map<String, String> headers = new HashMap<>();
        String jnew = j.replaceAll("^\"|\"$", "");
        headers.put("Authorization", "Bearer " + jnew);
        try {
            Request request = new Request();
            request.setURL(url);
            request.setMethod("get");
            request.setHeaderMap(headers);
            Response response = ApiInvocation.handleRequest(request);
            res = response;
        } catch (Exception e) {
            GemTestReporter.addTestStep(" Get Request Verification ", "Get Request Did not Executed Successfully", STATUS.FAIL);
        }
        int status = res.getStatus();
        if (status == 200) {
            String bodi = res.getResponseBody();
            JsonParser parser = new JsonParser();
            JsonObject body = (JsonObject) parser.parse(bodi);
            JsonObject data = body.get("data").getAsJsonObject();
            too = data.get("bridgeToken").getAsString();
        }
        return too;
    }

    public static Response CheckAPi(String UrlNameFromConfig, String method, String sampleName) throws Exception {
        String bt = Gettoken2();
        GemTestReporter.addTestStep("Test Case", "Test to insert the suite using "+method.toUpperCase() + " API  ", STATUS.INFO);
        String url = ProjectConfigData.getProperty(UrlNameFromConfig);
        GemTestReporter.addTestStep("Url for "+method.toUpperCase() + " Request", url, STATUS.INFO);
        String payloads = ProjectSampleJson.getSampleDataString(sampleName);
        JsonParser parser = new JsonParser();
        JsonObject pay = (JsonObject) parser.parse(payloads);
        String payload = String.valueOf(ApiHealthCheckUtils.result(pay));
        GemTestReporter.addTestStep("Payload ", String.valueOf(payload), STATUS.INFO);
        String username = ProjectConfigData.getProperty("username");
        Map<String, String> headers = new HashMap<>();
        headers.put("username", username);
        headers.put("bridgeToken", bt);
        Response response = new Response();
        try {
            Request request = new Request();
            request.setURL(url);
            request.setMethod(method);
            request.setHeaderMap(headers);
            request.setRequestPayload(payload);
            response = ApiInvocation.handleRequest(request);
            GemTestReporter.addTestStep(method.toUpperCase() + " Request Verification ", method.toUpperCase() + " Request Executed Successfully", STATUS.PASS);
        } catch (Exception e) {
            GemTestReporter.addTestStep(method.toUpperCase() + " Request Verification ", method.toUpperCase() + " Request Did not Executed Successfully", STATUS.FAIL);
        }
        return response;
    }

    public static Response CheckAPiWrongAuth(String UrlNameFromConfig, String method, String sampleName) throws Exception {
        String bt = Gettoken2();
        GemTestReporter.addTestStep("Test Case", "Test to insert the suite using Post API  ", STATUS.INFO);
        String url = ProjectConfigData.getProperty(UrlNameFromConfig);
        GemTestReporter.addTestStep("Url for "+method.toUpperCase() + " Request", url, STATUS.INFO);
        String payloads = ProjectSampleJson.getSampleDataString(sampleName);
        JsonParser parser = new JsonParser();
        JsonObject pay = (JsonObject) parser.parse(payloads);
        String payload = String.valueOf(ApiHealthCheckUtils.result(pay));
        GemTestReporter.addTestStep("Payload ", String.valueOf(payload), STATUS.INFO);
        String username = ProjectConfigData.getProperty("username");
        Map<String, String> headers = new HashMap<>();
        headers.put("username", username);
        headers.put("bridgeToken", bt + "pawan");
        Response response = new Response();
        try {
            Request request = new Request();
            request.setURL(url);
            request.setMethod(method);
            request.setHeaderMap(headers);
            request.setRequestPayload(payload);
            response = ApiInvocation.handleRequest(request);
            GemTestReporter.addTestStep(method.toUpperCase() + " Request Verification ", method.toUpperCase() + " Request Executed Successfully", STATUS.PASS);
        } catch (Exception e) {
            GemTestReporter.addTestStep(method.toUpperCase() + " Request Verification ", method.toUpperCase() + " Request Did not Executed Successfully", STATUS.FAIL);
        }
        return response;
    }

    public static Response CheckAPiWithoutHeaders(String UrlNameFromConfig, String method, String sampleName) throws Exception {
        Gettoken2();
        GemTestReporter.addTestStep("Test Case", "Test to insert the suite using Post API  ", STATUS.INFO);
        String url = ProjectConfigData.getProperty(UrlNameFromConfig);
        GemTestReporter.addTestStep("Url for "+method.toUpperCase() + " Request", url, STATUS.INFO);
        String payloads = ProjectSampleJson.getSampleDataString(sampleName);
        JsonParser parser = new JsonParser();
        JsonObject pay = (JsonObject) parser.parse(payloads);
        String payload = String.valueOf(ApiHealthCheckUtils.result(pay));
        GemTestReporter.addTestStep("Payload ", String.valueOf(payload), STATUS.INFO);
        Response response = new Response();
        try {
            Request request = new Request();
            request.setURL(url);
            request.setMethod(method);
            request.setRequestPayload(payload);
            response = ApiInvocation.handleRequest(request);
            GemTestReporter.addTestStep(method.toUpperCase() + " Request Verification ", method.toUpperCase() + " Request Executed Successfully", STATUS.PASS);
        } catch (Exception e) {
            GemTestReporter.addTestStep(method.toUpperCase() + " Request Verification ", method.toUpperCase() + " Request Did not Executed Successfully", STATUS.FAIL);
        }
        return response;
    }

    public static Response UpdateSuiteApi(String UrlNameFromConfig, String method, String sampleName,String srunid) throws Exception {
        String bt=Gettoken2();
        GemTestReporter.addTestStep("Test Case", "Test to Update the suite using Put API", STATUS.INFO);
        String url = ProjectConfigData.getProperty(UrlNameFromConfig);
        GemTestReporter.addTestStep("Url for "+method.toUpperCase() + " Request", url, STATUS.INFO);
        String payloads = ProjectSampleJson.getSampleDataString(sampleName);
        JsonParser parser = new JsonParser();
        JsonObject pay = (JsonObject) parser.parse(payloads);
        if (GemJarGlobalVar.environment.equalsIgnoreCase("prod")) {
            pay.addProperty("s_run_id", srunid);
        }
        String payload = String.valueOf(ApiHealthCheckUtils.result(pay));
        GemTestReporter.addTestStep("Payload ", String.valueOf(payload), STATUS.INFO);
        String username = ProjectConfigData.getProperty("username");
        Map<String, String> headers = new HashMap<>();
        headers.put("username", username);
        headers.put("bridgeToken", bt);
        Response response=new Response();
        try {
            Request request = new Request();
            request.setURL(url);
            request.setMethod(method);
            request.setHeaderMap(headers);
            request.setRequestPayload(payload);
            response = ApiInvocation.handleRequest(request);
            GemTestReporter.addTestStep(method.toUpperCase() + " Request Verification ", method.toUpperCase() + " Request Executed Successfully", STATUS.PASS);
        } catch (Exception e) {
            GemTestReporter.addTestStep(method.toUpperCase() + " Request Verification ", method.toUpperCase() + " Request Did not Executed Successfully", STATUS.FAIL);
        }
        return response;
    }

    public static Response UpdateSuiteApi1(String UrlNameFromConfig, String method, String sampleName,String srunid) throws Exception {
        String bt=Gettoken2();
        GemTestReporter.addTestStep("Test Case", "Test to Update the suite using Put API", STATUS.INFO);
        String url = ProjectConfigData.getProperty(UrlNameFromConfig);
        GemTestReporter.addTestStep("Url for "+method.toUpperCase() + " Request", url, STATUS.INFO);
        String payloads = ProjectSampleJson.getSampleDataString(sampleName);
        JsonParser parser = new JsonParser();
        JsonObject pay = (JsonObject) parser.parse(payloads);
        if (GemJarGlobalVar.environment.equalsIgnoreCase("prod")) {
            pay.addProperty("s_run_id", srunid);
        }
        String payload = String.valueOf(ApiHealthCheckUtils.result(pay));
        GemTestReporter.addTestStep("Payload ", String.valueOf(payload), STATUS.INFO);
        String username = ProjectConfigData.getProperty("username");
        Map<String, String> headers = new HashMap<>();
        headers.put("username", username);
        headers.put("bridgeToken", bt+"Pawan");
        Response response=new Response();
        try {
            Request request = new Request();
            request.setURL(url);
            request.setMethod(method);
            request.setHeaderMap(headers);
            request.setRequestPayload(payload);
            response = ApiInvocation.handleRequest(request);
            GemTestReporter.addTestStep(method.toUpperCase() + " Request Verification ", method.toUpperCase() + " Request Executed Successfully", STATUS.PASS);
        } catch (Exception e) {
            GemTestReporter.addTestStep(method.toUpperCase() + " Request Verification ", method.toUpperCase() + " Request Did not Executed Successfully", STATUS.FAIL);
        }
        return response;
    }

    public static Response UpdateSuiteApi2(String UrlNameFromConfig, String method, String sampleName,String srunid) throws Exception {
        Gettoken2();
        GemTestReporter.addTestStep("Test Case", "Test to Update the suite using Put API", STATUS.INFO);
        String url = ProjectConfigData.getProperty(UrlNameFromConfig);
        GemTestReporter.addTestStep("Url for "+method.toUpperCase() + " Request", url, STATUS.INFO);
        String payloads = ProjectSampleJson.getSampleDataString(sampleName);
        JsonParser parser = new JsonParser();
        JsonObject pay = (JsonObject) parser.parse(payloads);
        if (GemJarGlobalVar.environment.equalsIgnoreCase("prod")) {
            pay.addProperty("s_run_id", srunid);
        }
        String payload = String.valueOf(ApiHealthCheckUtils.result(pay));
        GemTestReporter.addTestStep("Payload ", String.valueOf(payload), STATUS.INFO);
        Response response=new Response();
        try {
            Request request = new Request();
            request.setURL(url);
            request.setMethod(method);
            request.setRequestPayload(payload);
            response = ApiInvocation.handleRequest(request);
            GemTestReporter.addTestStep(method.toUpperCase() + " Request Verification ", method.toUpperCase() + " Request Executed Successfully", STATUS.PASS);
        } catch (Exception e) {
            GemTestReporter.addTestStep(method.toUpperCase() + " Request Verification ", method.toUpperCase() + " Request Did not Executed Successfully", STATUS.FAIL);
        }
        return response;
    }

    public static Response Create(String UrlNameFromConfig, String method, String sampleName,String srunid) throws Exception {
        String bt=Gettoken2();
        Response response=new Response();
        GemTestReporter.addTestStep("Test Case", "To create a new testcase in the database using Post function  ", STATUS.INFO);
        String url = ProjectConfigData.getProperty(UrlNameFromConfig);
        GemTestReporter.addTestStep("Url for "+method.toUpperCase() + " Request", url, STATUS.INFO);
        String payloads = ProjectSampleJson.getSampleDataString(sampleName);
        JsonParser parser = new JsonParser();
        JsonObject pay = (JsonObject) parser.parse(payloads);
        if (GemJarGlobalVar.environment.equalsIgnoreCase("prod")) {
            pay.addProperty("s_run_id", srunid);
        }
        String payload = String.valueOf(ApiHealthCheckUtils.result(pay));
        GemTestReporter.addTestStep("Payload ", String.valueOf(payload), STATUS.INFO);
        String username = ProjectConfigData.getProperty("username");
        Map<String, String> headers = new HashMap<>();
        headers.put("username", username);
        headers.put("bridgeToken", bt);
        GemTestReporter.addTestStep("Headers", String.valueOf(headers), STATUS.INFO);
        try {
            Request request = new Request();
            request.setURL(url);
            request.setMethod(method);
            request.setHeaderMap(headers);
            request.setRequestPayload(payload);
            response = ApiInvocation.handleRequest(request);
            GemTestReporter.addTestStep(method.toUpperCase() + " Request Verification ", method.toUpperCase() + " Request Executed Successfully", STATUS.PASS);
        } catch (Exception e) {
            GemTestReporter.addTestStep(method.toUpperCase() + " Request Verification ", method.toUpperCase() + " Request Did not Executed Successfully", STATUS.FAIL);
        }
        return response;
    }

    public static Response UpdateSuiteApi5(String UrlNameFromConfig, String method, String sampleName,String srunid) throws Exception {
        String bt=Gettoken2();
        GemTestReporter.addTestStep("Test Case", "Test to Update the suite using Put API", STATUS.INFO);
        String url = ProjectConfigData.getProperty(UrlNameFromConfig);
        GemTestReporter.addTestStep("Url for "+method.toUpperCase() + " Request", url, STATUS.INFO);
        String payloads = ProjectSampleJson.getSampleDataString(sampleName);
        JsonParser parser = new JsonParser();
        JsonObject pay = (JsonObject) parser.parse(payloads);
        if (GemJarGlobalVar.environment.equalsIgnoreCase("prod")) {
            pay.addProperty("tc_run_id", "Test_functions_1_bbab0912-c8b8-4d0f-861d-4f5e2b151146");
            pay.addProperty("s_run_id", "GEMPYP_TEST_PROD_63700467-4D93-46AB-A46E-727B2E85DC3F");
        }
        String payload = String.valueOf(ApiHealthCheckUtils.result(pay));
        GemTestReporter.addTestStep("Payload ", String.valueOf(payload), STATUS.INFO);
        String username = ProjectConfigData.getProperty("username");
        Map<String, String> headers = new HashMap<>();
        headers.put("username", username);
        headers.put("bridgeToken", bt);
        Response response=new Response();
        try {
            Request request = new Request();
            request.setURL(url);
            request.setMethod(method);
            request.setHeaderMap(headers);
            request.setRequestPayload(payload);
            response = ApiInvocation.handleRequest(request);
            GemTestReporter.addTestStep(method.toUpperCase() + " Request Verification ", method.toUpperCase() + " Request Executed Successfully", STATUS.PASS);
        } catch (Exception e) {
            GemTestReporter.addTestStep(method.toUpperCase() + " Request Verification ", method.toUpperCase() + " Request Did not Executed Successfully", STATUS.FAIL);
        }
        return response;
    }

    public static Response UpdateSuiteWithoutS_id(String UrlNameFromConfig, String method, String sampleName) throws Exception {
        String bt=Gettoken2();
        GemTestReporter.addTestStep("Test Case", "Test to Update the suite using Put API", STATUS.INFO);
        String url = ProjectConfigData.getProperty(UrlNameFromConfig);
        GemTestReporter.addTestStep("Url for "+method.toUpperCase() + " Request", url, STATUS.INFO);
        String payloads = ProjectSampleJson.getSampleDataString(sampleName);
        JsonParser parser = new JsonParser();
        JsonObject pay = (JsonObject) parser.parse(payloads);
        String payload = String.valueOf(ApiHealthCheckUtils.result(pay));
        GemTestReporter.addTestStep("Payload ", String.valueOf(payload), STATUS.INFO);
        String username = ProjectConfigData.getProperty("username");
        Map<String, String> headers = new HashMap<>();
        headers.put("username", username);
        headers.put("bridgeToken", bt);
        Response response=new Response();
        try {
            Request request = new Request();
            request.setURL(url);
            request.setMethod(method);
            request.setHeaderMap(headers);
            request.setRequestPayload(payload);
            response = ApiInvocation.handleRequest(request);
            GemTestReporter.addTestStep(method.toUpperCase() + " Request Verification ", method.toUpperCase() + " Request Executed Successfully", STATUS.PASS);
        } catch (Exception e) {
            GemTestReporter.addTestStep(method.toUpperCase() + " Request Verification ", method.toUpperCase() + " Request Did not Executed Successfully", STATUS.FAIL);
        }
        return response;
    }
}