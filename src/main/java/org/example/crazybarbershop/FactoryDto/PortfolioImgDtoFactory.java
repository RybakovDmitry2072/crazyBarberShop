package org.example.crazybarbershop.FactoryDto;

import org.example.crazybarbershop.dto.CategoryDto;
import org.example.crazybarbershop.dto.PortfolioImgDto;
import org.example.crazybarbershop.models.Category;
import org.example.crazybarbershop.models.PortfolioImg;

public class PortfolioImgDtoFactory {

    public static PortfolioImgDto factoryDto(PortfolioImg portfolioImgDto){
        return PortfolioImgDto.builder()
                .id(portfolioImgDto.getId())
                .url(portfolioImgDto.getUrl())
                .build();
    }

}
