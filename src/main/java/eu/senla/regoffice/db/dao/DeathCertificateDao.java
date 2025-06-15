package eu.senla.regoffice.db.dao;

import eu.senla.regoffice.models.User;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@RequiredArgsConstructor
public class DeathCertificateDao {
    private final Connection connection;

    public void createDeathCertificate(User user, int citizenId) {
        String sql = """
                    INSERT INTO deathcertificates (citizenid, dateofdeath, placeofdeath)
                    VALUES (?, ?, ?)
                """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, citizenId);
            stmt.setDate(2, Date.valueOf(user.getDeathDate()));
            stmt.setString(3, user.getDeathPlace());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create death certificate", e);
        }
    }

    public void deleteDeathCertificateByCitizenId(int citizenId) {
        String sql = "DELETE FROM deathcertificates WHERE citizenid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, citizenId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete death certificate with citizenId = " + citizenId, e);
        }
    }
}
