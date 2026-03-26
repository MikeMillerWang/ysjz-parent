package com.ysjz.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ysjz.serializer.JacksonBigDecimalSerializer;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BigDecimalTest {

	@JsonSerialize(using = JacksonBigDecimalSerializer.class)
	private BigDecimal bd;
}