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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/group")
@Tag(name = "Group Api")
public class GroupController {

    @Autowired
    private GroupService groupServiceImpl;



    @PostMapping(value = "")
    @Operation(summary = "this mehtod make a group of bill" ,description = "ok status")
    public ResponseEntity<GroupResponseDto> createNewGroup(@RequestBody GroupRequestDto json) {

        GroupResponseDto group = groupServiceImpl.addNewGroup(json);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @GetMapping(value = "")
    @Operation(summary = "this mehtod return a list off all group bill" ,description = "List<GroupResponseDto>")
    public ResponseEntity<List<GroupResponseDto>> getGroups() {

        List<GroupResponseDto> groupList = groupServiceImpl.getAllGroups();
        return new ResponseEntity<>(groupList, HttpStatus.OK);
    }

    @PutMapping(value = "/addUsers")
    @Operation(summary = "this mehtod add a user to a group of bill" ,description = "Group")
    public ResponseEntity<Group> addUsersToGroup(@RequestBody GroupRequestDto groupRequestDto) {

        groupServiceImpl.addUserToGroup(groupRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
