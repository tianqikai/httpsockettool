package com.model.utils;

import java.io.IOException;

import okhttp3.*;

public class OkHttpClientUtils {
    public void doOkGet(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        final Call call = client.newCall(request);
        Response response = call.execute();
        System.out.println(response.body().string());
    }

}
