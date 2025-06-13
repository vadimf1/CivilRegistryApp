package eu.senla.regoffice.db.dao;

import eu.senla.regoffice.models.ApplicationRelatedIds;
import eu.senla.regoffice.models.User;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@RequiredArgsConstructor
public class ApplicationDao {
    private final Connection connection;

    public int createApplication(User user, int applicantId, int citizenId) {
        String sql = """
                    INSERT INTO applications (citizenid, applicantid, staffid, dateofapplication, kindofapplication, statusofapplication, channel)
                    VALUES (?, ?, NULL, CURRENT_TIMESTAMP, ?, ?, NULL) RETURNING applicationid
                """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, citizenId);
            stmt.setInt(2, applicantId);
            stmt.setString(3, user.getMode());
            stmt.setString(4, "created");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("applicationid");
            } else {
                throw new SQLException("Failed to insert application");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ApplicationRelatedIds getRelatedIds(int applicationId) {
        String sql = "SELECT citizenid, applicantid FROM applications WHERE applicationid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, applicationId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int citizenId = rs.getInt("citizenid");
                int applicantId = rs.getInt("applicantid");
                return ApplicationRelatedIds.builder()
                        .applicantId(applicantId)
                        .citizenId(citizenId)
                        .build();
            } else {
                throw new RuntimeException("No application found with id = " + applicationId);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch related IDs for application " + applicationId, e);
        }
    }

    public String getApplicationStatusById(int applicationId) {
        String sql = "SELECT statusofapplication FROM applications WHERE applicationid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, applicationId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("statusofapplication");
            } else {
                throw new RuntimeException("No application found with id = " + applicationId);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getApplicationIdByCitizenId(int citizenId) {
        String sql = "SELECT applicationid FROM applications WHERE citizenid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, citizenId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("applicationid");
            } else {
                throw new RuntimeException("No application found with citizen id = " + citizenId);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getApplicationsCount() {
        String sql = "SELECT COUNT(*) FROM applications";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new IllegalStateException("COUNT(*) query returned no result");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean applicationExistsById(int applicationId) {
        String sql = "SELECT 1 FROM applications WHERE applicationid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, applicationId);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to check application existence with id=" + applicationId, e);
        }
    }

    public void deleteApplicationById(int applicationId) {
        String sql = "DELETE FROM applications WHERE applicationid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, applicationId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete application with id = " + applicationId, e);
        }
    }
}
