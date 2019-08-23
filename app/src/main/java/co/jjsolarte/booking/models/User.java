package co.jjsolarte.booking.models;

import io.realm.RealmObject;

public class User extends RealmObject {

    private String nombre;
    private String correo;
    private int telefono;
    private int cuenta;
    private String contraseñ;

    public User() {
    }

    public User(String nombre, String correo, int telefono, int cuenta, String contraseñ) {
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.cuenta = cuenta;
        this.contraseñ = contraseñ;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public int getTelefono() {
        return telefono;
    }
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
    public int getCuenta() {
        return cuenta;
    }
    public void setCuenta(int cuenta) {
        this.cuenta = cuenta;
    }
    public String getContraseñ() {
        return contraseñ;
    }
    public void setContraseñ(String contraseñ) {
        this.contraseñ = contraseñ;
    }
}
