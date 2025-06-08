package com.dreamworks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linecorp.armeria.common.HttpResponse;
import com.linecorp.armeria.server.Server;
import com.linecorp.armeria.server.ServerBuilder;

/**
 *
 * @author airhacks.com
 */
interface App {

     public static final int PORT = 8000;
     static final Logger logger = LoggerFactory.getLogger(App.class);

    static void main(String... args) {
//        System.out.println("is working");

        final Server server = newServer(PORT);
        server.closeOnJvmShutdown();
        server.start().join();
        logger.info("Server has been started on port"+PORT);

    }

    static Server newServer(int port){
        ServerBuilder sb = Server.builder();
        return sb.http(port)
                .service("/", (ctx, req)-> HttpResponse.of("Hello Armeria!"))
                .build();
    }
}

