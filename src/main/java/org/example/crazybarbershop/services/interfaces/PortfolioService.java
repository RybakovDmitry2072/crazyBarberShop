package org.example.crazybarbershop.services.interfaces;

import org.example.crazybarbershop.dto.PortfolioImgDto;
import org.example.crazybarbershop.models.PortfolioImg;

import java.util.List;

public interface PortfolioService {

    List<PortfolioImgDto> getAllPorfolioImg();

    void delete(int id);

    void save(PortfolioImg image);

    void update(PortfolioImg image);



}
