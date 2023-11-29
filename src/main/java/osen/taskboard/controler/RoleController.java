package osen.taskboard.controler;

import org.springframework.web.bind.annotation.*;
import osen.taskboard.domain.Role;
import osen.taskboard.dto.RoleDTO;
import osen.taskboard.service.RoleService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/roleslist")
    public Iterable<RoleDTO> listOfRoles(){
        return roleService.getAllRoles();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/createrole")
    public void createRole(@RequestBody Role role){
        roleService.saveRole(role);
    }

    @PostMapping("/saverole")
    public void saveRole(@RequestBody Role role){
        roleService.saveRole(role);
    }
}
