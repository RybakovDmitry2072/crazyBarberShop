package org.example.crazybarbershop.services.impl;

import lombok.AllArgsConstructor;
import org.example.crazybarbershop.FactoryDto.PortfolioImgDtoFactory;
import org.example.crazybarbershop.dto.PortfolioImgDto;
import org.example.crazybarbershop.models.PortfolioImg;
import org.example.crazybarbershop.repository.interfaces.PortfolioImgRepository;
import org.example.crazybarbershop.services.interfaces.PortfolioService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class PortfolioServiceImpl implements PortfolioService {

    private PortfolioImgRepository portfolioImgRepository;

    @Override
    public List<PortfolioImgDto> getAllPorfolioImg() {
        Optional<List<PortfolioImg>> portfolioImgList = portfolioImgRepository.getAllPortfolioImg();
        try {
            return portfolioImgList.get().stream()
                    .map(PortfolioImgDtoFactory::factoryDto)
                    .collect(Collectors.toList());
        }catch (RuntimeException e) {
            throw new IllegalStateException("Not found images", e);
        }
    }

}

