package com.singin.singin.Repository;

import java.util.List;
import java.util.Optional;

import com.singin.singin.Model.User;

import com.singin.singin.Repository.Crud.UserCrudRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author JSBM
 */
@Repository
public class UserRepository {
    //Inicializa e instancia 
    @Autowired
    private UserCrudRepository userCrudRepository;

    //Para scar una lista de usuarios
    //Creo un metodo getAll guardado en una lista del objeto User para listar todos los usuarios
    //getAll se apoya de CrudRepository usando su metodo findAll el cual devuelve todos los registros de una tabla como una coleccion Iterable
    //Basicamente es como un SELECT * FROM User
    public List<User> getAll() {
        //Se hace un casteo a un listado de usuarios (List<User>)
        return (List<User>) userCrudRepository.findAll();
    }

    //Para buscar por id se utiliza el metodo findById() el cual retorna un Optional<>
    //El optional<> contiene el objeto y el empty
    public Optional<User> getUser (int id) {
        return userCrudRepository.findById(id);
    }

    //Para insertar y actualizar se usa el metodo save() del objeto descrito
    public User save(User user) {
        return userCrudRepository.save(user);
    }

    //para validar si existe se usa una consulta que retorna un Optional<> del objeto y valida si es empty
    public boolean existeEmail(String email) {
        Optional<User> usuario = userCrudRepository.findByEmail(email);
        //si la consulta es diferente a un estado empty true retorne el objeto
        //en este caso retorna si la consulta es diferente ! a true, es decir si existe el email
        return !usuario.isEmpty();
    }

    //Para validar si el usuario se puede autenticar se crea un metodo tipo Optional<> el cual resivira dos parametos
    //el email y el password como String y retornara la busqueda de los parametros ingresados si existen.
    public Optional<User> autenticarUsuario(String email, String password) {
        return userCrudRepository.findByEmailAndPassword(email, password);
    }

    // public boolean existeEmail(String email) {
    //     Optional<User> consulta = userCrudRepository.findByEmail(email);
    //     if(!consulta.isEmpty()){
    //         return true;
    //     }
    //     return false;
    // }
}
