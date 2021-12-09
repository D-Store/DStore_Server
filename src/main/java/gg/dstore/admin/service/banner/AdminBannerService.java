package gg.dstore.admin.service.banner;

import gg.dstore.domain.entity.BannerEntity;
import gg.dstore.domain.response.Response;
import org.springframework.web.multipart.MultipartFile;

public interface AdminBannerService {
    boolean bannerUpload(MultipartFile file);

    Response showBannerList();

    boolean dropBanner(BannerEntity targetBanner);

    BannerEntity isThereBanner(Long id);
}
