package com.github.lyrric.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.util.Date;

/**
* 酒店消息列表
* @author mybatis-generator
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("酒店消息列表")
public class HotelMessage{

    @ApiModelProperty(name = "id" , value = "")
    @TableId(value = "id", type = IdType.AUTO)
	private Long id;

    @ApiModelProperty(name = "roomName" , value = "房间名称")
	private String roomName;

    @ApiModelProperty(name = "title" , value = "标题")
	private String title;

    @ApiModelProperty(name = "readFlag" , value = "是否已读，0：未读，1：已读")
	private Integer readFlag;

    @ApiModelProperty(name = "status" , value = "状态，0：未处理，1：已处理")
	private Integer status;

    @ApiModelProperty(name = "detailId" , value = "外键id，关联不同类型消息的详情id")
	private Long detailId;

    @ApiModelProperty(name = "type" , value = "消息类型，1：客需，2：报警")
	private Integer type;

    @ApiModelProperty(name = "createTime" , value = "创建时间")
	private Date createTime;

    @ApiModelProperty(name = "hotelId" , value = "酒店id")
	private Long hotelId;

    @ApiModelProperty(name = "roomId" , value = "")
	private Long roomId;

    public static final String ID = "id";
    public static final String ROOM_NAME = "room_name";
    public static final String TITLE = "title";
    public static final String READ_FLAG = "read_flag";
    public static final String STATUS = "status";
    public static final String DETAIL_ID = "detail_id";
    public static final String TYPE = "type";
    public static final String CREATE_TIME = "create_time";
    public static final String HOTEL_ID = "hotel_id";
    public static final String ROOM_ID = "room_id";

}
