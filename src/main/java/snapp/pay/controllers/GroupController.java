package snapp.pay.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import snapp.pay.dto.GroupRequestDto;
import snapp.pay.dto.GroupResponseDto;
import snapp.pay.entities.Group;
import snapp.pay.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * GroupController handle group request
 * @Author Kiarash Shamaei 2023-06-25
 */

@RestController
@RequestMapping(value = "/api/v1")
@Tag(name = "Group Api")
public class GroupController {

    @Autowired
    private GroupService groupServiceImpl;



    @PostMapping(value = "/group")
    @Operation(summary = "this method make a group " ,description = "ok status")
    public ResponseEntity<GroupResponseDto> createNewGroup(@RequestBody GroupRequestDto json) {

        GroupResponseDto group = groupServiceImpl.addNewGroup(json);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @GetMapping(value = "/group")
    @Operation(summary = "this method return a list off all groups" ,description = "List<GroupResponseDto>")
    public ResponseEntity<List<GroupResponseDto>> getGroups() {

        List<GroupResponseDto> groupList = groupServiceImpl.getAllGroups();
        return new ResponseEntity<>(groupList, HttpStatus.OK);
    }

    @PutMapping(value = "/group/addUsers")
    @Operation(summary = "this method add a user to a group" ,description = "Group")
    public ResponseEntity<Group> addUsersToGroup(@RequestBody GroupRequestDto groupRequestDto) {

        groupServiceImpl.addUserToGroup(groupRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
