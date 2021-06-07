package gg.jominsubyungsin.admin.service.banner;

import gg.jominsubyungsin.domain.entity.BannerEntity;
import gg.jominsubyungsin.domain.response.Response;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface AdminBannerService {
    boolean bannerUpload(MultipartFile file);

    Response showBannerList(Pageable pageable);

    boolean dropBanner(BannerEntity targetBanner);

    BannerEntity isThereBanner(Long id);
}
