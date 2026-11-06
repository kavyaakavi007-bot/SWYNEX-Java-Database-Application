import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudySessionDAO {

    // CREATE
    public void addSession(StudySession session) {

        String sql = "INSERT INTO study_sessions " +
                     "(subject, topic, duration, priority, status, session_date) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        if (session.getSubject() == null || session.getSubject().isBlank()) {
            System.out.println("Subject cannot be empty.");
            return;
        }

        if (session.getTopic() == null || session.getTopic().isBlank()) {
            System.out.println("Topic cannot be empty.");
            return;
        }

        if (session.getDuration() <= 0) {
            System.out.println("Duration must be greater than 0.");
            return;
        }

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, session.getSubject());
            statement.setString(2, session.getTopic());
            statement.setInt(3, session.getDuration());
            statement.setString(4, session.getPriority().name());
            statement.setString(5, session.getStatus().name());
            statement.setDate(6, Date.valueOf(session.getSessionDate()));

            statement.executeUpdate();

            System.out.println("Study session added successfully.");

        } catch (SQLException e) {
            System.out.println("Error adding study session: " + e.getMessage());
        }
    }

    // READ
    public List<StudySession> getAllSessions() {

        List<StudySession> sessions = new ArrayList<>();

        String sql = "SELECT * FROM study_sessions";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                StudySession session = new StudySession(
                        resultSet.getInt("id"),
                        resultSet.getString("subject"),
                        resultSet.getString("topic"),
                        resultSet.getInt("duration"),
                        Priority.valueOf(resultSet.getString("priority")),
                        Status.valueOf(resultSet.getString("status")),
                        resultSet.getDate("session_date").toLocalDate()
                );

                sessions.add(session);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving sessions: " + e.getMessage());
        }

        return sessions;
    }

    // SEARCH
    public List<StudySession> searchBySubject(String subject) {

        List<StudySession> sessions = new ArrayList<>();

        String sql = "SELECT * FROM study_sessions WHERE subject LIKE ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, "%" + subject + "%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                StudySession session = new StudySession(
                        resultSet.getInt("id"),
                        resultSet.getString("subject"),
                        resultSet.getString("topic"),
                        resultSet.getInt("duration"),
                        Priority.valueOf(resultSet.getString("priority")),
                        Status.valueOf(resultSet.getString("status")),
                        resultSet.getDate("session_date").toLocalDate()
                );

                sessions.add(session);
            }

        } catch (SQLException e) {
            System.out.println("Error searching sessions: " + e.getMessage());
        }

        return sessions;
    }

    // UPDATE
    public void updateSession(StudySession session) {

        String sql = "UPDATE study_sessions SET " +
                     "subject = ?, topic = ?, duration = ?, " +
                     "priority = ?, status = ?, session_date = ? " +
                     "WHERE id = ?";

        if (session.getId() <= 0) {
            System.out.println("Invalid session ID.");
            return;
        }

        if (session.getSubject() == null || session.getSubject().isBlank()) {
            System.out.println("Subject cannot be empty.");
            return;
        }

        if (session.getTopic() == null || session.getTopic().isBlank()) {
            System.out.println("Topic cannot be empty.");
            return;
        }

        if (session.getDuration() <= 0) {
            System.out.println("Duration must be greater than 0.");
            return;
        }

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, session.getSubject());
            statement.setString(2, session.getTopic());
            statement.setInt(3, session.getDuration());
            statement.setString(4, session.getPriority().name());
            statement.setString(5, session.getStatus().name());
            statement.setDate(6, Date.valueOf(session.getSessionDate()));
            statement.setInt(7, session.getId());

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Study session updated successfully.");
            } else {
                System.out.println("Session ID not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error updating session: " + e.getMessage());
        }
    }

    // DELETE
    public void deleteSession(int id) {

        if (id <= 0) {
            System.out.println("Invalid session ID.");
            return;
        }

        String sql = "DELETE FROM study_sessions WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Study session deleted successfully.");
            } else {
                System.out.println("Session ID not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error deleting session: " + e.getMessage());
        }
    }

    // MARK COMPLETED
    public void markCompleted(int id) {

        if (id <= 0) {
            System.out.println("Invalid session ID.");
            return;
        }

        String sql = "UPDATE study_sessions SET status = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, Status.COMPLETED.name());
            statement.setInt(2, id);

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Study session marked as completed.");
            } else {
                System.out.println("Session ID not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error marking session as completed: " + e.getMessage());
        }
    }
}