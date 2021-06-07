package gg.jominsubyungsin.admin.controller;

import gg.jominsubyungsin.admin.service.banner.AdminBannerService;
import gg.jominsubyungsin.domain.response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
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
        Response result =new Response();

        log.info("POST  /admin/banner");
        bannerService.bannerUpload(file);

        result.setHttpStatus(HttpStatus.OK);
        result.setMessage("배너 저장 성공");

        return result;
    }
}
