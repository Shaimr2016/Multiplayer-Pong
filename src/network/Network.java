package network;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import java.net.URISyntaxException;

public class Network {

    private Socket socket;

    public Network() {
        try {
            socket = IO.socket("http://localhost:5000");
            socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                public void call(Object ... objects) {

                }
            });
            socket.on(Socket.EVENT_CONNECT_ERROR, new Emitter.Listener() {
                @Override
                public void call(Object... objects) {

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

                }
            });
            socket.on("other_move", new Emitter.Listener() {
                @Override
                public void call(Object... objects) {

                }
            });
            socket.on("ball_bounce", new Emitter.Listener() {
                @Override
                public void call(Object... objects) {

                }
            });
            socket.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
