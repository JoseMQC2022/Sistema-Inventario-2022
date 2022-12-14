
package Formularios;

public class ValorCodigos {
    
    private int dato;
    private int contador=1;
    private String numero="";

    public void generar(int dato) {
        this.dato = dato;
        if((this.dato>=100) || (this.dato<1000)){
            int can=contador+this.dato;
            numero = "0" + can; 
        }
        if((this.dato>=9) || (this.dato<100)){
            int can=contador+this.dato;
            numero = "00" + can;
        }
    }

    public String serie(){
        return this.numero;
    }
}
