package gg.jominsubyungsin.admin.service.multipart;

import org.springframework.web.multipart.MultipartFile;

public interface AdminMultipartService {
    String uploadFile(MultipartFile file);
}
