package Service;

import Entity.UserEntity;
import Repository.UserRepository;
import dto.UserDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    public UserDTO getUserById(String userId) {
        Optional<UserEntity> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            UserEntity userEntity = userOptional.get();
            return modelMapper.map(userEntity, UserDTO.class);
        }
        return null;
    }
}
