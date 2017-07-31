package com.eunion.manage.common.util;

import com.eunion.manage.entity.User;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ys on 2016/3/15.
 */
@Component
public class HttpSendMessage<E> {

    public static void main(String[] args) throws IllegalAccessException, IOException, InstantiationException {
        HttpSendMessage httpSendMessage = new HttpSendMessage();
        Map<String, String> map = new HashMap<String, String>();
        map.put("user", "zhangshan");
        map.put("password", "123456");

        User user =(User) httpSendMessage.sendPostHttp(User.class, null, map, "https://api.douban.com/v2", "book", "1220562");
        System.out.println(user.getId());
    }

    public E sendGetHttp(Class<E> e, Object paramsObj, Map<String, String> paramsMap, String urlString, String method, String function) throws IllegalAccessException, InstantiationException, IOException {
        StringBuffer param = new StringBuffer();
        param.append(urlString);
        param.append("/" + method);
        param.append("/" + function);
        int i = 0;
        for (String key : paramsMap.keySet()) {
            if (i == 0)
                param.append("?");
            else
                param.append("&");
            param.append(key).append("=").append(paramsMap.get(key));
            i++;
        }
        CloseableHttpClient httpclient = HttpClients.createDefault();
        //设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(1000).setConnectTimeout(1000).build();
        HttpGet httpGet = new HttpGet(param.toString());
        httpGet.setConfig(requestConfig);
        CloseableHttpResponse responseGet = httpclient.execute(httpGet);
        try {
            HttpEntity entity = responseGet.getEntity();
            System.out.println(responseGet.getStatusLine().getStatusCode());
            InputStream inputStream = entity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String result = "";
            String line = "";
            while (null != (line = reader.readLine())) {
                result += line;
            }
            System.out.println(result);

            EntityUtils.consume(entity);
        } finally {
            responseGet.close();
        }
        E t = e.newInstance();

        return t;
    }

    public E sendPostHttp(Class<E> e, Object paramsObj, Map<String, String> paramsMap, String urlString, String method, String function) throws IllegalAccessException, InstantiationException, IOException {
        E t = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        StringBuffer param = new StringBuffer();
        param.append(urlString);
        param.append("/" + method);
        param.append("/" + function);
        HttpPost httpPost = new HttpPost(param.toString());
        //拼接参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(1000).setConnectTimeout(1000).build();
        for (String key : paramsMap.keySet()) {
            nvps.add(new BasicNameValuePair(key, paramsMap.get(key)));
        }
        httpPost.setEntity(new UrlEncodedFormEntity(nvps));
        httpPost.setConfig(requestConfig);
        CloseableHttpResponse response = httpclient.execute(httpPost);

        try {
            System.out.println(response.getStatusLine());
            HttpEntity entity = response.getEntity();
            InputStream inputStream = entity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String result = "";
            String line = "";
            while (null != (line = reader.readLine())) {
                result += line;
            }
            System.out.println(result);
            Gson gson = new Gson();

            t = gson.fromJson("{id:12,\"userName\":\"yangshuo\",\"userPassword\":\"12345\"}", e);
            EntityUtils.consume(entity);
        } finally {
            response.close();
        }

        return t;
    }
}
