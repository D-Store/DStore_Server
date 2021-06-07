package gg.jominsubyungsin.admin.service.banner;

import gg.jominsubyungsin.admin.domain.dto.banner.datalgnore.ASelectBanner;
import gg.jominsubyungsin.admin.domain.dto.banner.response.BannerListResponse;
import gg.jominsubyungsin.admin.domain.repository.BannerListRepo;
import gg.jominsubyungsin.admin.service.multipart.AdminMultipartService;
import gg.jominsubyungsin.domain.entity.BannerEntity;
import gg.jominsubyungsin.domain.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminBannerServiceImpl implements AdminBannerService {
    private final AdminMultipartService multipartService;

    private final BannerListRepo bannerListRepository;

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

    @Override
    public Response showBannerList(Pageable pageable) {
        BannerListResponse response = new BannerListResponse();
        List<ASelectBanner> bannerList = new ArrayList<>();
        Page<BannerEntity> bannerEntityPage;

        try {
            bannerEntityPage = bannerListRepository.findAll(pageable);

            for (BannerEntity entity : bannerEntityPage) {
                ASelectBanner banner = new ASelectBanner(entity);

                bannerList.add(banner);
            }

            response.setHttpStatus(HttpStatus.OK);
            response.setMessage("배너 보기 성공");
            response.setBannerList(bannerList);
            response.setTotalPages(bannerEntityPage.getTotalPages());

            return response;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public boolean dropBanner(BannerEntity targetBanner) {
        try {
            bannerListRepository.delete(targetBanner);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public BannerEntity isThereBanner(Long id) {
        return bannerListRepository.findById(id)
                .orElseThrow(
                        () -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "없는 배너입니다")
                );
    }
}
