package com.example.mobilelele.model.mapper;

import com.example.mobilelele.model.dto.UserRegisterDTO;
import com.example.mobilelele.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "active", constant = "true")
    User userDtoToUserEntity(UserRegisterDTO userRegisterDTO);
}
