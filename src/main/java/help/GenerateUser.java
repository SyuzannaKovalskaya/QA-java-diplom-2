package help;

public class GenerateUser {
    public static String generateEmail() {
        return String.format("alex%d", ((int) (Math.random() * (9999 - 1111) + 1111))) + "@gmail.com";
    }
}
