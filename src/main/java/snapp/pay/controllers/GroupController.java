package snapp.pay.controllers;

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
public class GroupController extends AbstractController {

    @Autowired
    private GroupService groupServiceImpl;

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView model = new ModelAndView("HelloWorldPage");
        model.addObject("msg", "hello world");
        return model;
    }

    @PostMapping(value = "")
    public ResponseEntity<GroupResponseDto> createNewGroup(@RequestBody GroupRequestDto json) {

        GroupResponseDto group = groupServiceImpl.addNewGroup(json);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<List<GroupResponseDto>> getGroups() {

        List<GroupResponseDto> groupList = groupServiceImpl.getAllGroups();
        return new ResponseEntity<>(groupList, HttpStatus.OK);
    }

    @PutMapping(value = "/addUsers")
    public ResponseEntity<Group> addUsersToGroup(@RequestBody GroupRequestDto groupRequestDto) {

        groupServiceImpl.addUserToGroup(groupRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
