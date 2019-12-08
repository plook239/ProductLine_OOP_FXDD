import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Employee {
    String username;
    String password;
    String email;
    String fullName;

    /**
     * The constructor should accept a String for name (firstname and surname) and a String for password.
     */
    public Employee(String name, String password) {

        if (checkName(name)) {
            this.username = setUsername(name);
            this.email = setEmail(name);
            this.fullName = setFullName(name);
        } else {
            fullName = name.toUpperCase().charAt(0) + name.substring(1);
            username = "default";
            email = "user@oracleacademy.Test";
        }
        this.password = isValidPassword(password);
    }


    private boolean checkName(String name) {
        return name.contains(" ");
    }

    /**
     * setUsername will set the username field to the first initial of the first name and then the last name, all lowercase.
     */
    private String setUsername(String name) {
        String[] EmpName = name.split(" ");
        String first = EmpName[0];
        String last = EmpName[1];
        String userName = first.charAt(0) + last;
        return userName.toLowerCase();
    }

    /**
     * setEmail will set the email field to the first name, then a period, then the last name (all lowercase) followed by @oracleacademy.Test
     */
    private String setEmail(String name) {
        String[] EmpName = name.split(" ");
        String first = EmpName[0];
        String last = EmpName[1];
        return first.toLowerCase() + "." + last.toLowerCase() + "@oracleacademy.Test";
    }

    /**
     * If the password is valid (containing a lowercase letter, uppercase letter, and a special character) the password field gets set to the supplied password. If the password is invalid, the password field gets set to "pw".
     */
    private String isValidPassword(String password) {
        boolean hasUppercase = !password.equals(password.toLowerCase());
        boolean hasLowercase = !password.equals(password.toUpperCase());
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
        Matcher matcher = pattern.matcher(password);
        String userPass = password;
        if (hasUppercase && hasLowercase && !matcher.matches()) {
            return userPass;
        } else {
            userPass = "pw";
        }
        return userPass;
    }

    private String setFullName(String name) {
        String[] EmpName = name.split(" ");
        String first = EmpName[0];
        String last = EmpName[1];
        return first.toUpperCase().charAt(0) + first.substring(1) + " " + last.toUpperCase().charAt(0) + last.substring(1);
    }

    static String reverseString(String id) {
        if (id.length() == 1)
            return id;

        return reverseString(id.substring(1)) + id.charAt(0);
    }

    public String toString() {
        return "Employee Details\n" +
                "Name : " + fullName + "\n" +
                "Username : " + username + "\n" +
                "Email : " + email + "\n" +
                "Initial Password : " + password;
    }

}
