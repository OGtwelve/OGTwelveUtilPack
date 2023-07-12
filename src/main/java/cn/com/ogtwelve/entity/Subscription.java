package cn.com.ogtwelve.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Author OGtwelve
 * @Date 2023/7/12 12:04
 * @Description: 订阅
 */
public class Subscription {
    private String object;
    @JsonProperty("has_payment_method")
    private boolean hasPaymentMethod;
    private boolean canceled;
    @JsonProperty("canceled_at")
    private String canceledAt;
    private String delinquent;
    @JsonProperty("access_until")
    private long accessUntil;
    @JsonProperty("soft_limit")
    private long softLimit;
    @JsonProperty("hard_limit")
    private long hardLimit;
    @JsonProperty("system_hard_limit")
    private long systemHardLimit;
    @JsonProperty("soft_limit_usd")
    private String softLimitUsd;
    @JsonProperty("hard_limit_usd")
    private String hardLimitUsd;
    @JsonProperty("system_hard_limit_usd")
    private String systemHardLimitUsd;
    @JsonProperty("plan")
    private Plan plan;
    @JsonProperty("account_name")
    private String accountName;
    @JsonProperty("po_number")
    private long poNumber;
    @JsonProperty("billing_email")
    private String billingEmail;
    @JsonProperty("tax_ids")
    private String taxIds;
    @JsonProperty("billing_address")
    private String billingAddress;
    @JsonProperty("business_address")
    private String businessAddress;

    public Subscription() {
    }

    public String getObject() {
        return this.object;
    }

    public boolean isHasPaymentMethod() {
        return this.hasPaymentMethod;
    }

    public boolean isCanceled() {
        return this.canceled;
    }

    public String getCanceledAt() {
        return this.canceledAt;
    }

    public String getDelinquent() {
        return this.delinquent;
    }

    public long getAccessUntil() {
        return this.accessUntil;
    }

    public long getSoftLimit() {
        return this.softLimit;
    }

    public long getHardLimit() {
        return this.hardLimit;
    }

    public long getSystemHardLimit() {
        return this.systemHardLimit;
    }

    public String getSoftLimitUsd() {
        return this.softLimitUsd;
    }

    public String getHardLimitUsd() {
        return this.hardLimitUsd;
    }

    public String getSystemHardLimitUsd() {
        return this.systemHardLimitUsd;
    }

    public Plan getPlan() {
        return this.plan;
    }

    public String getAccountName() {
        return this.accountName;
    }

    public long getPoNumber() {
        return this.poNumber;
    }

    public String getBillingEmail() {
        return this.billingEmail;
    }

    public String getTaxIds() {
        return this.taxIds;
    }

    public String getBillingAddress() {
        return this.billingAddress;
    }

    public String getBusinessAddress() {
        return this.businessAddress;
    }

    public void setObject(String object) {
        this.object = object;
    }

