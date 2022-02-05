package hhtpserver.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class HttpsConnectionWorkerThread extends Thread{

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpsConnectionWorkerThread.class);
    private Socket socket;

    public HttpsConnectionWorkerThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){
        InputStream inputStream = null;
        PrintWriter outputStream = null;
        try {
            inputStream = socket.getInputStream();
            outputStream = new PrintWriter(socket.getOutputStream(), true);

            // TODO we would read

            // TODO we would write
            String html = "<html><head><title>Simple Java HTTP Server</title></head><body><h1>This page was created by naldo testing a Java server</h1></body></html>";
            final String CRFL = "\n\r";
            String response =
                    "HTTP/1.1 200 OK" + CRFL + // Status Line
                            "Content-Length" + html.getBytes().length + CRFL + "Content-Type: text/html" + CRFL +
                            CRFL +
                            html +
                            CRFL + CRFL;

            outputStream.printf(response);
            outputStream.flush();

        }catch(IOException e){e.printStackTrace(); LOGGER.error("Could not establish connection", e);
        }finally{
            if(inputStream!=null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(outputStream!=null)
                outputStream.close();
            if(socket!=null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            LOGGER.info("Connection Processing Finished");
        }
    }
}
