package com.multiauth.multiauthapplication.util;

import com.multiauth.multiauthapplication.util.dto.FindByPropertyDto;

public interface CommonUtils {

    <T> T findByProperties(FindByPropertyDto findByPropertyDto, Class<T> returnType);
}
