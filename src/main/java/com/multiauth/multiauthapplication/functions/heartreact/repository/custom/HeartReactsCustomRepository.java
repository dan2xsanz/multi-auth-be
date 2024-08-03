package com.multiauth.multiauthapplication.functions.heartreact.repository.custom;

import com.multiauth.multiauthapplication.functions.heartreact.dto.HeartReactsDto;

public interface HeartReactsCustomRepository {

    void addToHeartReactsRequest(HeartReactsDto heartReactsDto);

    void deleteHeartReactsRequest(HeartReactsDto heartReactsDto);
}
