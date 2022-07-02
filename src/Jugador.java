import java.util.Scanner;

public class Jugador{
    private String nombre;
    private String[] coorHor = {"A","B","C","D","E","F","G","H"};
    private String[] coorxy = new String[2];
    private Barco[] barcosJugador = new Barco[4];
    private Tablero tableroJugador;
    private Tablero tableroEnemigo;
    Scanner sc = new Scanner(System.in);
    
    public Jugador(String nombre){
        this.nombre = nombre;
        tableroJugador = new Tablero();
        tableroEnemigo = new Tablero();
    }
    
    public String getNombreJugador() {
        return nombre;
    }
    
    public Barco[] getBarcos(){
    	return barcosJugador;
    }
    
    public Tablero getTableroJugador() {
        return tableroJugador;
    }
    
    public Tablero getTableroEnemigo(){
        return tableroEnemigo;
    }
        
    public void colocaBarcos(){
    // Elegimos nº del barco a colocar.
        for (int i = 1; i <= 4; i++) {
            // leemos coordenadas.
            System.out.println(this.nombre + ", introduce coordenadas del barco " + i +" (ej: 2C, 5B):");
            try{
            	coorxy = sc.nextLine().split("");
	            // Traducimos coordenadas de caracter a entero.
	            for(int j = 0; j < coorHor.length; j++){
	                if(coorxy[1].toUpperCase().equals(coorHor[j])){
	                    coorxy[1] = "" + j;
	                }
	            }
	            int coordenadaX = Integer.parseInt(coorxy[0]);
	            int coordenadaY = Integer.parseInt(coorxy[1]);
	            // Comprobamos que coordenadas esten vacias y colocamos barco
	            if (tableroJugador.getTablero()[coordenadaX][coordenadaY].equals("~")){
	            	// Un if para comprobar el tamaño del barco, barcos mas grande de 1 necesitan saber si se
	            	// colocan horizontalmente o verticalmente.
	                if (i > 1) {
	                    // Preguntamos a jugador si quiere posicionar barco horizontal o vertical.
	                    System.out.println("Horizontal o Vertical? H/V");
	                    char orient = sc.nextLine().toUpperCase().charAt(0);
	                    if (orient == 'H') {
	                        // Comprobamos que no se salga del tablero horizontalmente.
	                        if ((coordenadaY + i) > 8) {
	                            System.out.println("No se puede colocar barco fuera del tablero");
	                            i--;
	                        }else {
	                        	barcosJugador[i-1] = new Barco(i);
	                            for (int j = 0; j < i; j++) {
	                                tableroJugador.getTablero()[coordenadaX][coordenadaY++] = "" + i;
	                                this.barcosJugador[i-1].getBarco()[j] = "" + coordenadaX + (coordenadaY-1); 
	                            }
	                        }
	                    }else {
	                        // Comprobamos que no se salga del tablero verticalmente.
	                        if ((coordenadaX + i) > 8) {
	                            System.out.println("No se puede colocar barco fuera del tablero");
	                            i--;
	                        }else {
	                        	barcosJugador[i-1] = new Barco(i);
	                            for (int j = 0; j < i; j++) {
	                                tableroJugador.getTablero()[coordenadaX++][coordenadaY] = "" + i;
	                                this.barcosJugador[i-1].getBarco()[j] = "" + (coordenadaX-1) + coordenadaY; 
	                            }    
	                        }
	                    }
                    // Si barco es de tamaño 1 se crea sin otras comprobaciones.
	                } else {
	                    tableroJugador.getTablero()[coordenadaX][coordenadaY] = "" + i;
	                    barcosJugador[i-1] = new Barco(i);
	                    barcosJugador[i-1].getBarco()[i-1] = "" + coordenadaX + (coordenadaY);
	                }
	            }else{
	                System.out.println("Aqui ya hay un barco");
	                i--;
	                sc.close();
	            }
            } catch (Exception e) {
            	System.out.println(e + "\nCoordenada incorrecta, intentelo de nuevo. Coordenada debe ser"
            			+ " un numero + una letra (ejemplos: 2B, 4C");
            	i--;
            }
            tableroJugador.muestraTablero(this);
        }
    }
    
    public void turnoJugador(Jugador local, Jugador enemigo) {
    	boolean acertado = true;
    	boolean ganado = false;
    	local.tableroEnemigo.muestraTablero(local);
    	do{
    		// leemos coordenadas.
            System.out.println(this.nombre + ", introduce coordenadas del enemigo a hundir (ej: 2C, 5B):");
            try{
            	coorxy = sc.nextLine().split("");
	            // Traducimos coordenadas de caracter a entero.
	            for(int j = 0; j < coorHor.length; j++){
	                if(coorxy[1].toUpperCase().equals(coorHor[j])){
	                    coorxy[1] = "" + j;
	                }
	            }
	            int coordenadaX = Integer.parseInt(coorxy[0]);
	            int coordenadaY = Integer.parseInt(coorxy[1]);
	            
	            // Comprobamos si ha tocado agua.
	            if(enemigo.tableroJugador.getTablero()[coordenadaX][coordenadaY].equals("~")) {
	            	local.tableroEnemigo.getTablero()[coordenadaX][coordenadaY] = "0";
	            	local.tableroEnemigo.muestraTablero(local);
	            	System.out.println("Agua");
	            	acertado = false;
	            	
	            // Si no ha tocado agua comprobamos si hay barco en coordenada.
	            } else {
	            	for(int i = 0; i < 4; i++) {
	            		boolean tocado = (enemigo.getBarcos()[i].compruebaBarcoTocado("" + coordenadaX + coordenadaY));
	            		boolean hundido = false;
	            		// Comprobamos si barco esta tocado y en caso de tocado se iguala trozo de barco con X
	            		// y si esta hundido, con H.
	            		if (tocado) {
	            			local.tableroEnemigo.getTablero()[coordenadaX][coordenadaY] = "X";
	            			hundido = (enemigo.getBarcos()[i].compruebaBarcoHundido(enemigo.barcosJugador[i]));
	            			if(hundido) {
	            				enemigo.getBarcos()[i].setHundido(true);
	            				local.tableroEnemigo.getTablero()[coordenadaX][coordenadaY] = "H";
	            			}
	            			local.tableroEnemigo.muestraTablero(local);
	            			acertado = true;
	            		}
	            		if (tocado && hundido) {
	            			System.out.println("Tocado y hundido!!!");
	            		} else if (tocado) {
	            			System.out.println("Tocado!!!");
	            		}
	            	}
	            }
	    	} catch (Exception e) {
	        	System.out.println(e + "\nCoordenada incorrecta, intentelo de nuevo. Coordenada debe ser"
	        			+ " un numero + una letra (ejemplos: 2B, 4C");
	        }
            ganado = this.compruebaGanado(enemigo);
    	} while (acertado == true && ganado == false);
    }
    
    // Método comprueba si el jugador ha ganado.
    public boolean compruebaGanado(Jugador enemigo) {
    	boolean ganado = false;
    	int barcos = 4;
    	// Comprobamos cuantos barcos se han hundido.
    	for (int i = 0; i < 4; i++) {
    		if (enemigo.getBarcos()[i].getHundido()) {
    			barcos--;
    		}
    	}
    	// Si todos los barcos se han hundido, el jugador gana.
    	if (barcos == 0) {
    		ganado = true;
    		System.out.println(this.nombre + " has ganado!!! Todos los barcos de " + enemigo.getNombreJugador() + 
    				" han sido destruidos");
    	}
    	return ganado;
    }
}