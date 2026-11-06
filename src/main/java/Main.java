import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final StudySessionDAO dao = new StudySessionDAO();

    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println("     STUDY SESSION PLANNER");
        System.out.println("====================================");

        while (true) {

            System.out.println("\n1. Add Study Session");
            System.out.println("2. View All Sessions");
            System.out.println("3. Search by Subject");
            System.out.println("4. Update Session");
            System.out.println("5. Delete Session");
            System.out.println("6. Mark Session as Completed");
            System.out.println("7. Exit");

            System.out.print("\nEnter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    addSession();
                    break;

                case "2":
                    viewAllSessions();
                    break;

                case "3":
                    searchSession();
                    break;

                case "4":
                    updateSession();
                    break;

                case "5":
                    deleteSession();
                    break;

                case "6":
                    markCompleted();
                    break;

                case "7":
                    System.out.println("Thank you for using Study Session Planner!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please enter 1-7.");
            }
        }
    }

    // ADD
    private static void addSession() {

        System.out.println("\n--- Add Study Session ---");

        String subject = getNonEmptyInput("Enter subject: ");
        String topic = getNonEmptyInput("Enter topic: ");

        int duration = getPositiveInteger("Enter duration in minutes: ");

        Priority priority = getPriority();

        Status status = getStatus();

        LocalDate date = getDate();

        StudySession session = new StudySession(
                subject,
                topic,
                duration,
                priority,
                status,
                date
        );

        dao.addSession(session);
    }

    // VIEW
    private static void viewAllSessions() {

        System.out.println("\n--- All Study Sessions ---");

        List<StudySession> sessions = dao.getAllSessions();

        if (sessions.isEmpty()) {
            System.out.println("No study sessions found.");
            return;
        }

        for (StudySession session : sessions) {
            System.out.println(session);
        }
    }

    // SEARCH
    private static void searchSession() {

        System.out.println("\n--- Search Study Session ---");

        String subject = getNonEmptyInput("Enter subject to search: ");

        List<StudySession> sessions = dao.searchBySubject(subject);

        if (sessions.isEmpty()) {
            System.out.println("No sessions found for: " + subject);
            return;
        }

        for (StudySession session : sessions) {
            System.out.println(session);
        }
    }

    // UPDATE
    private static void updateSession() {

        System.out.println("\n--- Update Study Session ---");

        int id = getPositiveInteger("Enter session ID: ");

        String subject = getNonEmptyInput("Enter new subject: ");
        String topic = getNonEmptyInput("Enter new topic: ");

        int duration = getPositiveInteger("Enter new duration in minutes: ");

        Priority priority = getPriority();

        Status status = getStatus();

        LocalDate date = getDate();

        StudySession session = new StudySession(
                id,
                subject,
                topic,
                duration,
                priority,
                status,
                date
        );

        dao.updateSession(session);
    }

    // DELETE
    private static void deleteSession() {

        System.out.println("\n--- Delete Study Session ---");

        int id = getPositiveInteger("Enter session ID: ");

        dao.deleteSession(id);
    }

    // MARK COMPLETED
    private static void markCompleted() {

        System.out.println("\n--- Mark Session as Completed ---");

        int id = getPositiveInteger("Enter session ID: ");

        dao.markCompleted(id);
    }

    // VALIDATION
    private static String getNonEmptyInput(String message) {

        while (true) {

            System.out.print(message);

            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println("Input cannot be empty. Please try again.");
        }
    }

    private static int getPositiveInteger(String message) {

        while (true) {

            System.out.print(message);

            String input = scanner.nextLine();

            try {

                int value = Integer.parseInt(input);

                if (value > 0) {
                    return value;
                }

                System.out.println("Value must be greater than 0.");

            } catch (NumberFormatException e) {

                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static Priority getPriority() {

        while (true) {

            System.out.print("Enter priority (LOW/MEDIUM/HIGH): ");

            String input = scanner.nextLine().trim().toUpperCase();

            try {
                return Priority.valueOf(input);

            } catch (IllegalArgumentException e) {

                System.out.println("Invalid priority. Choose LOW, MEDIUM, or HIGH.");
            }
        }
    }

    private static Status getStatus() {

        while (true) {

            System.out.print("Enter status (PENDING/COMPLETED): ");

            String input = scanner.nextLine().trim().toUpperCase();

            try {
                return Status.valueOf(input);

            } catch (IllegalArgumentException e) {

                System.out.println("Invalid status. Choose PENDING or COMPLETED.");
            }
        }
    }

    private static LocalDate getDate() {

        while (true) {

            System.out.print("Enter date (YYYY-MM-DD): ");

            String input = scanner.nextLine().trim();

            try {
                return LocalDate.parse(input);

            } catch (DateTimeParseException e) {

                System.out.println("Invalid date format. Example: 2026-11-05");
            }
        }
    }
}