package com.dreamworks;

import com.linecorp.armeria.client.WebClient;
import com.linecorp.armeria.common.HttpResponse;
import com.linecorp.armeria.server.ServerBuilder;

import com.linecorp.armeria.testing.junit5.server.ServerExtension;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {

    @RegisterExtension
    static final ServerExtension server = new ServerExtension() {
        @Override
        protected void configure(ServerBuilder sb) {
            sb.http(0); // 0 = bind to random available port
            sb.service("/", (ctx, req) -> HttpResponse.of("Hello Armeria!"));
        }
    };

    @Test
    void testRootPathResponse() {
        WebClient client = WebClient.of(server.httpUri());
        String response = client.get("/").aggregate().join().contentUtf8();
        assertThat(response).isEqualTo("Hello Armeria!");
    }
}
