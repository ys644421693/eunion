package com.eunion.manage.serviceimpl.role;

import com.eunion.manage.entity.sysrole.CheckInfo;
import com.eunion.manage.repository.role.CheckInfoRepository;
import com.eunion.manage.service.role.CheckInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yangshuo on 2016/11/22.
 */
@Service
public class CheckInfoServiceImpl implements CheckInfoService {

    @Resource
    private CheckInfoRepository checkInfoRepository;

    @Override
    public List<CheckInfo> getAllCheckInfo() {
        return (List<CheckInfo>) checkInfoRepository.findAll();
    }

    @Override
    public Object saveCheckInfo(CheckInfo checkInfo) {
        return checkInfoRepository.save(checkInfo);
    }

    @Override
    public void deleteCheckInfo(CheckInfo checkInfo) {
        checkInfoRepository.delete(checkInfo);
    }
}
