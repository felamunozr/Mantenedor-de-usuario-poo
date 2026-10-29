package com.munoz.felipe.dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.munoz.felipe.dto.UserDto;

public class UserDao {
    public static final String NOMBRE_FILE = "users.json";
    private final Path archivo;

    private static Gson gson;

    public UserDao() {
        archivo = Path.of(System.getProperty("user.home"), NOMBRE_FILE);
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    // Guardar archivo
    public void guardar(ArrayList<UserDto> heroes) throws IOException {
        try {
            String datos = gson.toJson(heroes);
            Files.writeString(archivo, datos);
        } catch(IOException e){
            System.out.println("No se pudo guardar el dato: " + e);
        }
    }

    // Leer archivo
    public String leer(){
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
