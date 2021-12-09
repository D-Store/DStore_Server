package gg.dstore.admin.controller;

import gg.dstore.admin.service.banner.AdminBannerService;
import gg.dstore.domain.entity.BannerEntity;
import gg.dstore.domain.response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@ResponseBody
@RequestMapping("/admin/banner")
@Slf4j
@Controller
public class AdminBannerController {
    private final AdminBannerService bannerService;

    @PostMapping("")
    public Response bannerUpload(MultipartFile file) {
        Response result = new Response();

        log.info("POST  /admin/banner");
        bannerService.bannerUpload(file);

        result.setHttpStatus(HttpStatus.OK);
        result.setMessage("배너 저장 성공");

        return result;
    }

    @GetMapping("")
    public Response getBanners() {
        Response result;

        try {
            result = bannerService.showBannerList();
        } catch (Exception e) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러");
        }

        return result;
    }

    @DeleteMapping("/{id}")
    public Response dropBanner(@PathVariable Long id) {
        Response result = new Response();
        BannerEntity banner = bannerService.isThereBanner(id);

        try {
            bannerService.dropBanner(banner);
        } catch (Exception e) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러");
        }

        result.setHttpStatus(HttpStatus.OK);
        result.setMessage("배너 삭제 성공");

        return result;
    }
}
