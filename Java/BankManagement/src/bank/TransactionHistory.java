package bank;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TransactionHistory {
    private Long id;
    private LocalDate dayTrading;
    private String description;
    private String beneficiaryAccount;
    private long money;

    public TransactionHistory(Long id, LocalDate dayTrading, String description, String beneficiaryAccount, long money) {
        this.id = id;
        this.dayTrading = dayTrading;
        this.description = description;
        this.beneficiaryAccount = beneficiaryAccount;
        this.money = money;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDayTrading() {
        return dayTrading;
    }

    public void setDayTrading(LocalDate dayTrading) {
        this.dayTrading = dayTrading;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBeneficiaryAccount() {
        return beneficiaryAccount;
    }

    public void setBeneficiaryAccount(String beneficiaryAccount) {
        this.beneficiaryAccount = beneficiaryAccount;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public String toString() {
        DateTimeFormatter myFormater = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return id + " - " + dayTrading.format(myFormater) + " - " + description + " - "
                + Controller.formatMoney(money) + " - " + beneficiaryAccount + "\n";
    }
}
