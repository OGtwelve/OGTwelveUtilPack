package cn.com.ogtwelve.entity;

/**
 * @Author OGtwelve
 * @Date 2023/7/12 12:03
 * @Description: 账单
 */
public class Billing {
    private String dueDate;
    private String total;
    private String usage;
    private String balance;

    public Billing() {
    }

    public String getDueDate() {
        return this.dueDate;
    }

    public String getTotal() {
        return this.total;
    }

    public String getUsage() {
        return this.usage;
    }

    public String getBalance() {
        return this.balance;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Billing)) {
            return false;
        } else {
            Billing other = (Billing) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label59:
                {
                    Object this$dueDate = this.getDueDate();
                    Object other$dueDate = other.getDueDate();
                    if (this$dueDate == null) {
                        if (other$dueDate == null) {
                            break label59;
                        }
                    } else if (this$dueDate.equals(other$dueDate)) {
                        break label59;
                    }

                    return false;
                }

                Object this$total = this.getTotal();
                Object other$total = other.getTotal();
                if (this$total == null) {
                    if (other$total != null) {
                        return false;
                    }
                } else if (!this$total.equals(other$total)) {
                    return false;
                }

                Object this$usage = this.getUsage();
                Object other$usage = other.getUsage();
                if (this$usage == null) {
                    if (other$usage != null) {
                        return false;
                    }
                } else if (!this$usage.equals(other$usage)) {
                    return false;
                }

                Object this$balance = this.getBalance();
                Object other$balance = other.getBalance();
                if (this$balance == null) {
                    if (other$balance != null) {
                        return false;
                    }
                } else if (!this$balance.equals(other$balance)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof Billing;
    }

    public int hashCode() {
        int result = 1;
        Object $dueDate = this.getDueDate();
        result = result * 59 + ($dueDate == null ? 43 : $dueDate.hashCode());
        Object $total = this.getTotal();
        result = result * 59 + ($total == null ? 43 : $total.hashCode());
        Object $usage = this.getUsage();
        result = result * 59 + ($usage == null ? 43 : $usage.hashCode());
        Object $balance = this.getBalance();
        result = result * 59 + ($balance == null ? 43 : $balance.hashCode());
        return result;
    }

    public String toString() {
        return "Billing(dueDate=" + this.getDueDate() + ", total=" + this.getTotal() + ", usage=" + this.getUsage() + ", balance=" + this.getBalance() + ")";
    }

}