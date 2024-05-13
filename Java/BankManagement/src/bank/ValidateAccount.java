package bank;

public class ValidateAccount {
    //Kiểm tra đăng nhập
    public final String MOBILE = "1234567890";
    public final String PASSWORD = "Ngo Van Khoai";
    public boolean checkAccount(String mobile, String password) {
        if (mobile.equals(MOBILE)) {
            if (password.equals(PASSWORD)) {
                System.out.println("Đăng nhập thành công!");
                return true;
            } else {
                System.out.println("Mật khẩu sai, vui lòng thử lai");
                return false;
            }
        } else {
            System.out.println("Kiểm tra lại số điện thoại hoặc password");
            return false;
        }
    }
}
