package gg.dstore.admin.controller;

import gg.dstore.admin.service.project.AdminPJService;
import gg.dstore.domain.entity.ProjectEntity;
import gg.dstore.domain.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

@RequiredArgsConstructor
@ResponseBody
@RequestMapping("/admin/pj")
@Controller
public class AdminPJController {
    private final AdminPJService adminPJService;

    /**
     * 프로젝트 리스트 받아오기
     */
    @GetMapping("")
    public Response projectList(Pageable pageable) {
        Response result;

        result = getProjectList(pageable);

        return result;
    }

    /**
     * 프로젝트 삭제
     */
    @DeleteMapping("/{id}")
    public Response dropProject(@PathVariable Long id){
        Response result = new Response();

        adminPJService.isThereProject(id);
        try {
            adminPJService.dropProject(id);
        } catch (Exception e) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러");
        }

        result.setHttpStatus(HttpStatus.OK);
        result.setMessage("성공");

        return result;
    }

    @GetMapping("/detail/{id}")
    public Response detailProject(@PathVariable Long id) {
        Response result;

        ProjectEntity thereProject = adminPJService.isThereProject(id);

        try {
            result =  adminPJService.getProjectDetail(thereProject);
        } catch (Exception e) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러");
        }

        return result;
    }

    /**
     * 프로젝트 받아오기
     */
    private Response getProjectList(Pageable pageable) {
        Response result;

        try {
            result = adminPJService.getProjects(pageable);
        } catch (Exception e) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러");
        }

        return result;
    }


}
