package es.ulpgc.spotify;

import es.ulpgc.spotify.controller.Controller;

public class Main {

    public static void main(String[] args) throws Exception {
        Controller controller = new Controller();
        controller.store();
    }
}
