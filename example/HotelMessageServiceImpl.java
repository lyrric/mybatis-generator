package com.github.lyrric.service.impl;

import com.github.lyrric.service.HotelMessageService;
import com.github.lyrric.mapper.HotelMessageMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
* 酒店消息列表
* @author mybatis-generator
*/
@Service
public class HotelMessageServiceImpl implements HotelMessageService  {

    @Resource
    private HotelMessageMapper hotelMessageMapper;
}