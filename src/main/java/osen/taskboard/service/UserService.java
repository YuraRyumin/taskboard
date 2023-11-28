package osen.taskboard.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import osen.taskboard.domain.Role;
import osen.taskboard.domain.User;
import osen.taskboard.dto.UserDTO;
import osen.taskboard.repo.RoleRepo;
import osen.taskboard.repo.UserRepo;

import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Transactional(readOnly = true)
@Service
public class UserService implements UserDetailsService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    public UserService(UserRepo userRepo, RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    public UserDTO getEmptyDTO(){
        UserDTO userDTO = new UserDTO();
        userDTO.setLogin("");
        userDTO.setRole("");
        userDTO.setPhone("");
        userDTO.setEmail("");
        userDTO.setUuid("");
        userDTO.setActivationCode("");
        userDTO.setActive(false);
        return userDTO;
    }

    public UserDTO convertEntityToDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setLogin(user.getLogin());
        userDTO.setRole(user.getRole().getName());
        userDTO.setPhone(user.getPhone());
        userDTO.setEmail(user.getEmail());
        userDTO.setUuid(user.getUuid());
        userDTO.setActivationCode(user.getActivationCode());
        userDTO.setActive(user.isActive());
        return userDTO;
    }

    public Iterable<UserDTO> convertAllEntitysToDTO(Iterable<User> users){
        return StreamSupport.stream(users.spliterator(), false).
                map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    @Transactional
    public void saveUser(User user){
        User thisUser = userRepo.findFirstByUuid(user.getUuid());
        if(thisUser == null){
            thisUser = new User();
        }
        thisUser.setUuid(user.getUuid());
        thisUser.setEmail(user.getEmail());
        thisUser.setPhone(user.getPhone());
        if(user.getRole() != null) {
            Role role = roleRepo.findFirstByName(user.getRole().getName());
            if (role != null) {
                thisUser.setRole(role);
            }
        }
        thisUser.setLogin(user.getLogin());
        thisUser.setActive(false);
        if(user.getActivationCode().isEmpty()){
            thisUser.setActivationCode(UUID.randomUUID().toString());
        } else {
            thisUser.setActivationCode(user.getActivationCode());
        }
        userRepo.save(thisUser);
    }

    @Transactional
    public void saveUser(String uuid, String email,
                         String phone, String roleName,
                         String login, String password,
                         String activationCode){
        User user = new User();
        user.setUuid(uuid);
        user.setEmail(email);
        user.setPhone(phone);
        Role role = roleRepo.findFirstByName(roleName);
        if(role != null){
            user.setRole(role);
        }
        user.setLogin(login);
        user.setActive(false);
        if(activationCode.isEmpty()){
            user.setActivationCode(UUID.randomUUID().toString());
        } else {
            user.setActivationCode(activationCode);
        }
        userRepo.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findFirstByLogin(username);
    }
}
