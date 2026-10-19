package com.munoz.felipe.model;

import com.munoz.felipe.dto.UserDto;

import java.util.ArrayList;

public class UserModel {
    private static final ArrayList<UserDto> usuarios =  new ArrayList<>();

    public static ArrayList<UserDto> getUsuarios(){
        return usuarios;
    }
    public static void agregarUsuario(UserDto user){usuarios.add(user);}

    public static void eliminarUsuario(UserDto user){usuarios.remove(user);}
}
