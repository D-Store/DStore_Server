package gg.jominsubyungsin.admin.service.banner;

import org.springframework.web.multipart.MultipartFile;

public interface AdminBannerService {
    boolean bannerUpload(MultipartFile file);
}
