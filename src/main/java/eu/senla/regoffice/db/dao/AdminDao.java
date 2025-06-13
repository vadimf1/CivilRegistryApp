package eu.senla.regoffice.db.dao;

import eu.senla.regoffice.models.Admin;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@RequiredArgsConstructor
public class AdminDao {
    private final Connection connection;

    public int createAdmin(Admin admin) {
        String sql = """
                    INSERT INTO staff (surname, name, middlename, dateofbirth, passportnumber, phonenumber)
                    VALUES (?, ?, ?, ?, ?, ?) RETURNING staffid
                """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, admin.getLastName());
            stmt.setString(2, admin.getFirstName());
            stmt.setString(3, admin.getMiddleName());
            stmt.setDate(4, Date.valueOf(admin.getBirthDate()));
            stmt.setString(5, admin.getPassportNumber());
            stmt.setString(6, admin.getPhoneNumber());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("staffid");
            } else {
                throw new RuntimeException("Failed to insert staff (admin)");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean adminExistsById(int staffId) {
        String sql = "SELECT 1 FROM staff WHERE staffid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, staffId);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to check admin existence with id=" + staffId, e);
        }
    }

    public int getLastAdminId() {
        String sql = "SELECT staffid FROM staff ORDER BY staffid DESC LIMIT 1";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("staffid");
            } else {
                throw new RuntimeException("Admins are empty");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAdminById(int staffId) {
        String sql = "DELETE FROM staff WHERE staffid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, staffId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAdmin(Admin admin) {
        String sql = "DELETE FROM staff WHERE name = ? and surname = ? and middlename = ? and phonenumber = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, admin.getFirstName());
            stmt.setString(2, admin.getLastName());
            stmt.setString(3, admin.getMiddleName());
            stmt.setString(4, admin.getPhoneNumber());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
