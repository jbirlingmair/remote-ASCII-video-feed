import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Random;

public class Client extends Thread {
    App app;
    String address;
    int port;

    public Client(String user_input, App app) {
        address = user_input.substring(0, user_input.lastIndexOf(':'));
        port = Integer.parseInt(user_input.substring(user_input.lastIndexOf(':') + 1));
        this.app = app;
    }

    @Override
    public void run() {
        try {
            /* Socket socket = new Socket(address, port);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream())); */

            while (true) {

                //EXAMPLE CODE: to show ui function
                Random rand = new Random();
                String test = "";
                for (int i = 0; i < 128 * 72; i++) {
                    test += rand.nextInt(10);
                }
                app.setOutput(test);
                sleep(500);


                // app.setOutput(reader.readLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
