public class Barco{
    private int size;
    private String[] barco;
    private boolean hundido = false;
    
    // Creamos barco y le asignamos un nÃºmero.
    public Barco(int size){
        barco = new String[size];
        this.size = size;
        for(int i = 0; i < size; i++){
            barco[i] = ""; 
        }
    }
    
    public String[] getBarco(){
        return barco;
    }
    
    public void setHundido(boolean hundido) {
    	this.hundido = hundido;
    }
    
    public boolean getHundido() {
    	return hundido;
    }
    
    // Comprobar estado barco.
    public boolean compruebaBarcoTocado(String coordenadas){
    	boolean acertado = false;
    	// Comprobamos si coinciden las coordenadas con alguna posicion del barco.
    	for(int i = 0; i < this.size; i++){
            if (this.barco[i].equals(coordenadas)){
                this.barco[i] = "H";
                acertado = true;
            }
        }
    	
        
        
        return acertado;
    }
    
    public boolean compruebaBarcoHundido(Barco barco) {
    	boolean hundido = false;
    	int estado = this.size;
    	// Comprobamos si el barco esta hundido. 
        for(int i = 0; i < this.size; i++) {
        	if (this.barco[i].equals("H")) {
        		estado--;
        	}
        }
        
        if (estado == 0){
            hundido = true;
        }
        return hundido;
    }
}