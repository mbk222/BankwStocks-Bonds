
import java.time.LocalDate;
import java.util.List;

public class boughtBond {
    private int customerid;
    private String id;
    private String bondName;
    private double boughtAmount;
    private LocalDate date;
    private int sb;
    private int type;

    public boughtBond(int customerid, String id, String bondName, double boughtAmount, LocalDate date, int sb, int type){
        this.customerid = customerid;
        this.id = id;
        this.bondName = bondName;
        this.boughtAmount = boughtAmount;
        this.date = date;
        this.sb = sb;
        this.type = type;
    }

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public String getBondName() {
        return bondName;
    }

    public void setBondName(String bondName) {
        this.bondName = bondName;
    }

    public double getBoughtAmount() {
        return boughtAmount;
    }

    public void setBoughtAmount(double boughtAmount) {
        this.boughtAmount = boughtAmount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getSb() {
        return sb;
    }

    public void setSb(int sb) {
        this.sb = sb;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
