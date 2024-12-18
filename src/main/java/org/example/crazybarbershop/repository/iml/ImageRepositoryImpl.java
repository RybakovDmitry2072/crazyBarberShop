package org.example.crazybarbershop.repository.iml;

import lombok.AllArgsConstructor;
import org.example.crazybarbershop.models.Image;
import org.example.crazybarbershop.repository.interfaces.ImageRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@AllArgsConstructor
public class ImageRepositoryImpl implements ImageRepository {

    private static final String QUERY_FIND_BY_ID = "select * from images where id = ?";

    private DataSource dataSource;


    @Override
    public List<Image> getImageAll() {
        List<Image> imageList = null;

        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_FIND_BY_ID);
            ResultSet rs = preparedStatement.executeQuery()){
            while (rs.next()){
                Image image = Image.builder()
                        .id(rs.getInt("id"))
                        .url(rs.getString("url"))
                        .discription("discription")
                        .build();
                imageList.add(image);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return imageList;
    }
}
