package com.ysjz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class ChannelTest {

	@TableId(type = IdType.AUTO)
	private Long id;

	/**
	* 姓名
	*/
	private String linkmanName;

	/**
	* 手机号
	*/
	private String mobile;

	/**
	* 微信二维码URL
	*/
	private String wxQrUrl;

	/**
	* 从业年限
	*/
	private String workPeriod;

	/**
	* 履历简介
	*/
	private String briefVitae;

	/**
	* 头像地址
	*/
	private String portraitUrl;
}