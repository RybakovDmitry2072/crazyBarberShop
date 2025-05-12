package org.example.crazybarbershop.repository.iml;

import lombok.AllArgsConstructor;
import org.example.crazybarbershop.Exceptions.DbException;
import org.example.crazybarbershop.models.PortfolioImg;
import org.example.crazybarbershop.repository.interfaces.PortfolioImageService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class PortfolioImageRepositoryImpl implements PortfolioImageService {

    private static final String QUERY_SAVE = "INSERT INTO \"portfolioImage\" (url) VALUES (?)";

    private static final String QUERY_FIND_ALL = "SELECT * FROM \"portfolioImage\"";

    private static final String QUERY_UPDATE = "UPDATE \"portfolioImage\" SET url = ? WHERE id = ?";

    private static final String QUERY_DELETE = "DELETE FROM \"portfolioImage\" WHERE id = ?";

    private DataSource dataSource;

    @Override
    public Optional<List<PortfolioImg>> getAllPortfolioImg() {
        List<PortfolioImg> imageList = new ArrayList<>();

        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_FIND_ALL);
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

    @Override
    public void save(PortfolioImg portfolioImg) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(QUERY_SAVE)) {

            stmt.setString(1, portfolioImg.getUrl());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DbException("Failed to save image: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(PortfolioImg portfolioImg) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(QUERY_UPDATE)) {

            stmt.setString(1, portfolioImg.getUrl());
            stmt.setInt(2, portfolioImg.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Failed to update image: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(int id) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(QUERY_DELETE)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Failed to delete image: " + e.getMessage(), e);
        }
    }

}
