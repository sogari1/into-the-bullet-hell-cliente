package com.intothebullethell.game.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import com.intothebullethell.game.globales.GameData;


public class ClientThread extends Thread {

	private InetAddress serverIp;
    private final int SERVER_PORT = 5555;
    private DatagramSocket socket;
    private boolean end = false;
    private String specialChar = "!";

    public ClientThread() {

        try {
            serverIp = InetAddress.getByName("255.255.255.255");
            socket = new DatagramSocket();
        } catch (SocketException | UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        while(!end){
            DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
            try {
                socket.receive(packet);
                procesarMensajeDeServidor(packet);
            } catch (IOException e) {

            }
        }
    }
    private void procesarMensajeDeServidor(DatagramPacket packet) {
        String message = new String(packet.getData()).trim();
//        System.out.println("CLIENTE: Mensaje recibido: " + message);
        String[] parts = message.split(specialChar);
        switch(parts[0]){
        case "connection":
        	manejarConexion(parts[1], Integer.parseInt(parts[2]), packet.getAddress());
            break;
        case "startgame":
        	GameData.networkListener.empezarJuego();
            break;
        case "jugador":
            manejarJugador(parts);
            break;
        case "enemigo":
            manejarEnemigos(parts);
            break;
        case "proyectil":
            manejarProyectiles(parts);
            break;
        case "tiempo":
        	manejarTiempo(parts);
        	break;
        }
        
    }
    private void manejarTiempo(String[] parts) {
		
	}

	private void manejarJugador(String[] parts) {
		switch(parts[1]) {
		case "mover": 
			GameData.networkListener.actualizarJugadorPosicion(Integer.parseInt(parts[2]), Float.parseFloat(parts[3]), Float.parseFloat(parts[4]));
			break;
		}
    }

    private void manejarEnemigos(String[] parts) {
    	switch (parts[1]) {
        case "mover":
        	GameData.networkListener.moverEnemigo(Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4]));
            break;
        case "crear":
        	GameData.networkListener.añadirEnemigo(Float.parseFloat(parts[2]), Float.parseFloat(parts[3]));
        	break;
        case "remover":
        	GameData.networkListener.removerEnemigo();
        	break;
    	}
    }

    private void manejarProyectiles(String[] parts) {
        int projectileId = Integer.parseInt(parts[1]);
        float xPos = Float.parseFloat(parts[2]);
        float yPos = Float.parseFloat(parts[3]);
        
        GameData.networkListener.actualizarProyectilPosicion(projectileId, xPos, yPos);
    }
    private void manejarConexion(String state, int clienteNumero, InetAddress serverIp) {
        this.serverIp = serverIp;
        switch(state){
            case "successful":
            	GameData.clienteNumero = clienteNumero;
                break;
        }
    }
    public void enviarMensajeAlServidor(String msg){
        byte[] data = msg.getBytes();
        DatagramPacket packet = new DatagramPacket(data, data.length, serverIp, SERVER_PORT);
        try {
            socket.send(packet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void end(){
        end = true;
        socket.close();
    }

}
