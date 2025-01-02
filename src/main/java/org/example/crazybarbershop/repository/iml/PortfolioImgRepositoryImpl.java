package org.example.crazybarbershop.repository.iml;

import lombok.AllArgsConstructor;
import org.example.crazybarbershop.models.Image;
import org.example.crazybarbershop.models.PortfolioImg;
import org.example.crazybarbershop.repository.interfaces.PortfolioImgRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class PortfolioImgRepositoryImpl implements PortfolioImgRepository {

    private static final String QUERY_FIND_BY_ID = "select * from \"portfolioImage\"";

    private DataSource dataSource;


    @Override
    public Optional<List<PortfolioImg>> getAllPortfolioImg() {
        List<PortfolioImg> imageList = new ArrayList<>();

        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_FIND_BY_ID);
            ResultSet rs = preparedStatement.executeQuery()){
            while (rs.next()){
                PortfolioImg image = PortfolioImg.builder()
                        .id(rs.getInt("id"))
                        .url(rs.getString("url"))
                        .build();
                imageList.add(image);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(imageList);
    }
}
