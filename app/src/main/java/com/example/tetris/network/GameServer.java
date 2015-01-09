package com.example.tetris.network;

import android.util.Log;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by ssm on 2015-01-08.
 */
public class GameServer {
    ServerSocket server;
    Socket socket;
    public static final int PORT = 9999;

    public GameServer() {
        PrintWriter writer = null;
        try {
            server = new ServerSocket(PORT);
            socket = server.accept();
            writer = new PrintWriter(socket.getOutputStream());
            writer.write("Server Connected!!");
            writer.flush();
        } catch (IOException e) {
            Log.e("net", "연결 오류 ");
        } finally {
            try {
                if (writer != null)
                    writer.close();
                if (socket != null)
                    socket.close();
                if(server != null)
                    server.close();
            } catch (IOException e) {
                Log.e("net", "스트림 닫기 실패");
            }
        }
    }
}