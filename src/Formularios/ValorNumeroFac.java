
package Formularios;

public class ValorNumeroFac {
    
    private int dato;
    private int contador_fac=1;
    private String numero_fac="";

    public void generar(int dato) {
        this.dato = dato;
        if((this.dato>=1000000) || (this.dato<10000000)){
            int can=contador_fac+this.dato;
            numero_fac = "" + can; 
        }
        if((this.dato>=100000) || (this.dato<1000000)){
            int can=contador_fac+this.dato;
            numero_fac = "0" + can; 
        }
        if((this.dato>=10000) || (this.dato<100000)){
            int can=contador_fac+this.dato;
            numero_fac = "00" + can; 
        }
        if((this.dato>=1000) || (this.dato<10000)){
            int can=contador_fac+this.dato;
            numero_fac = "000" + can; 
        }
        if((this.dato>=100) || (this.dato<1000)){
            int can=contador_fac+this.dato;
            numero_fac = "0000" + can; 
        }
        if((this.dato>=9) || (this.dato<100)){
            int can=contador_fac+this.dato;
            numero_fac = "00000" + can;
        }
    }

    public String serie_fac(){
        return this.numero_fac;
    }
}
