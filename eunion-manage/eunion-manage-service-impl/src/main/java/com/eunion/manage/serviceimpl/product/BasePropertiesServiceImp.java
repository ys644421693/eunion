package com.eunion.manage.serviceimpl.product;

import com.eunion.manage.dto.BaseResponse;
import com.eunion.manage.entity.product.BaseProperties;
import com.eunion.manage.repository.BasePropertiesRepository;
import com.eunion.manage.service.product.BasePropertiesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yangshuo on 2016/11/14.
 */
@Service
public class BasePropertiesServiceImp implements BasePropertiesService {

    @Resource
    private BasePropertiesRepository basePropertiesRepository;

    @Override
    public List<BaseProperties> getAllBaseProperties() {
        return basePropertiesRepository.findAll();
    }

    @Override
    public Object addProperties(BaseProperties baseProperties) {
        return basePropertiesRepository.save(baseProperties);
    }

    @Override
    public Object deleteProperties(BaseProperties baseProperties) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMsg("删除成功！");
        baseResponse.setStatus("000000");
        try {
            basePropertiesRepository.delete(baseProperties);
        }catch (Exception e){
            baseResponse.setMsg("删除失败，此属性有其他商品使用，无法删除！");
            baseResponse.setStatus("000001");
        }
        return baseResponse;
    }
}
