package snapp.pay.services;


import  snapp.pay.dto.GroupRequestDto;
import  snapp.pay.dto.GroupResponseDto;

import java.util.List;


/**
 *
 * @Author Kiarash Shamaei 2023-06-25
 */

public interface GroupService {

    GroupResponseDto addNewGroup(GroupRequestDto groupRequestDto);

    List<GroupResponseDto> getAllGroups();

    void addUserToGroup(GroupRequestDto groupRequestDto);
}
