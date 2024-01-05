package org.user;

import org.common.Status;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/persons")
public class UserService {
    UserControl userControl = new UserControl();
    // 查询所有人员信息
    @GetMapping
    public List<User> getAllPersons() {
        System.out.println("getAllPersons");


        return userControl.getAllPersons();
    }

    // 根据 id 查询人员信息
    @GetMapping("/{account}")
    public User getPersonByAccount(@PathVariable String account) {
        System.out.println("getPersonById:" + account);
        return userControl.getPersonByAccount(account);
    }

    // 添加人员信息
    @PostMapping
    public Status addPerson(@RequestBody User user) {
        Status status = userControl.addPerson(user);
//        objectMap.put("person",person);
        return status;
    }

    // 更新人员信息
    @PutMapping("/{account}")
    public Status updatePerson(@PathVariable String account, @RequestBody User updatedUser) {
        System.out.println("updatePerson:" + account);
        return userControl.updatePerson(account, updatedUser);
    }

    // 删除人员信息
    @DeleteMapping("/{account}")
    public Status deletePerson(@PathVariable String account) {
        return userControl.deletePerson(account);
    }
}