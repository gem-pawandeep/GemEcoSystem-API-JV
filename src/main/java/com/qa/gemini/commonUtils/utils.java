package com.qa.gemini.CommonUtils;

import com.gemini.generic.api.utils.*;
import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.utils.GemJarGlobalVar;
import com.gemini.generic.utils.ProjectConfigData;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.RestAssured;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class utils {
    public static Response HitAPI(String UrlNameFromConfig, String method, String step) throws Exception {
        Response response = new Response();
        try {
            Request request = new Request();
            String url = ProjectConfigData.getProperty(UrlNameFromConfig);
            GemTestReporter.addTestStep("Url of the test case", url, STATUS.INFO);
            request.setURL(url);
            request.setMethod(method);
            if (!step.isEmpty()) {
                request.setStep(step);
            }
            response = ApiInvocation.handleRequest(request);
            GemTestReporter.addTestStep(method.toUpperCase() + " Request Verification ", method.toUpperCase() + " Request Executed Successfully", STATUS.PASS);
            GemTestReporter.addTestStep("Response Message", response.getResponseMessage(), STATUS.INFO);
            if ((response.getResponseBody()) != null) {
                GemTestReporter.addTestStep("Response Body", response.getResponseBody(), STATUS.INFO);
            } else {
                GemTestReporter.addTestStep("Response Body", "No-Response", STATUS.INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            GemTestReporter.addTestStep(method.toUpperCase() + " Request Verification ", method.toUpperCase() + " Request Did not Executed Successfully", STATUS.FAIL);
            GemTestReporter.addTestStep("Response Message", response.getResponseMessage(), STATUS.INFO);
        }
        return response;
    }

    public static Response HitAPI(String UrlNameFromConfig, String method) throws Exception {
        return HitAPI(UrlNameFromConfig, method, "");
    }

    public static Response HitAPIWithToken(String UrlNameFromConfig, String method, String step, Map<String, String> headers) throws Exception {
        Response response = new Response();
        try {
            Request request = new Request();
            String url = ProjectConfigData.getProperty(UrlNameFromConfig);
            GemTestReporter.addTestStep("Url for " + method.toUpperCase() + " Request", url, STATUS.INFO);
            request.setURL(url);
            request.setMethod(method);
            if (!headers.isEmpty()) {
                request.setHeaderMap(headers);
            }
            if (!step.isEmpty()) {
                request.setStep(step);
            }
            response = ApiInvocation.handleRequest(request);
            GemTestReporter.addTestStep(method.toUpperCase() + " Request Verification ", method.toUpperCase() + " Request Executed Successfully", STATUS.PASS);
            if (step.isEmpty()) {
                GemTestReporter.addTestStep("Message", response.getErrorMessage(), STATUS.INFO);
            }
            GemTestReporter.addTestStep("Response Message", response.getResponseMessage(), STATUS.INFO);
            if ((response.getResponseBody()) != null) {
                GemTestReporter.addTestStep("Response Body", response.getResponseBody(), STATUS.INFO);
            } else {
                GemTestReporter.addTestStep("Response Body", "No-Response", STATUS.INFO);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep(method.toUpperCase() + " Request Verification ", method.toUpperCase() + " Request Did not Executed Successfully", STATUS.FAIL);
            GemTestReporter.addTestStep("Response Message", response.getResponseMessage(), STATUS.INFO);
        }
        return response;
    }

    public static Response HitAPIWithToken(String UrlNameFromConfig, String method, Map<String, String> headers) throws Exception {
        return HitAPIWithToken(UrlNameFromConfig, method, "", headers);
    }

    public static Response HitAPIWithToken(String UrlNameFromConfig, String method) throws Exception {
        return HitAPIWithToken(UrlNameFromConfig, method, "", new HashMap<>());
    }

    public static Response LoginUser(String UrlNameFromConfig, String method, String sampleName, String step) throws Exception {
        Response response = new Response();
        try {
            Request request = new Request();
            String url = ProjectConfigData.getProperty(UrlNameFromConfig);
            GemTestReporter.addTestStep("Url for " + method.toUpperCase() + " Request", url, STATUS.INFO);
            request.setURL(url);
            request.setMethod(method);
            if (!step.isEmpty()) {
                request.setStep(step);
            }
            String payload = ProjectSampleJson.getSampleDataString(sampleName);
            request.setRequestPayload(payload);
            response = ApiInvocation.handleRequest(request);
            GemTestReporter.addTestStep(method.toUpperCase() + " Request Verification ", method.toUpperCase() + " Request Executed Successfully", STATUS.PASS);
            GemTestReporter.addTestStep("Response Message", response.getResponseMessage(), STATUS.INFO);
            if ((response.getResponseBody()) != null) {
                GemTestReporter.addTestStep("Response Body", response.getResponseBody(), STATUS.INFO);
            } else {
                GemTestReporter.addTestStep("Response Body", "No-Response", STATUS.INFO);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep(method.toUpperCase() + " Request Verification ", method.toUpperCase() + " Request Did not Executed Successfully", STATUS.FAIL);
            GemTestReporter.addTestStep("Response Message", response.getResponseMessage(), STATUS.INFO);
        }
        return response;
    }

    public static Response LoginUser(String UrlNameFromConfig, String method, String sampleName) throws Exception {
        return LoginUser(UrlNameFromConfig, method, sampleName, "");
    }

    public static Response CheckAPiWithAuth(String UrlNameFromConfig, String method, String bt, String sampleName) throws Exception {
        Response response = new Response();
        try {
            GemTestReporter.addTestStep("Test Case", "Test using " + method.toUpperCase() + "API  ", STATUS.INFO);
            String url = ProjectConfigData.getProperty(UrlNameFromConfig);
            GemTestReporter.addTestStep("Url for " + method.toUpperCase() + " Request", url, STATUS.INFO);
            String payloads = ProjectSampleJson.getSampleDataString(sampleName);
            JsonParser parser = new JsonParser();
            JsonObject pay = (JsonObject) parser.parse(payloads);
            String payload = String.valueOf(ApiHealthCheckUtils.result(pay));
            GemTestReporter.addTestStep("Payload ", String.valueOf(payload), STATUS.INFO);
            String username = ProjectConfigData.getProperty("username");
            Map<String, String> headers = new HashMap<>();
            headers.put("username", username);
            headers.put("bridgeToken", bt);
            response = new Response();
            Request request = new Request();
            request.setURL(url);
            request.setMethod(method);
            if (!bt.isEmpty()) {
                request.setHeaderMap(headers);
            }
            request.setRequestPayload(payload);
            response = ApiInvocation.handleRequest(request);
            GemTestReporter.addTestStep(method.toUpperCase() + " Request Verification ", method.toUpperCase() + " Request Executed Successfully", STATUS.PASS);
            GemTestReporter.addTestStep("Response Message", response.getResponseMessage(), STATUS.INFO);
            if ((response.getResponseBody()) != null) {
                GemTestReporter.addTestStep("Response Body", response.getResponseBody(), STATUS.INFO);
            } else {
                GemTestReporter.addTestStep("Response Body", "No-Response", STATUS.INFO);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep(method.toUpperCase() + " Request Verification ", method.toUpperCase() + " Request Did not Executed Successfully", STATUS.FAIL);
            GemTestReporter.addTestStep("Response Message", response.getResponseMessage(), STATUS.INFO);
        }
        return response;
    }

    public static Response CheckAPiWithAuth(String UrlNameFromConfig, String method, String sampleName) throws Exception {
        return CheckAPiWithAuth(UrlNameFromConfig, method, "", sampleName);
    }

    public static Response requestSuiteApi(String UrlNameFromConfig, String method, String bt, String sampleName, String srunid) throws Exception {
        Response response = new Response();
        try {
            GemTestReporter.addTestStep("Test Case", "Test to Update the suite using Put API", STATUS.INFO);
            String url = ProjectConfigData.getProperty(UrlNameFromConfig);
            GemTestReporter.addTestStep("Url for " + method.toUpperCase() + " Request", url, STATUS.INFO);
            String payloads = ProjectSampleJson.getSampleDataString(sampleName);
            JsonParser parser = new JsonParser();
            JsonObject pay = (JsonObject) parser.parse(payloads);
            if (GemJarGlobalVar.environment.equalsIgnoreCase("prod") && (!srunid.isEmpty())) {
                pay.addProperty("s_run_id", srunid);
            }
            String payload = String.valueOf(ApiHealthCheckUtils.result(pay));
            GemTestReporter.addTestStep("Payload ", String.valueOf(payload), STATUS.INFO);
            String username = ProjectConfigData.getProperty("username");
            Map<String, String> headers = new HashMap<>();
            headers.put("username", username);
            headers.put("bridgeToken", bt);
            response = new Response();
            Request request = new Request();
            request.setURL(url);
            request.setMethod(method);
            if (!bt.isEmpty()) {
                request.setHeaderMap(headers);
            }
            request.setRequestPayload(payload);
            response = ApiInvocation.handleRequest(request);
            GemTestReporter.addTestStep(method.toUpperCase() + " Request Verification ", method.toUpperCase() + " Request Executed Successfully", STATUS.PASS);
            GemTestReporter.addTestStep("Response Message", response.getResponseMessage(), STATUS.INFO);
            if ((response.getResponseBody()) != null) {
                GemTestReporter.addTestStep("Response Body", response.getResponseBody(), STATUS.INFO);
            } else {
                GemTestReporter.addTestStep("Response Body", "No-Response", STATUS.INFO);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep(method.toUpperCase() + " Request Verification ", method.toUpperCase() + " Request Did not Executed Successfully", STATUS.FAIL);
            GemTestReporter.addTestStep("Response Message", response.getResponseMessage(), STATUS.INFO);
        }
        return response;
    }

    public static Response requestSuiteApi(String UrlNameFromConfig, String method, String sampleName, String srunid) throws Exception {
        return requestSuiteApi(UrlNameFromConfig, method, "", sampleName, srunid);
    }

    public static Response requestSuiteApi(String UrlNameFromConfig, String method, String sampleName) throws Exception {
        return requestSuiteApi(UrlNameFromConfig, method, Gettoken2(), sampleName, "");
    }

    public static Response UpdateSuiteApi(String UrlNameFromConfig, String method, String sampleName) throws Exception {
        String bt = Gettoken2();
        Response response = new Response();
        try {
            GemTestReporter.addTestStep("Test Case", "Test to Update the suite using Put API", STATUS.INFO);
            String url = ProjectConfigData.getProperty(UrlNameFromConfig);
            GemTestReporter.addTestStep("Url for " + method.toUpperCase() + " Request", url, STATUS.INFO);
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
            Request request = new Request();
            request.setURL(url);
            request.setMethod(method);
            request.setHeaderMap(headers);
            request.setRequestPayload(payload);
            response = ApiInvocation.handleRequest(request);
            GemTestReporter.addTestStep(method.toUpperCase() + " Request Verification ", method.toUpperCase() + " Request Executed Successfully", STATUS.PASS);
            GemTestReporter.addTestStep("Response Message", response.getResponseMessage(), STATUS.INFO);
            if ((response.getResponseBody()) != null) {
                GemTestReporter.addTestStep("Response Body", response.getResponseBody(), STATUS.INFO);
            } else {
                GemTestReporter.addTestStep("Response Body", "No-Response", STATUS.INFO);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep(method.toUpperCase() + " Request Verification ", method.toUpperCase() + " Request Did not Executed Successfully", STATUS.FAIL);
            GemTestReporter.addTestStep("Response Message", response.getResponseMessage(), STATUS.INFO);
        }
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
            String Body = response.getResponseBody();
            JsonParser parser = new JsonParser();
            JsonObject Boddy = (JsonObject) parser.parse(Body);
            JsonObject to = (JsonObject) Boddy.get("data");
            Token = String.valueOf(to.get("token"));
            return Token;
        } catch (Exception e) {
            GemTestReporter.addTestStep("Final token", "Some error occured while fetching token", STATUS.FAIL);
            e.printStackTrace();
            return null;
        }
    }

    public static String Gettoken2() throws Exception {
        String url = ProjectConfigData.getProperty("Gettoken");
        String too = null;
        String j = token();
        Response res = null;
        Map<String, String> headers = new HashMap<>();
        assert j != null;
        String jnew = j.replaceAll("^\"|\"$", "");
        headers.put("Authorization", "Bearer " + jnew);
        try {
            Request request = new Request();
            request.setURL(url);
            request.setMethod("get");
            request.setHeaderMap(headers);
            res = ApiInvocation.handleRequest(request);
        } catch (Exception e) {
            GemTestReporter.addTestStep(" Get Request Verification ", "Get Request Did not Executed Successfully", STATUS.FAIL);
        }
        assert res != null;
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

    public static int FileUpload(String url, String filePath, String username, String bt) {
        try {
            String u = ProjectConfigData.getProperty(url);
            GemTestReporter.addTestStep("Url of the test case", u, STATUS.INFO);
            CloseableHttpClient httpclient = HttpClients.createDefault();
            MultipartEntityBuilder entitybuilder = MultipartEntityBuilder.create();
            entitybuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            entitybuilder.addBinaryBody("file", new File(filePath));
            HttpEntity mutiPartHttpEntity = entitybuilder.build();
            RequestBuilder reqbuilder = RequestBuilder.post(u);
            reqbuilder.setEntity(mutiPartHttpEntity);
            HttpUriRequest multipartRequest = reqbuilder.build();
            if (!username.equals("")) {
                multipartRequest.setHeader(new BasicHeader("username", username));
            }
            multipartRequest.setHeader(new BasicHeader("bridgeToken", bt));
            HttpResponse httpresponse = httpclient.execute(multipartRequest);
            GemTestReporter.addTestStep("POST Request Verification", "POST request executed Successfully", STATUS.PASS);
            JsonObject js = (JsonObject) JsonParser.parseString(EntityUtils.toString(httpresponse.getEntity()));
            GemTestReporter.addTestStep("Response Body", String.valueOf(js), STATUS.INFO);
            GemTestReporter.addTestStep("Response Message", js.get("message").getAsString(), STATUS.INFO);
            GemTestReporter.addTestStep("operation", js.get("operation").getAsString(), STATUS.PASS);
            return httpresponse.getStatusLine().getStatusCode();
        } catch (IOException e) {
            GemTestReporter.addTestStep("POST Request Verification", String.valueOf(e), STATUS.FAIL);
        }
        return 0;
    }

    public static int FileUpload2(String url, String filePath, String jnew) {
        try {
            String u = ProjectConfigData.getProperty(url);
            GemTestReporter.addTestStep("Url of the test case", u, STATUS.INFO);
            CloseableHttpClient httpclient = HttpClients.createDefault();
            MultipartEntityBuilder entitybuilder = MultipartEntityBuilder.create();
            entitybuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            entitybuilder.addBinaryBody("file", new File(filePath));
            HttpEntity mutiPartHttpEntity = entitybuilder.build();
            RequestBuilder reqbuilder = RequestBuilder.post(u);
            reqbuilder.setEntity(mutiPartHttpEntity);
            HttpUriRequest multipartRequest = reqbuilder.build();
            multipartRequest.setHeader(new BasicHeader("Authorization", "Bearer " + jnew));
            HttpResponse httpresponse = httpclient.execute(multipartRequest);
            GemTestReporter.addTestStep("POST Request Verification", "POST request executed Successfully", STATUS.PASS);
            JsonObject js = (JsonObject) JsonParser.parseString(EntityUtils.toString(httpresponse.getEntity()));
            GemTestReporter.addTestStep("Response Body", String.valueOf(js), STATUS.INFO);
            GemTestReporter.addTestStep("Response Message", js.get("message").getAsString(), STATUS.INFO);
            GemTestReporter.addTestStep("operation", js.get("operation").getAsString(), STATUS.PASS);
            return httpresponse.getStatusLine().getStatusCode();
        } catch (IOException e) {
            GemTestReporter.addTestStep("POST Request Verification", String.valueOf(e), STATUS.FAIL);
        }
        return 0;
    }

    public static Response TextUpload(String url, String TextToEnter, String username, String bt) {
        Response response = new Response();
        try {
            String urlss = ProjectConfigData.getProperty(url);
            Request request = new Request();
            Map<String, String> headers = new HashMap<>();
            headers.put("username", username);
            headers.put("bridgeToken", bt);
            request.setURL(urlss);
            request.setMethod("Post");
            request.setHeaderMap(headers);
            request.setStep("Upload Text");
            request.setRequestPayload(TextToEnter);
            response = ApiInvocation.handleRequest(request);
            GemTestReporter.addTestStep("POST" + " Request Verification ", "POST" + " Request Executed Successfully", STATUS.PASS);
            GemTestReporter.addTestStep("Response Message", response.getResponseMessage(), STATUS.INFO);
            if ((response.getResponseBody()) != null) {
                GemTestReporter.addTestStep("Response Body", response.getResponseBody(), STATUS.INFO);
            } else {
                GemTestReporter.addTestStep("Response Body", "No-Response", STATUS.INFO);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("POST" + " Request Verification ", "POST" + " Request Did not Executed Successfully", STATUS.FAIL);
            GemTestReporter.addTestStep("Response Message", response.getResponseMessage(), STATUS.INFO);
        }
        return response;
    }

    public static Response TextUpload2(String url, String TextToEnter, String username, String bt) {
        Response response = new Response();
        try {
            String urlss = ProjectConfigData.getProperty(url);
            Request request = new Request();
            Map<String, String> headers = new HashMap<>();
            headers.put("username", username);
            headers.put("bridgeToken", bt);
            request.setURL(urlss);
            request.setMethod("Post");
            request.setHeaderMap(headers);
            request.setRequestPayload(TextToEnter);
            response = ApiInvocation.handleRequest(request);
            GemTestReporter.addTestStep("POST" + " Request Verification ", "POST" + " Request Executed Successfully", STATUS.PASS);
            GemTestReporter.addTestStep("Response Message", response.getResponseMessage(), STATUS.INFO);
            if ((response.getResponseBody()) != null) {
                GemTestReporter.addTestStep("Response Body", response.getResponseBody(), STATUS.INFO);
            } else {
                GemTestReporter.addTestStep("Response Body", "No-Response", STATUS.INFO);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("POST" + " Request Verification ", "POST" + " Request Did not Executed Successfully", STATUS.FAIL);
            GemTestReporter.addTestStep("Response Message", response.getResponseMessage(), STATUS.INFO);
        }
        return response;
    }

    public static Response TextUpload3(String url, String TextToEnter, String bt) {
        Response response = new Response();
        try {
            String urlss = ProjectConfigData.getProperty(url);
            Request request = new Request();
            Map<String, String> headers = new HashMap<>();
            headers.put("Authorization", "Bearer " + bt);
            request.setURL(urlss);
            request.setMethod("Post");
            request.setHeaderMap(headers);
            request.setRequestPayload(TextToEnter);
            response = ApiInvocation.handleRequest(request);
            GemTestReporter.addTestStep("POST" + " Request Verification ", "POST" + " Request Executed Successfully", STATUS.PASS);
            GemTestReporter.addTestStep("Response Message", response.getResponseMessage(), STATUS.INFO);
            if ((response.getResponseBody()) != null) {
                GemTestReporter.addTestStep("Response Body", response.getResponseBody(), STATUS.INFO);
            } else {
                GemTestReporter.addTestStep("Response Body", "No-Response", STATUS.INFO);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("POST" + " Request Verification ", "POST" + " Request Did not Executed Successfully", STATUS.FAIL);
            GemTestReporter.addTestStep("Response Message", response.getResponseMessage(), STATUS.INFO);
        }
        return response;
    }

    public static Response Access(String url, String method, String sampleName, Map<String, String> headers, String step) {
        Response response = new Response();
        try {
            String urlss = ProjectConfigData.getProperty(url);
            GemTestReporter.addTestStep("Request URL", urlss, STATUS.INFO);
            Request request = new Request();
            request.setURL(urlss);
            request.setMethod(method);
            if (!headers.isEmpty()) {
                request.setHeaderMap(headers);
            }
            if (!step.equals("")) {
                request.setStep(step);
            }
            if (!sampleName.equals("")) {
                String payload = ProjectSampleJson.getSampleDataString(sampleName);
                request.setRequestPayload(payload);
            }
            response = ApiInvocation.handleRequest(request);
            GemTestReporter.addTestStep(method.toUpperCase() + " Request Verification ", method.toUpperCase() + " Request Executed Successfully", STATUS.PASS);
            if (step.isEmpty()) {
                GemTestReporter.addTestStep("Message", response.getErrorMessage(), STATUS.INFO);
            }
            GemTestReporter.addTestStep("Response Message", response.getResponseMessage(), STATUS.INFO);
            if ((response.getResponseBody()) != null) {
                GemTestReporter.addTestStep("Response Body", response.getResponseBody(), STATUS.INFO);
            } else {
                GemTestReporter.addTestStep("Response Body", "No-Response", STATUS.INFO);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep(method.toUpperCase() + " Request Verification ", method.toUpperCase() + " Request Did not Executed Successfully", STATUS.FAIL);
            //GemTestReporter.addTestStep("Response Message", response.getResponseMessage(), STATUS.INFO);
        }
        return response;
    }

    public static Response Access(String url, String method, String sampleName, Map<String, String> headers) {
        return Access(url, method, sampleName, headers, "");
    }

    public static Response Access(String url, String method, Map<String, String> headers) {
        return Access(url, method, "", headers, "");
    }
}

