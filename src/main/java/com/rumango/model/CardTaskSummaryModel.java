package com.rumango.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class CardTaskSummaryModel {
	private List<CardTaskSummaryDataModel> cardList = new ArrayList<>();
	private int noOfElements;
	private int totalPages;
	private long totalNoOfElements;

}
