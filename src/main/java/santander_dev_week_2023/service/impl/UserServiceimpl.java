package santander_dev_week_2023.service.impl;

import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;
import santander_dev_week_2023.model.User;
import santander_dev_week_2023.repository.UserRepository;
import santander_dev_week_2023.service.UserService;

import java.util.NoSuchElementException;

@Service

public class UserServiceimpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceimpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }
    @Override
    public User create(@NonNull User userToCreate){
        if (userRepository.existsByAccountNumber(userToCreate.getAccount().getNumber())){
            throw new IllegalArgumentException("This Account number already exists");
        }
        return userRepository.save(userToCreate);
    }
}
