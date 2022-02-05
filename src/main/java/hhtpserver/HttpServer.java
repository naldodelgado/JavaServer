package hhtpserver;

import hhtpserver.config.Configuration;
import hhtpserver.config.ConfigurationManager;
import hhtpserver.core.ServerListenerThread;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class HttpServer {
    private final static Logger LOGGER = LoggerFactory.getLogger(HttpServer.class);

    public static void main(String[] args) throws IOException {
        LOGGER.info("Server starting...");

        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getConfiguration();

        ServerListenerThread serverListenerThread = new ServerListenerThread(conf.getPort(),conf.getWebroot());
        serverListenerThread.start();

    }
}
