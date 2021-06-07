package gg.jominsubyungsin.admin.service.banner;

import gg.jominsubyungsin.admin.domain.repository.BannerListRepository;
import gg.jominsubyungsin.admin.service.multipart.AdminMultipartService;
import gg.jominsubyungsin.domain.entity.BannerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class AdminBannerServiceImpl implements AdminBannerService{
    private final AdminMultipartService multipartService;

    private final BannerListRepository bannerListRepository;

    @Override
    public boolean bannerUpload(MultipartFile file) {
        String url = multipartService.uploadFile(file);
        try {
        BannerEntity bannerEntity = new BannerEntity();
        bannerEntity.setFileLocation(url);
        bannerListRepository.save(bannerEntity);
        } catch (Exception e) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러");
        }
        return true;
    }
}
