package org.example.crazybarbershop.repository.interfaces;

import org.example.crazybarbershop.models.PortfolioImg;

import java.util.List;
import java.util.Optional;

public interface PortfolioImageService {

    Optional<List<PortfolioImg>> getAllPortfolioImg();

    void delete(int id);

    void save(PortfolioImg portfolioImg);

    void update(PortfolioImg portfolioImg);



}
