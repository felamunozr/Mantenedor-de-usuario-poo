package com.munoz.felipe.dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UserDao {
    public static final String NOMBRE_FILE = "users.json";

    private final Path archivo;

    public UserDao() {
        String path = System.getProperty("user.home");
        this.archivo = Paths.get(path, NOMBRE_FILE);
    }

    // Guardar archivo
    public void guardarDato(String nombre) throws IOException {
        try {
            Files.writeString(this.archivo, nombre);
        } catch(IOException e){
            System.out.println("No se pudo guardar el dato: " + nombre);
        }
    }

    // Leer archivo
    public String leerDato(){
        String resultado = "";
        if (!Files.exists(this.archivo)){ return ""; }

        try {
            resultado = Files.readString(this.archivo);
        } catch(IOException e){
            System.out.println("No se pudo leer el archivo");
        }
        return resultado;

    }
}
