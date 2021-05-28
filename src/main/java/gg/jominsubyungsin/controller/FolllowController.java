package gg.jominsubyungsin.controller;

import gg.jominsubyungsin.domain.dto.user.dataIgnore.SelectUserDto;
import gg.jominsubyungsin.domain.entity.UserEntity;
import gg.jominsubyungsin.domain.dto.user.response.ShowUserListResponse;
import gg.jominsubyungsin.domain.response.Response;
import gg.jominsubyungsin.lib.PageEnd;
import gg.jominsubyungsin.service.follow.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/follow")
public class FolllowController {
	private final FollowService followService;
	private final PageEnd pageEnd;

	@PutMapping("/{userId}")
	public Response follow(HttpServletRequest request, @PathVariable Long userId) {
		Response response = new Response();
		UserEntity user = (UserEntity) request.getAttribute("user");
		if(user == null){
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "토큰이 필요함");
		}
		try {
			followService.ChangeFollowState(user, userId);

			response.setHttpStatus(HttpStatus.OK);
			response.setMessage("성공");
			return response;
		} catch (Exception e) {
			throw e;
		}
	}
	/*
	 * 팔로워 리스트
	 */
	@GetMapping("/followers/{userId}")
	public ShowUserListResponse showFollowers(HttpServletRequest request, @PathVariable Long userId, Pageable pageable) {
		ShowUserListResponse response = new ShowUserListResponse();
		UserEntity user = (UserEntity) request.getAttribute("user");
		try {
			List<SelectUserDto> users = followService.showFollower(userId, user, pageable);
			response.setUserList(users);
			Long followers = followService.countFollower(userId);

			response.setEnd(false);
			if (followers <= (long) (pageable.getPageNumber() + 1) * pageable.getPageSize()) {
				response.setEnd(true);
			}
			response.setHttpStatus(HttpStatus.OK);
			response.setMessage("성공");

			return response;
		} catch (Exception e) {
			throw e;
		}
	}

	/*
	 *팔로잉 리스트
	 */
	@GetMapping("/followings/{userId}")
	public ShowUserListResponse showFollowing(HttpServletRequest request, @PathVariable Long userId, Pageable pageable) {
		ShowUserListResponse response = new ShowUserListResponse();
		UserEntity user = (UserEntity) request.getAttribute("user");
		try {
			List<SelectUserDto> users = followService.showFollowing(userId, user, pageable);
			response.setUserList(users);
			Long followings = followService.countFollowing(userId);

			Boolean end = pageEnd.pageEnd(pageable.getPageSize(), pageable.getPageNumber(), followings);

			response.setEnd(end);
			response.setHttpStatus(HttpStatus.OK);
			response.setMessage("성공");

			return response;
		} catch (Exception e) {
			throw e;
		}
	}
}
