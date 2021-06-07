package gg.jominsubyungsin.admin.service.multipart;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@Service
public class AdminMultipartServiceImpl implements AdminMultipartService{

    private final Path bannerStorageLocation = Paths.get("static/banner/").toAbsolutePath().normalize();
    @Value("${server.get.url}")
    String server;

    @Override
    public String uploadFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "파일이 비었음");
        }
        String fileName = StringUtils.cleanPath(UUID.randomUUID().toString() + "-" + Objects.requireNonNull(file.getOriginalFilename()));
        try {
            if (fileName.contains("..")) {
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "파일 이름 오류");
            }

            String type = fileName.substring(fileName.lastIndexOf(".") + 1);
            File newFile;
            // 파일 타입
            if(type.equals("jpeg") || type.equals("jpg") || type.equals("png")) {
                //저장할 파일 위치
                Path pathName = bannerStorageLocation.resolve(fileName);

                //파일 생성
                newFile = new File(pathName.toString());

            } else {
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "지원하지 않는 형식입니다");
            }

            if(newFile.createNewFile()) {
                return "Http://" + server + "/file/banner" + fileName;
            } else {
                throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "저장 실패");
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러");
        }
    }
}
