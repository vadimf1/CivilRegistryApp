package eu.senla.regoffice.db.dao;

import eu.senla.regoffice.models.User;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@RequiredArgsConstructor
public class ApplicantDao {
    private final Connection connection;

    public int createApplicant(User user) {
        String sql = """
                    INSERT INTO applicants (surname, name, middlename, passportnumber, phonenumber, registration_address)
                    VALUES (?, ?, ?, ?, ?, ?) RETURNING applicantid
                """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getApplicantLastName());
            stmt.setString(2, user.getApplicantFirstName());
            stmt.setString(3, user.getApplicantMiddleName());
            stmt.setString(4, user.getApplicantPassportNumber());
            stmt.setString(5, user.getApplicantPhoneNumber());
            stmt.setString(6, user.getApplicantAddress());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("applicantid");
            } else {
                throw new SQLException("Failed to insert applicant");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteApplicantById(int applicantId) {
        String sql = "DELETE FROM applicants WHERE applicantid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, applicantId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete applicant with id = " + applicantId, e);
        }
    }
}
