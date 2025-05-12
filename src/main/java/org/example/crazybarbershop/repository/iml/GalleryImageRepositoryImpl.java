package org.example.crazybarbershop.repository.iml;

import lombok.AllArgsConstructor;
import org.example.crazybarbershop.models.GalleryImage;
import org.example.crazybarbershop.repository.interfaces.GalleryImageRepository;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class GalleryImageRepositoryImpl implements GalleryImageRepository {

    private static final String QUERY_FIND_ALL = "SELECT * FROM \"galleryImage\"";

    private DataSource dataSource;

    @Override
    public List<GalleryImage> findAll() {
        List<GalleryImage> imageList = new ArrayList<>();

        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_FIND_ALL);
            ResultSet rs = preparedStatement.executeQuery()){
            while (rs.next()){
                GalleryImage image = GalleryImage.builder()
                        .id(rs.getInt("id"))
                        .url(rs.getString("url"))
                        .build();
                imageList.add(image);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return imageList;
    }

}
