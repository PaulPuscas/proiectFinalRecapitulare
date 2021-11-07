package com.sda.proiectFinalRecapitulare.validator;

import com.sda.proiectFinalRecapitulare.dto.ProductDto;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
public class ProductDtoValidator {

    public void validate(ProductDto productDto, BindingResult bindingResult) {
        String priceAsString = productDto.getStartBiddingPrice();
        try {
            Integer priceAsInteger = Integer.valueOf(priceAsString);
            if (priceAsInteger<= 0){
                FieldError fieldError =new FieldError("productDto","startBiddingPrice","The price must be positive!");
                bindingResult.addError(fieldError);
            }
        } catch (NumberFormatException numberFormatException) {
            FieldError fieldError =new FieldError("productDto","startBiddingPrice","The price must be a number!");
            bindingResult.addError(fieldError);
        }
    }

    public boolean validate2(ProductDto productDto) {
        String priceAsString = productDto.getStartBiddingPrice();
        try {
            Integer priceAsInteger = Integer.valueOf(priceAsString);
            if (priceAsInteger <= 0){
                return false;
            }
        } catch (NumberFormatException numberFormatException) {
            return false;
        }
        return true;
    }
}
