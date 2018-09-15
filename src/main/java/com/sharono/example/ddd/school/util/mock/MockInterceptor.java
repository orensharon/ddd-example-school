package com.sharono.example.ddd.school.util.mock;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;
import sun.misc.IOUtils;

/**
 * Created by manuelvicnt on 11/11/2016.
 */

public class MockInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        addDelay();

        try {
            byte[] data = Files.readAllBytes(Paths.get(this.getClass().getClassLoader().getResource("school_200.json").toURI()));
            return new Response.Builder()
                    .code(200)
                    .message("OK")
                    .request(chain.request())
                    .protocol(Protocol.HTTP_1_0)
                    .body(ResponseBody.create(MediaType.parse("application/json"), data))
                    .build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void addDelay() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
