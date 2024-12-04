import java.util.Random;

public class Client extends Thread {
    String user_input;
    App app;
    

    public Client(String user_input, App app) {
        this.user_input = user_input;
        this.app = app;
    }

    //runs when address is entered into textbox
    @Override
    public void run() {
        //setup serversocket

        while (true) {
            //loop this code
            updateApp();
        }
    }

    private void updateApp() {
        //generate random characters
        Random rand = new Random();
        String test = "";
        for (int i = 0; i < 128 * 72; i++) {
            test += rand.nextInt(10);
        }

        //update app with string of length 128 * 72
        app.setOutput(test);
    }
}
