package eu.senla.regoffice.db.dao;

import eu.senla.regoffice.models.User;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@RequiredArgsConstructor
public class BirthCertificateDao {
    private final Connection connection;

    public void createBirthCertificate(User user, int citizenId) {
        String sql = """
                    INSERT INTO birthcertificates (citizenid, placeofbirth, mother, father, grandma, grandpa)
                    VALUES (?, ?, ?, ?, ?, ?)
                """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, citizenId);
            stmt.setString(2, user.getBirthPlace());
            stmt.setString(3, user.getMotherInfo());
            stmt.setString(4, user.getFatherInfo());
            stmt.setString(5, user.getGrandmotherInfo());
            stmt.setString(6, user.getGrandfatherInfo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create birth certificate", e);
        }
    }

    public void deleteBirthCertificateByCitizenId(int citizenId) {
        String sql = "DELETE FROM birthcertificates WHERE citizenid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, citizenId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete birth certificate with citizenId = " + citizenId, e);
        }
    }
}
