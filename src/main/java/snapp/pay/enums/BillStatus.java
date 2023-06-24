package snapp.pay.enums;

import lombok.Getter;

@Getter
public enum BillStatus {

    ACTIVE(1, "ACTIVE", "bill is unsettled"),
    SETTLED(2, "SETTLED", "bill is settled");

    private Integer id;
    private String value;
    private String desc;

    BillStatus(Integer id, String value, String desc) {
        this.id = id;
        this.value = value;
        this.desc = desc;
    }

    public static BillStatus getBillStatus(Integer id) {

        BillStatus notStatus = null;
        for (BillStatus eventStatus : BillStatus.values()) {
            notStatus = eventStatus.getId().intValue() == id.intValue() ? eventStatus : null;
        }
        return notStatus;
    }
}
