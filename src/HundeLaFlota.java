import java.util.Scanner;

public class HundeLaFlota{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        // Mostramos mensaje de bienvenida.
        System.out.println("BIENVENIDO AL HUNDE LA FLOTA\n\n");
        System.out.println("REGLAS BASICAS DEL JUEGO:\nEn un tablero colocas tus barcos que son de diferentes "
                + "tamaños, y entre ellos siempre debe haber agua alrededor,\nes decir, dos barquitos no pueden"
                + " ir pegados, y luego hay que intentar hundir los barcos de tu enemigo.\n\n");
        System.out.println("Jugamos?\n\n");
        System.out.println("Introduce nombre jugador 1: ");
        
        // Creamos jugadores y tableros
        Jugador jugador1 = new Jugador(sc.nextLine());
        System.out.println("Introduce nombre jugador 2: ");
        Jugador jugador2 = new Jugador(sc.nextLine());
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        
        // Mostramos tableros y cada jugador coloca sus barcos.
        jugador1.getTableroJugador().muestraTablero(jugador1);
        jugador1.colocaBarcos();
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        jugador2.getTableroJugador().muestraTablero(jugador2);
        jugador2.colocaBarcos();
        boolean jugador1ganado = false;
        boolean jugador2ganado = false;
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        
        // Iniciamos bucle principal del juego.
        do {
        	// Turno jugador 1
            System.out.println("Es tu turno " + jugador1.getNombreJugador() + "\n");
            jugador1.turnoJugador(jugador1, jugador2);
            jugador1ganado = jugador1.compruebaGanado(jugador2);
            if (jugador1ganado == true) {
                break;
            }
            // Turno jugador 2
            System.out.println("Es tu turno " + jugador2.getNombreJugador() + "\n");
            jugador2.turnoJugador(jugador2, jugador1);
            jugador2ganado = jugador2.compruebaGanado(jugador1);
            if (jugador2ganado == true) {
                break;
            }
        } while ( jugador1ganado == false && jugador2ganado == false);
        sc.close();
    }
}