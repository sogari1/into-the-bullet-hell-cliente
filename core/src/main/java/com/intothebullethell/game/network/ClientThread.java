package com.intothebullethell.game.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import com.intothebullethell.game.globales.GameData;


public class ClientThread extends Thread {

	private InetAddress serverIp;
    private final int SERVER_PORT = 5555;
    private DatagramSocket socket;
    private boolean end = false;
    private boolean conectado = false; 
    private String specialChar = "!";
    private final Object lock = new Object(); 

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
        	synchronized (lock) {
        		if (!conectado) {
        			try {
        				lock.wait(); 
        			} catch (InterruptedException e) {
        				System.out.println("Error en el hilo: " + e.getMessage());
        			}
        		}
        	}
        	DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
        	try {
        		socket.receive(packet);
        		procesarMensajeDelServidor(packet);
        	} catch (IOException e) {
        		
        	}
        }
    }
    private void procesarMensajeDelServidor(DatagramPacket packet) {
        String message = new String(packet.getData()).trim();
//        System.out.println("CLIENTE: Mensaje recibido de servidor: " + message);
        String[] parts = message.split(specialChar);
        switch(parts[0]){
        case "empezarjuego":
        	GameData.networkListener.empezarJuego();
            break;
        case "gameover":
        	GameData.networkListener.gameOver();
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
        case "objeto":
        	manejarObjetos(parts);
        	break;
        case "tiempo":
        	GameData.networkListener.actualizarTiempo(Integer.parseInt(parts[1]));
        	break;
        case "ronda":
        	GameData.networkListener.actualizarRonda(Integer.parseInt(parts[1]));
        	break;
        case "servidorapagado":
        	GameData.networkListener.gameOver();
        	GameData.networkListener.mostrarError(parts[1]);
        	System.out.println("CLIENTE: El servidor se ha apagado");
        	this.conectado = false; 
        	break;
        case "clientedesconectado":
        	GameData.networkListener.gameOver();
        	GameData.networkListener.mostrarError(parts[1]);
        	System.out.println("CLIENTE: Un jugador se desconectó");
        	this.conectado = false; 
        	break;
        }
        
    }

	private void manejarObjetos(String[] parts) {
		switch(parts[1]) {
		case "crear": 
			GameData.networkListener.añadirObjeto(parts[2], Float.parseFloat(parts[3]), Float.parseFloat(parts[4]));
			break;
		case "remover":
			GameData.networkListener.removerObjeto(Integer.parseInt(parts[2]));
			break;
		}
	}

	private void manejarJugador(String[] parts) {
		switch(parts[1]) {
		case "mover": 
			GameData.networkListener.actualizarJugadorPosicion(Integer.parseInt(parts[2]), Float.parseFloat(parts[3]), Float.parseFloat(parts[4]));
			break;
		case "direccion":
			GameData.networkListener.actualizarDireccionJugador(Integer.parseInt(parts[2]), parts[3]);
			break;
		case "vida":
			GameData.networkListener.actualizarVidaJugador(Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
			break;
		case "muerto":
			GameData.networkListener.jugadorMuerto(Integer.parseInt(parts[2]));
			break;
		case "arma":
			GameData.networkListener.actualizarArmaJugador(Integer.parseInt(parts[2]), parts[3]);
			break;
		case "activo":
			GameData.networkListener.actualizarActivoJugador(Integer.parseInt(parts[2]), parts[3]);
			break;
		case "activousado":
			GameData.networkListener.activoUsadoJugador(Integer.parseInt(parts[2]), Boolean.parseBoolean(parts[3]));
			break;
		case "armamunicion":
			GameData.networkListener.actualizarBalasArmaJugador(Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4]));
			break;
		}
    }

    private void manejarEnemigos(String[] parts) {
    	switch (parts[1]) {
        case "mover":
        	GameData.networkListener.moverEnemigo(Integer.parseInt(parts[2]), Float.parseFloat(parts[3]), Float.parseFloat(parts[4]));
            break;
        case "crear":
        	GameData.networkListener.añadirEnemigo(parts[2], Float.parseFloat(parts[3]), Float.parseFloat(parts[4]));
        	break;
        case "remover":
        	GameData.networkListener.removerEnemigo(Integer.parseInt(parts[2]));
        case "cantidad":
        	GameData.networkListener.actualizarEnemigosRestantes(Integer.parseInt(parts[2]));
        	break;
    	}
    }

    private void manejarProyectiles(String[] parts) {
    	switch(parts[1]) {
    	case "mover":
    		GameData.networkListener.actualizarProyectilPosicion(Integer.parseInt(parts[2]), Float.parseFloat(parts[3]), Float.parseFloat(parts[4]));
    		break;
    	case "crear":
    		GameData.networkListener.añadirProyectil(parts[2], Float.parseFloat(parts[3]), Float.parseFloat(parts[4]), Float.parseFloat(parts[5]), Integer.parseInt(parts[6]), Boolean.parseBoolean(parts[7]));
    		break;
    	case "remover":
    		GameData.networkListener.removerProyectil(Integer.parseInt(parts[2]));
    		break;
    	}
    }
    private void manejarConexion(String state, int clienteNumero, InetAddress serverIp) {
        this.serverIp = serverIp;
        switch(state){
            case "exitosa":
            	GameData.clienteNumero = clienteNumero;
            	System.out.println("CLIENTE: Conexión exitosa con el servidor.");
                break;
        }
    }
    public boolean conectarAlServidor() {
        int intentos = 0;
        int tiempoEspera = 3000;
        boolean conectado = false;

        while (intentos < 3 && !conectado) {
            try {
                enviarMensajeAlServidor("conectar");
                System.out.println("\nSolicitando conexión...");

                socket.setSoTimeout(tiempoEspera); 
                DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);

                try {
                    socket.receive(packet);
                    String message = new String(packet.getData()).trim();
                    
                    String[] parts = message.split(specialChar);

                    if (parts[0].equals("conexion")) {
                        manejarConexion(parts[1], Integer.parseInt(parts[2]), packet.getAddress());
                        conectado = true; 
                        this.conectado = true; 
                        synchronized (lock) {
                            lock.notify(); 
                        }
                    }

                } catch (SocketTimeoutException e) {
                    System.out.println("CLIENTE: No se recibió respuesta del servidor en el intento " + (intentos + 1));
                }

            } catch (IOException e) {
                System.out.println("CLIENTE: Error al intentar conectar: " + e.getMessage());
            }
            intentos++;
        }
        if(!conectado) {
        	System.out.println("CLIENTE: No se pudo conectar al servidor. Volviendo al menú principal.");
        	end();
        }
        return conectado;
    }

    public void enviarMensajeAlServidor(String msg){
        byte[] data = msg.getBytes();
        DatagramPacket packet = new DatagramPacket(data, data.length, serverIp, SERVER_PORT);
        try {
            socket.send(packet);
//            System.out.println("CLIENTE: Mensaje enviado: " + msg);
        } catch (IOException e) {
        	throw new RuntimeException(e);
        }
    }

    public void end(){
    	synchronized (lock) {
            end = true;
            conectado = true; 
            lock.notify();   
        }
        socket.close();
        System.out.println("Hilo cliente detenido.");
    }

}
