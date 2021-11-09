package com.sda.proiectFinalRecapitulare.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class ProductDto {
 //cream datele pe care le vom introduce in baza de date
   private String id;
    private String name;
    private String description;
    private String startBiddingPrice;
    private String category;
    private String endDateTime;
    private String image;

}
