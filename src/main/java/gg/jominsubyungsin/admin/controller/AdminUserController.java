package gg.jominsubyungsin.admin.controller;

import gg.jominsubyungsin.admin.domain.dto.user.dataIgnore.ASelectUserDto;
import gg.jominsubyungsin.admin.enums.SearchMode;
import gg.jominsubyungsin.admin.service.user.AdminUserService;
import gg.jominsubyungsin.domain.entity.UserEntity;
import gg.jominsubyungsin.domain.response.Response;
import gg.jominsubyungsin.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@ResponseBody
@RequestMapping("/admin/user")
@Controller
public class AdminUserController {
    private final AdminUserService adminUserService;

    /**
     * 일반 유저 받아오기
     */
    @GetMapping("/general")
    public Response getUser(Pageable pageable) {
        Response result;

        result = findUserByRole(pageable, Role.USER);

        return result;
    }

    /**
     * 어드민 유저 받아오기
     */
    @GetMapping("/adminUser")
    public Response getAdmin(Pageable pageable) {
        Response result;

        result = findUserByRole(pageable, Role.ADMIN);

        return result;
    }

    /**
     * 유저 삭제
     */
    @DeleteMapping("/{id}")
    public Response dropUser(@PathVariable Long id) {
        Response result = new Response();

        adminUserService.isThereUser(id);
        try {
            adminUserService.dropUser(id);
        } catch (Exception e) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "없는 유저 입니다.");
        }

        result.setHttpStatus(HttpStatus.OK);
        result.setMessage("유저 삭제 성공");

        return result;
    }

    /**
     * 유저 권한 추가
     */
    @PutMapping("/permission/{id}")
    public Response addPer(@PathVariable Long id) {
        Response result = new Response();

        UserEntity target = adminUserService.isThereUser(id);
        try {
            adminUserService.addPerUser(target);
        } catch (Exception e) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러");
        }

        result.setHttpStatus(HttpStatus.OK);
        result.setMessage("권한 추가 성공");

        return result;
    }

    /**
     * 유저 권한 삭제
     */
    @DeleteMapping("/permission/{id}")
    public Response dropPer(@PathVariable Long id) {
        Response result = new Response();

        UserEntity target = adminUserService.isThereUser(id);
        try {
            adminUserService.delPerUser(target);
        } catch (Exception e) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러");
        }

        result.setHttpStatus(HttpStatus.OK);
        result.setMessage("권한 제거 성공");

        return result;
    }

    /**
     * 유저 검색
     */
    @GetMapping("/search")
    public Response searchUser(Pageable pageable, HttpServletRequest request) {
        Response result;
        Role role;
        SearchMode mode;

        try {
            role = Role.valueOf(request.getParameter("r"));
            mode = SearchMode.valueOf(request.getParameter("m"));
        } catch (IllegalArgumentException e) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "잘못 된 요청");
        }
        String keyword = request.getParameter("keyword");

        result = searchUserByMode(pageable, role, mode, keyword);

        return result;
    }

    /**
     * 역할로 유저 찾기
     */
    private Response findUserByRole(Pageable pageable, Role role) {

        Response result;

        try {
            result = adminUserService.getUserList(pageable, role);
        } catch (Exception e) {
            throw e;
        }

        return result;
    }

    /**
     * 유저 검색
     */
    private Response searchUserByMode(Pageable pageable, Role role,
                                      SearchMode mode, String keyword) {

        Response result;

        try {
            result = adminUserService.searchUser(pageable, role, mode, keyword);
        } catch (Exception e) {
            throw e;
        }

        return result;
    }
}
