package org.example.crazybarbershop.repository.interfaces;

import org.example.crazybarbershop.models.PortfolioImg;

import java.util.List;
import java.util.Optional;

public interface PortfolioImgRepository {

    Optional<List<PortfolioImg>> getAllPortfolioImg();

}
