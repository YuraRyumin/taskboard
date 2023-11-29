package osen.taskboard.controler;

import org.springframework.web.bind.annotation.*;
import osen.taskboard.domain.User;
import osen.taskboard.service.UserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping("/userslist")
//    public Iterable<UserDTO> listOfUsers(){
//        return userService.getEmptyDTO()
//    }

    @RequestMapping(method = RequestMethod.POST, value = "/createuser")
    public void createUser(@RequestBody User user){
        userService.saveUser(user);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveuser")
    public void saveUser(@RequestBody User user){
        userService.saveUser(user);
    }
}
