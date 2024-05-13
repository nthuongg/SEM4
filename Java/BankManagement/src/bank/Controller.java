package bank;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {
    private static long balanceNumber = 1000000;
    List<TransactionHistory> listHistory = new ArrayList<TransactionHistory>();

    public long getBalanceNumber() {
        return balanceNumber;
    }

    public static long transfer(long money) {
        // Kiểm tra xem số tiền nhỏ hơn 50.000 thì thông báo số tiền chuyển tối thiểu phải là 50.000
        if (money < 50000) {
            System.out.println("Số tiền tối thiểu là 50.000");
            return balanceNumber;
        }
        // Nếu số tiền chuyển lớn hơn số dư sẽ báo tài khoản không đủ
        if (money > balanceNumber) {
            System.out.println("Số dư không đủ, vui lòng nạp thêm !!");
            return balanceNumber;
        }
        //Nếu chuyển khoản thành công, thông báo chuyển khoản thành công, in số dư mới và thêm lịch sử giao dịch mới
        System.out.println("Chuyển khoản thành công !!");

        balanceNumber -= money; // giảm số dư
        System.out.println("Số dư mới: " + balanceNumber);
        return  balanceNumber;
    }

    public void actionTransfer() {
        //Thực hiện các công việc như nhập số tài khoản thụ hưởng, số tiền mô tả tại đây
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập số tk hưởng thụ:");
        String beneficiaryAccount = scanner.nextLine();
        System.out.println("Nhập số tiền cần chuyển:");
        long money = scanner.nextLong();
        System.out.println("Chuyển khoản thành công tới số tài khoản " + beneficiaryAccount + "!\n");
        System.out.print("Mô tả: ");
        String description = scanner.nextLine();
        LocalDate dayTrading = LocalDate.now();

        // Gọi phương thức transfer() để thực hiện chuyển khoản với số tài khoản thụ hưởng và số tiền đã nhập
        transfer(money);
        listHistory.add(new TransactionHistory(IdGenerate.getNewID(),dayTrading,description,beneficiaryAccount,money));
    }

    public void getHistory() {
        //In danh sách lịch sử giao dịch
        for (TransactionHistory history : listHistory) {
            System.out.println("Danh sách lịch sử giao dịch: " + history + "\n");
        }
    }

    //Định dạng số tiênf theo mình mong muốn
    public static String formatMoney(long money) {
        DecimalFormat formatter = new DecimalFormat("###,###,##0.00");
        //100000 -> 100,000.00
        return formatter.format(money);
    }
}
