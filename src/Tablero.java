

public class Tablero{
    private int size = 8;
    private String[][] tablero;
    
    // Creamos tablero.
    public Tablero(){
        tablero = new String[size][size];
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                tablero[i][j] = "~";
            }
        }
    }
    
    public String[][] getTablero(){
        return tablero;
    }
    
    // Imprimimos tablero en pantalla.
    public void muestraTablero(Jugador jugador){
        System.out.println("  A B C D E F G H");
        for (int i = 0; i < tablero.length; i++) {
            System.out.print(i);
            for (int j = 0; j < tablero.length; j++) {
                System.out.print(" " + tablero[i][j]);
            }
            System.out.print("\n");
        }
    }
}