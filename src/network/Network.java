package network;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import main.Main;
import objectdraw.Location;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

public class Network {

    private Socket socket;

    public Network() {
        try {
            socket = IO.socket("http://192.168.1.169:5000");
            socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                public void call(Object ... objects) {

                }
            });
            socket.on(Socket.EVENT_CONNECT_ERROR, new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    System.out.println("Failed to connect server");
                }
            });
            socket.on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... objects) {

                }
            });
            socket.on("start", new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    System.out.println("starting game");
                    Main.startGame();
                }
            });
            socket.on("other_move", new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    JSONObject j = (JSONObject) objects[0];
                    try {
                        Location l = (Location) j.get("newPosition");
                        Main.getGame().getOtherPlayer().moveTo(l);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            socket.on("ball_move", new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    JSONObject j = (JSONObject) objects[0];
                    try {
                        Main.getGame().getBall().moveTo((Location) j.get("position"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            socket.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    public void emit(String message, Object ... objects) {
        socket.emit(message, objects);
    }
}
