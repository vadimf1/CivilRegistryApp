package eu.senla.regoffice.db.dao;

import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@RequiredArgsConstructor
public class MarriageCertificateDao {
    private final Connection connection;

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
