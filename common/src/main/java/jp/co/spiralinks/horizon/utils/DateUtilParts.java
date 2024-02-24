package jp.co.spiralinks.horizon.utils;

import jp.co.spiralinks.horizon.dao.MstDateDao;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
@Getter
public class DateUtilParts {

    @Value("${horizon.system-id}")
    private String systemId;
    private final MstDateDao mstDateDao;

    @Cacheable("getDate")
    public LocalDate getBusinessDate(){
        return mstDateDao.selectById(systemId).getBusinessDate();
    }
}
