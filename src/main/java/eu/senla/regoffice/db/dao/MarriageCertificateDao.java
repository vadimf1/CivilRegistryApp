package eu.senla.regoffice.db.dao;

import eu.senla.regoffice.models.User;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@RequiredArgsConstructor
public class MarriageCertificateDao {
    private final Connection connection;

    public void createMarriageCertificate(User user, int citizenId) {
        String sql = """
                    INSERT INTO merrigecertificates (citizenid, dateofmerrige, surnameofspouse, newsurnameofspouse, nameofspouse, middlenameofspouse, passportnumberofspouse, dateofbirthofspouse)
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, citizenId);
            stmt.setDate(2, Date.valueOf(user.getMarriageRegistrationDate()));
            stmt.setString(3, user.getSpouseLastName());
            stmt.setString(4, user.getNewLastName());
            stmt.setString(5, user.getSpouseFirstName());
            stmt.setString(6, user.getSpouseMiddleName());
            stmt.setString(7, user.getSpousePassportNumber());
            stmt.setDate(8, Date.valueOf(user.getSpouseBirthDate()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create marriage certificate", e);
        }
    }

    public void deleteMarriageCertificateByCitizenId(int citizenId) {
        String sql = "DELETE FROM merrigecertificates WHERE citizenid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, citizenId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete marriage certificate with citizenId = " + citizenId, e);
        }
    }
}
