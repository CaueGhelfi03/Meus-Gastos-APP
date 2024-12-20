package com.example.meus_gastos.services;

import com.example.meus_gastos.DTOs.User.UserUpdateDTO;
import com.example.meus_gastos.domain.User.UserEntity;
import com.example.meus_gastos.mapper.User.UserMap;
import com.example.meus_gastos.repositories.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserMap userMap;

    public UserEntity createUser(UserEntity user) throws Exception{
        if(repository.existsById(user.getId())) throw new ResponseStatusException(HttpStatus.CONFLICT);

        repository.save(user);
        return user;
    }

    public UserEntity findById(Long id){
        Optional<UserEntity> data = repository.findById(id);
        if(data.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return data.get();
    }

    public List<UserEntity> findAll(){
        List<UserEntity> users = repository.findAll();
        return users;
    }

    public UserUpdateDTO updateUser(Long id, UserUpdateDTO user) throws Exception{
        Optional<UserEntity> userReturn = repository.findById(id);
        if(userReturn.isPresent()){
            repository.save(userReturn.get());
            return userMap.toUpdateUserDTO(userReturn.get());
        }


        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }


    public void deleteUser(Long id) {
        if(!repository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        repository.deleteById(id);
    }
}
