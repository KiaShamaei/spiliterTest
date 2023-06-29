package snapp.pay.services;

import snapp.pay.dto.GroupRequestDto;
import snapp.pay.dto.GroupResponseDto;
import snapp.pay.dto.UserResponseDto;
import snapp.pay.entities.Group;
import snapp.pay.entities.User;
import snapp.pay.entities.UserGroup;
import snapp.pay.exceptions.CustomerNotFoundException;
import snapp.pay.exceptions.GroupNameAlreadyExistException;
import snapp.pay.exceptions.GroupNotFoundException;
import snapp.pay.exceptions.GroupWithoutAdminException;
import snapp.pay.repositories.GroupRepository;
import snapp.pay.repositories.UserGroupRepository;
import snapp.pay.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *  GroupService do business of Group
 * @Author Kiarash Shamaei 2023-06-25
 */
@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserGroupRepository userGroupRepository;

    @Transactional
    @Override
    public GroupResponseDto addNewGroup(GroupRequestDto groupRequestDto) throws CustomerNotFoundException {
        if (groupRequestDto.getUserEmails() == null || groupRequestDto.getUserEmails().isEmpty()) {
            throw new GroupWithoutAdminException("User list is empty. Group without admin not possible!!!");
        }
        Optional<Group> gr = groupRepository.findByName(groupRequestDto.getName());
        if(gr.isPresent()){
            throw new GroupNameAlreadyExistException("User group name Already exist please choose an other name ...");
        }
        List<User> members = userRepository.findByEmailIn(groupRequestDto.getUserEmails());
        User admin = members.get(0);

        Group group = Group.builder()
                .name(groupRequestDto.getName())
                .adminUser(admin)
                .build();
        groupRepository.save(group);

        List<UserGroup> userGroupList = new ArrayList<>();
        members.forEach(user -> {
            UserGroup usrGrp = new UserGroup(user);
            usrGrp.setGang(group);
            userGroupRepository.save(usrGrp);
            userGroupList.add(usrGrp);
        });
        group.setUserGroups(userGroupList);
        GroupResponseDto dto = getGroupResponseDto(group);
        return dto;
    }

    private GroupResponseDto getGroupResponseDto(Group group) {

        UserResponseDto adminDto = UserResponseDto.builder()
                .id(group.getAdminUser().getId())
                .name(group.getAdminUser().getName())
                .email(group.getAdminUser().getEmail())
                .contact(group.getAdminUser().getContact())
                .build();

        List<UserResponseDto> userList = group.getUserGroups().stream()
                .map(usrgrp -> UserResponseDto.builder()
                        .id(usrgrp.getUser().getId())
                        .name(usrgrp.getUser().getName())
                        .email(usrgrp.getUser().getEmail())
                        .contact(usrgrp.getUser().getContact())
                        .build())
                .collect(Collectors.toList());

        GroupResponseDto dto = GroupResponseDto.builder()
                .id(group.getId())
                .admin(adminDto)
                .name(group.getName())
                .users(userList)
                .build();
        return dto;
    }

    @Transactional(readOnly = true)
    @Override
    public List<GroupResponseDto> getAllGroups() throws CustomerNotFoundException {
        List<Group> groups = groupRepository.findAll();
        List<GroupResponseDto> groupsList = convertEntityToDto(groups);
        return groupsList;
    }

    private List<GroupResponseDto> convertEntityToDto(List<Group> groups) {
        List<GroupResponseDto> dtos = new ArrayList<>();
        for (Group grp : groups) {
            GroupResponseDto dto = getGroupResponseDto(grp);
            dtos.add(dto);
        }
        return dtos;
    }

    @Transactional
    @Override
    public void addUserToGroup(GroupRequestDto groupRequestDto) throws GroupNotFoundException, CustomerNotFoundException {

        Group grp = groupRepository.findById(groupRequestDto.getId()).get();
        if (grp == null) {
            throw new GroupNotFoundException("Group with id " + groupRequestDto.getId() + " does not exists");
        }
        List<User> userList = userRepository.findByEmailIn(groupRequestDto.getUserEmails());
        if (userList.size() != groupRequestDto.getUserEmails().size()) {
            throw new CustomerNotFoundException("Some customers does not exists");
        }

        // add users to database
        List<UserGroup> userGroupList = new ArrayList<>();
        for (User usr : userList) {
            UserGroup userGroup = UserGroup.builder()
                    .gang(grp)
                    .user(usr)
                    .build();
            userGroupList.add(userGroup);
        }
        userGroupRepository.saveAll(userGroupList);
    }
}
