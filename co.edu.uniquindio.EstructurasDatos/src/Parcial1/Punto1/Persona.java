package Parcial1.Punto1;

public class Persona {
    // Los datos miembro hazlos siempre privados
    // Inicializa siempre los datos miembro
    private String Nombre = "";
    private String Sexo = "";
    private int Edad = 0;

    Persona (String Nombre, String Sexo, int edad){
        this.Nombre = Nombre;
        this.Sexo = Sexo;
        this.Edad = edad;
    }

    public String getNombre() {
        return Nombre;
    }    

    public void setNombre(String nombre) {
        Nombre = nombre;
    }    

    public String getSexo() {
        return Sexo;
    }    

    public void setSexo(String sexo) {
        Sexo = sexo;
    }    

    public int getEdad() {
        return Edad;
    }    

    public void setEdad(int edad) {
        Edad = edad;
    }       
}