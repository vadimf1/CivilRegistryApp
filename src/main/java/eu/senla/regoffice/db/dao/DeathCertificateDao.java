package eu.senla.regoffice.db.dao;

import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@RequiredArgsConstructor
public class DeathCertificateDao {
    private final Connection connection;

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
