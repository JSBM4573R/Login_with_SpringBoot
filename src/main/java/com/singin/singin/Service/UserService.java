package com.singin.singin.Service;

import java.util.List;
import java.util.Optional;

import com.singin.singin.Model.User;
import com.singin.singin.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    //Inicializa e instancia
    @Autowired
    private UserRepository userRepository;

    //Para listar todos los usuarios
    public List<User> getAll() {
        return userRepository.getAll();
    }

    //Para buscar un usuario por el id
    public Optional<User> getUser(int id) {
        return userRepository.getUser(id);
    }

    //Para registrar un usuario creo un metodo de la clase User
    public User registrar(User user) {
        //si el id del usuario no existe es igual a null
        if (user.getId() == null) {
            //si no existe el email entonces guarde el objeto que recibe con el metodo save()
            if (existeEmail(user.getEmail()) == false) {
                return userRepository.save(user);
            } else {
                //si existe el email no hace nada retorna el objeto, retorna la misma informacion que recibio
                return user;
            }
        } else {
            //si existe el Id o el Id es nulo retorne el objeto 
            return user;
        }
    }

    //Se usa el mismo metodo creado en el Repository
    public boolean existeEmail(String email) {
        return userRepository.existeEmail(email);
    }

    //Para autenticar
    public User autenticarUsuario(String email, String password) {
        //valido la combinacion ingresada como parametros email y password 
        //dentro de un Optional<> guardado en la variable usuario 
        Optional<User> usuario = userRepository.autenticarUsuario(email, password);
        if (usuario.isEmpty()) {
            //Si el usuario es vacio no existe la combinacion usuario y contraseña 
            //retorna un nuevo objeto User con su email, password y un string "NO DEFINIDO"
            return new User(email, password, "No Definido");
        } else {
            //si el usuario no es vacio, es decir, si existe entonces traiga el objeto
            return usuario.get();
        }
    }


    
}
