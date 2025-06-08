package com.dreamworks;

import com.linecorp.armeria.client.WebClient;
import com.linecorp.armeria.server.Server;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JavaTest {

    private Server server;

    @BeforeEach
    void setUp() {
        server = App.newServer(0); // Use port 0 for random available port
        server.start().join();
    }

    @AfterEach
    void tearDown() {
        if (server != null) {
            server.stop().join();
        }
    }

    @Test
    void testMyServerResponse() {
        int port = server.activeLocalPort();
        WebClient client = WebClient.of("http://127.0.0.1:" + port);
        String response = client.get("/").aggregate().join().contentUtf8();
        assertThat(response).isEqualTo("Hello Armeria!");
    }
}
