package gg.dstore.admin.domain.dto.banner.response;

import gg.dstore.admin.domain.dto.banner.datalgnore.ASelectBanner;
import gg.dstore.domain.response.Response;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BannerListResponse extends Response {
    private List<ASelectBanner> bannerList;
}
