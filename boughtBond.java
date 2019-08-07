import java.sql.Timestamp;

public class boughtBond {
    private int customerid;
    private int typeChose;
    private double boughtAmount;
    private Timestamp date;
    private int sb;

    public boughtBond(int customerid, int typeChose, double boughtAmount, Timestamp date, int sb){
        this.customerid = customerid;
        this.typeChose = typeChose;
        this.boughtAmount = boughtAmount;
        this.date = date;
        this.sb = sb;
    }

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public int getTypeChose() {
        return typeChose;
    }

    public void setTypeChose(int typeChose) {
        this.typeChose = typeChose;
    }

    public double getBoughtAmount() {
        return boughtAmount;
    }

    public void setBoughtAmount(double boughtAmount) {
        this.boughtAmount = boughtAmount;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public int getSb() {
        return sb;
    }

    public void setSb(int sb) {
        this.sb = sb;
    }
}
