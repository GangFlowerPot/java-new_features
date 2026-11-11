package com.dgq.jl.java9.HttpClient;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * java9 引入了全新的Http客户端API
 *
 * 提供了一个新的HTTP客户端（HttpClient），它将替代仅适用于blocking模式的HttpURLConnection 提供对WebSocket和HTTP/2的支持
 * 具体的包目录为
 * jdk.incubator.http.HttpClient
 * jdk.incubator.http.HttpRequest
 * jdk.incubator.http.HttpResponse
 *
 * 在Java 11中又再次升级，处于为正式可用状态，同时更改目录到 java.net.http
 *
 *
 * NOTICE jdk8 也存在httpClient ，但是不是java官方的api，而是apache相关的api
 */
public class _HttpClient {

    @Test
    public void testHttpClient() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest req = HttpRequest.newBuilder(URI.create("http://www.baidu.com")).GET().build();
            HttpResponse<String> response = null;
            // java9 api对于响应的用法
            // response = client.send(req,HttpResponse.BodyHandler.asString());
            // jdk11 修改了api的用法
            response = client.send(req, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());
            System.out.println(response.version().name());
            System.out.println(response.body());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
