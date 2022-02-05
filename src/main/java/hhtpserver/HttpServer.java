package hhtpserver;

import hhtpserver.config.Configuration;
import hhtpserver.config.ConfigurationManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class HttpServer {
    public static void main(String[] args) throws IOException {
        System.out.println("Server starting...");
        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getConfiguration();

        System.out.println("port : " + conf.getPort());
        System.out.println("webroot : " + conf.getWebroot());

        try {
            ServerSocket serverSocket = new ServerSocket(conf.getPort());
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            // TODO we would read

            // TODO we would write
            String html = "<html><head><title>Simple Java HTTP Server</title></head><body><h1>This page was created by naldo testing a Java server</h1></body></html>";
            final String CRFL = "\n\r";
            String response =
                    "HTTP/1.1 200 OK" + CRFL + // Status Line
                    "Content-Length" + html.getBytes().length + CRFL + // HEADER
                    CRFL +
                    html +
                    CRFL + CRFL;

            outputStream.write(response.getBytes());

            inputStream.close();
            outputStream.close();
            socket.close();
            serverSocket.close();
        }catch(IOException e){e.printStackTrace();}
    }
}