    @JsonProperty("has_payment_method")
    public void setHasPaymentMethod(boolean hasPaymentMethod) {
        this.hasPaymentMethod = hasPaymentMethod;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    @JsonProperty("canceled_at")
    public void setCanceledAt(String canceledAt) {
        this.canceledAt = canceledAt;
    }

    public void setDelinquent(String delinquent) {
        this.delinquent = delinquent;
    }

    @JsonProperty("access_until")
    public void setAccessUntil(long accessUntil) {
        this.accessUntil = accessUntil;
    }

    @JsonProperty("soft_limit")
    public void setSoftLimit(long softLimit) {
        this.softLimit = softLimit;
    }

    @JsonProperty("hard_limit")
    public void setHardLimit(long hardLimit) {
        this.hardLimit = hardLimit;
    }

    @JsonProperty("system_hard_limit")
    public void setSystemHardLimit(long systemHardLimit) {
        this.systemHardLimit = systemHardLimit;
    }

    @JsonProperty("soft_limit_usd")
    public void setSoftLimitUsd(String softLimitUsd) {
        this.softLimitUsd = softLimitUsd;
    }

    @JsonProperty("hard_limit_usd")
    public void setHardLimitUsd(String hardLimitUsd) {
        this.hardLimitUsd = hardLimitUsd;
    }

    @JsonProperty("system_hard_limit_usd")
    public void setSystemHardLimitUsd(String systemHardLimitUsd) {
        this.systemHardLimitUsd = systemHardLimitUsd;
    }

    @JsonProperty("plan")
    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    @JsonProperty("account_name")
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    @JsonProperty("po_number")
    public void setPoNumber(long poNumber) {
        this.poNumber = poNumber;
    }

    @JsonProperty("billing_email")
    public void setBillingEmail(String billingEmail) {
        this.billingEmail = billingEmail;
    }

    @JsonProperty("tax_ids")
    public void setTaxIds(String taxIds) {
        this.taxIds = taxIds;
    }

    @JsonProperty("billing_address")
    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    @JsonProperty("business_address")
    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Subscription)) {
            return false;
        } else {
            Subscription other = (Subscription)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.isHasPaymentMethod() != other.isHasPaymentMethod()) {
                return false;
            } else if (this.isCanceled() != other.isCanceled()) {
                return false;
            } else if (this.getAccessUntil() != other.getAccessUntil()) {
                return false;
            } else if (this.getSoftLimit() != other.getSoftLimit()) {
                return false;
            } else if (this.getHardLimit() != other.getHardLimit()) {
                return false;
            } else if (this.getSystemHardLimit() != other.getSystemHardLimit()) {
                return false;
            } else if (this.getPoNumber() != other.getPoNumber()) {
                return false;
            } else {
                label172: {
                    Object this$object = this.getObject();
                    Object other$object = other.getObject();
                    if (this$object == null) {
                        if (other$object == null) {
                            break label172;
                        }
                    } else if (this$object.equals(other$object)) {
                        break label172;
                    }

                    return false;
                }

                Object this$canceledAt = this.getCanceledAt();
                Object other$canceledAt = other.getCanceledAt();
                if (this$canceledAt == null) {
                    if (other$canceledAt != null) {
                        return false;
                    }
                } else if (!this$canceledAt.equals(other$canceledAt)) {
                    return false;
                }

                Object this$delinquent = this.getDelinquent();
                Object other$delinquent = other.getDelinquent();
                if (this$delinquent == null) {
                    if (other$delinquent != null) {
                        return false;
                    }
                } else if (!this$delinquent.equals(other$delinquent)) {
                    return false;
                }

                label151: {
                    Object this$softLimitUsd = this.getSoftLimitUsd();
                    Object other$softLimitUsd = other.getSoftLimitUsd();
                    if (this$softLimitUsd == null) {
                        if (other$softLimitUsd == null) {
                            break label151;
                        }
                    } else if (this$softLimitUsd.equals(other$softLimitUsd)) {
                        break label151;
                    }

                    return false;
                }

                label144: {
                    Object this$hardLimitUsd = this.getHardLimitUsd();
                    Object other$hardLimitUsd = other.getHardLimitUsd();
                    if (this$hardLimitUsd == null) {
                        if (other$hardLimitUsd == null) {
                            break label144;
                        }
                    } else if (this$hardLimitUsd.equals(other$hardLimitUsd)) {
                        break label144;
                    }

                    return false;
                }

                Object this$systemHardLimitUsd = this.getSystemHardLimitUsd();
                Object other$systemHardLimitUsd = other.getSystemHardLimitUsd();
                if (this$systemHardLimitUsd == null) {
                    if (other$systemHardLimitUsd != null) {
                        return false;
                    }
                } else if (!this$systemHardLimitUsd.equals(other$systemHardLimitUsd)) {
                    return false;
                }

                Object this$plan = this.getPlan();
                Object other$plan = other.getPlan();
                if (this$plan == null) {
                    if (other$plan != null) {
                        return false;
                    }
                } else if (!this$plan.equals(other$plan)) {
                    return false;
                }

                label123: {
                    Object this$accountName = this.getAccountName();
                    Object other$accountName = other.getAccountName();
                    if (this$accountName == null) {
                        if (other$accountName == null) {
                            break label123;
                        }
                    } else if (this$accountName.equals(other$accountName)) {
                        break label123;
                    }

                    return false;
                }

                Object this$billingEmail = this.getBillingEmail();
                Object other$billingEmail = other.getBillingEmail();
                if (this$billingEmail == null) {
                    if (other$billingEmail != null) {
                        return false;
                    }
                } else if (!this$billingEmail.equals(other$billingEmail)) {
                    return false;
                }

                Object this$taxIds = this.getTaxIds();
                Object other$taxIds = other.getTaxIds();
                if (this$taxIds == null) {
                    if (other$taxIds != null) {
                        return false;
                    }
                } else if (!this$taxIds.equals(other$taxIds)) {
                    return false;
                }

                label102: {
                    Object this$billingAddress = this.getBillingAddress();
                    Object other$billingAddress = other.getBillingAddress();
                    if (this$billingAddress == null) {
                        if (other$billingAddress == null) {
                            break label102;
                        }
                    } else if (this$billingAddress.equals(other$billingAddress)) {
                        break label102;
                    }

                    return false;
                }

                Object this$businessAddress = this.getBusinessAddress();
                Object other$businessAddress = other.getBusinessAddress();
                if (this$businessAddress == null) {
                    if (other$businessAddress != null) {
                        return false;
                    }
                } else if (!this$businessAddress.equals(other$businessAddress)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof Subscription;
    }

    public int hashCode() {
        int result = 1;
        result = result * 59 + (this.isHasPaymentMethod() ? 79 : 97);
        result = result * 59 + (this.isCanceled() ? 79 : 97);
        long $accessUntil = this.getAccessUntil();
        result = result * 59 + (int)($accessUntil >>> 32 ^ $accessUntil);
        long $softLimit = this.getSoftLimit();
        result = result * 59 + (int)($softLimit >>> 32 ^ $softLimit);
        long $hardLimit = this.getHardLimit();
        result = result * 59 + (int)($hardLimit >>> 32 ^ $hardLimit);
        long $systemHardLimit = this.getSystemHardLimit();
        result = result * 59 + (int)($systemHardLimit >>> 32 ^ $systemHardLimit);
        long $poNumber = this.getPoNumber();
        result = result * 59 + (int)($poNumber >>> 32 ^ $poNumber);
        Object $object = this.getObject();
        result = result * 59 + ($object == null ? 43 : $object.hashCode());
        Object $canceledAt = this.getCanceledAt();
        result = result * 59 + ($canceledAt == null ? 43 : $canceledAt.hashCode());
        Object $delinquent = this.getDelinquent();
        result = result * 59 + ($delinquent == null ? 43 : $delinquent.hashCode());
        Object $softLimitUsd = this.getSoftLimitUsd();
        result = result * 59 + ($softLimitUsd == null ? 43 : $softLimitUsd.hashCode());
        Object $hardLimitUsd = this.getHardLimitUsd();
        result = result * 59 + ($hardLimitUsd == null ? 43 : $hardLimitUsd.hashCode());
        Object $systemHardLimitUsd = this.getSystemHardLimitUsd();
        result = result * 59 + ($systemHardLimitUsd == null ? 43 : $systemHardLimitUsd.hashCode());
        Object $plan = this.getPlan();
        result = result * 59 + ($plan == null ? 43 : $plan.hashCode());
        Object $accountName = this.getAccountName();
        result = result * 59 + ($accountName == null ? 43 : $accountName.hashCode());
        Object $billingEmail = this.getBillingEmail();
        result = result * 59 + ($billingEmail == null ? 43 : $billingEmail.hashCode());
        Object $taxIds = this.getTaxIds();
        result = result * 59 + ($taxIds == null ? 43 : $taxIds.hashCode());
        Object $billingAddress = this.getBillingAddress();
        result = result * 59 + ($billingAddress == null ? 43 : $billingAddress.hashCode());
        Object $businessAddress = this.getBusinessAddress();
        result = result * 59 + ($businessAddress == null ? 43 : $businessAddress.hashCode());
        return result;
    }

    public String toString() {
        return "Subscription(object=" + this.getObject() + ", hasPaymentMethod=" + this.isHasPaymentMethod() + ", canceled=" + this.isCanceled() + ", canceledAt=" + this.getCanceledAt() + ", delinquent=" + this.getDelinquent() + ", accessUntil=" + this.getAccessUntil() + ", softLimit=" + this.getSoftLimit() + ", hardLimit=" + this.getHardLimit() + ", systemHardLimit=" + this.getSystemHardLimit() + ", softLimitUsd=" + this.getSoftLimitUsd() + ", hardLimitUsd=" + this.getHardLimitUsd() + ", systemHardLimitUsd=" + this.getSystemHardLimitUsd() + ", plan=" + this.getPlan() + ", accountName=" + this.getAccountName() + ", poNumber=" + this.getPoNumber() + ", billingEmail=" + this.getBillingEmail() + ", taxIds=" + this.getTaxIds() + ", billingAddress=" + this.getBillingAddress() + ", businessAddress=" + this.getBusinessAddress() + ")";
    }
}

