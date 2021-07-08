package io.github.marcodiri.java_socketio_chatroom_client;

import java.net.URI;
import io.github.marcodiri.java_socketio_chatroom_client.model.Message;
import io.socket.client.IO;
import io.socket.client.IO.Options;
import io.socket.client.Socket;

public class ChatroomClient {

	private final Socket socket;

	public ChatroomClient(URI uri, Options options) {
		this.socket = IO.socket(uri, options);
	}

	public Socket getSocket() {
		return socket;
	}

	public boolean isConnected() {
		return socket.connected();
	}

	public void connect() {
		socket.on(Socket.EVENT_CONNECT, objects -> {
			socket.emit("join");
		});
		socket.connect();
	}

	public void disconnect() {
		socket.disconnect();
	}

	public void sendMessage(Message msg) throws RuntimeException {
		if (isConnected()) {
			socket.emit("msg", msg.toJSON());
		} else {
			throw new RuntimeException("Unable to send message when not connected to server");
		}
	}

}
