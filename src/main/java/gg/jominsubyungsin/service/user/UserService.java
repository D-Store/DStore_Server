package gg.jominsubyungsin.service.user;

import gg.jominsubyungsin.domain.dto.user.dataIgnore.SelectUserDto;
import gg.jominsubyungsin.domain.dto.user.request.UserDto;
import gg.jominsubyungsin.domain.dto.user.request.UserUpdateDto;

import gg.jominsubyungsin.domain.dto.user.response.UserDetailResponseDto;
import gg.jominsubyungsin.domain.entity.UserEntity;


import org.springframework.data.domain.Pageable;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

public interface UserService {

	boolean userUpdate(UserUpdateDto userDto) throws HttpServerErrorException;

	boolean userDelete(UserDto userDto);

	boolean userUpdateIntroduce(UserDto userDto);

	SelectUserDto findUser(Long id, UserEntity user);

	UserEntity findUserById(Long id);

	UserEntity findUser(String email);

	List<SelectUserDto> findUserLikeName(String name, String email, UserEntity user);

	boolean checkUserSame(String email, Long id);

	void updateProfileImage(String email, String fileUrl);

	UserDetailResponseDto getUserDetail(Long id, UserEntity user, Pageable pageable);
}
