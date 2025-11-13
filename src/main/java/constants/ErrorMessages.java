package constants;

public class ErrorMessages {
    // Login errors
    public static final String LOGIN_FAILED = "Đăng nhập thất bại\n" +
            "Email/Số điện thoại hoặc mật khẩu không đúng. Tài khoản của bạn sẽ bị khóa nếu nhập sai 5 lần";

    public static final String EMAIL_REQUIRED = "Vui lòng nhập tên đăng nhập";
    public static final String PASSWORD_REQUIRED = "Vui lòng nhập mật khẩu";

    private ErrorMessages() {
        // Prevent instantiation
    }
}
