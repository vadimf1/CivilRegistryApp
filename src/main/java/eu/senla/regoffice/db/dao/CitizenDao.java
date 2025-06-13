package eu.senla.regoffice.db.dao;

import eu.senla.regoffice.models.User;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@RequiredArgsConstructor
public class CitizenDao {
    private final Connection connection;

    public int createCitizen(User user) {
        String sql = """
                    INSERT INTO citizens (surname, name, middlename, passportnumber, dateofbirth, gender, registration_address, image)
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING citizenid
                """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getCitizenLastName());
            stmt.setString(2, user.getCitizenFirstName());
            stmt.setString(3, user.getCitizenMiddleName());
            stmt.setString(4, user.getCitizenPassportNumber());
            stmt.setDate(5, Date.valueOf(user.getCitizenBirthDate()));
            stmt.setString(6, user.getCitizenGender());
            stmt.setString(7, user.getCitizenAddress());
            stmt.setString(8, null);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("citizenid");
            } else {
                throw new SQLException("Failed to insert citizen");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getCitizenId(User user) {
        String sql = "SELECT citizenid FROM citizens WHERE name = ? and surname = ? and middlename = ? and passportnumber = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, user.getCitizenFirstName());
            stmt.setString(2, user.getCitizenLastName());
            stmt.setString(3, user.getCitizenMiddleName());
            stmt.setString(4, user.getCitizenPassportNumber());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("citizenid");
            } else {
                throw new SQLException("Failed to insert citizen");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteCitizenById(int citizenId) {
        String sql = "DELETE FROM citizens WHERE citizenid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, citizenId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete citizen with id = " + citizenId, e);
        }
    }
}
