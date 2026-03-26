package com.ysjz.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ysjz.serializer.BigDecimalSerializer;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BigDecimalTest {

	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal bd;
}