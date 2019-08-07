import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SqlFunc {
    private static Connection conn;
    private static Statement stmt;
    private static ResultSet rs;


    public static boolean init(){
        String host = "jdbc:mysql://database-1.cnuspg7wr1lq.us-east-2.rds.amazonaws.com:3306/bank";
        String username = "admin";
        String password = "cs591ood";
        try {
            conn = DriverManager.getConnection(host,username,password);
            stmt = conn.createStatement();
            System.out.print("Connect Successfully!\n");
            return true;

        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }

    }

    public static List<Client> getAllCustomer(){
        try {
            String sql;
            sql = "SELECT * FROM Customer";
            rs = stmt.executeQuery(sql);
            List<Client> lst = new ArrayList<Client>();

            while(rs.next()){

                int id  = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String fName = rs.getString("fName");
                String mName = rs.getString("mName");
                String lName = rs.getString("lName");
                Client customer = new Client(fName,mName,lName,username);
                customer.setPassword(password);
                lst.add(customer);

                System.out.print("ID: " + id);
                System.out.print(", Username: " + username);
                System.out.print(", Password: " + password);
                System.out.print(", fName: " + fName);
                System.out.print(", mName: " + mName);
                System.out.print(", lName: " + lName);
                System.out.print("\n");
            }
            return lst;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }

    }

    public static Client getCustomerById(int id){
        try {
            String sql;
            sql = "SELECT * FROM Customer where id = " + id;
            rs = stmt.executeQuery(sql);
            Client customer = null;

            while(rs.next()){
                String username = rs.getString("username");
                String password = rs.getString("password");
                String fName = rs.getString("fName");
                String mName = rs.getString("mName");
                String lName = rs.getString("lName");
                customer = new Client(fName,mName,lName,username);
                customer.setPassword(password);


                System.out.print("ID: " + id);
                System.out.print(", Username: " + username);
                System.out.print(", Password: " + password);
                System.out.print(", fName: " + fName);
                System.out.print(", mName: " + mName);
                System.out.print(", lName: " + lName);
                System.out.print("\n");
            }
            return customer;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }

    }

    public static Client getCustomerByUsername(String username){
        try{
            String sql;
            sql = "SELECT * FROM Customer where username = '" + username + "'";
            rs = stmt.executeQuery(sql);
            Client customer = null;

            while(rs.next()){
                int id = rs.getInt("id");
                String password = rs.getString("password");
                String fName = rs.getString("fName");
                String mName = rs.getString("mName");
                String lName = rs.getString("lName");
                customer = new Client(fName,mName,lName,username,id);
                customer.setPassword(password);


                System.out.print("ID: " + id);
                System.out.print(", Username: " + username);
                System.out.print(", Password: " + password);
                System.out.print(", fName: " + fName);
                System.out.print(", mName: " + mName);
                System.out.print(", lName: " + lName);
                System.out.print("\n");
            }
            return customer;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }

    }

    public static boolean insertCustomer(int id, String username, String password, String fName, String mName, String lName){
        try {
            String sql;
            sql = "INSERT INTO Customer (id,username,password,fName,mName,lName) VALUES " +
                    "(" + id + ",'" + username + "'," + "'" + password + "'," + "'" + fName + "', '" + mName + "', '" + lName +  "')";
            int result = stmt.executeUpdate(sql);
            if(result == 1){
                return true;
            }
            return false;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }

    }

    //need more parameter in account constructor account id,
//    public static List<Account> getAllAccount(){
//        try {
//            String sql;
//            sql = "SELECT * FROM Account";
//            rs = stmt.executeQuery(sql);
//            List<Account> lst = new ArrayList<Account>();
//
//            while(rs.next()){
//                int aid  = rs.getInt("aid");
//                int id = rs.getInt("id");
//                double balance = rs.getDouble("balance");
//                int type = rs.getInt("type");
//                int currency = rs.getInt("currency");
//                double loan = rs.getDouble("loan");
//                Currency c = generateCurrency(currency);
//
//
//                Account account = new Account(balance,c);
//                lst.add(account);
//                System.out.print("AID: " + aid);
//                System.out.print(", ID: " + id);
//                System.out.print(", Balance: " + balance);
//                System.out.print(", Type: " + type);
//                System.out.print(", Currency: " + currency);
//                System.out.print(", Loan: " + loan);
//                System.out.print("\n");
//            }
//            return lst;
//        } catch (SQLException e){
//            e.printStackTrace();
//            return null;
//        }
//
//    }

    //need more parameter in account constructor account id,
    public static List<Account> getAccountByCustomerId(int id){
        try {
            String sql;
            sql = "SELECT * FROM Account where id = " + id;
            rs = stmt.executeQuery(sql);
            List<Account> lst = new ArrayList<Account>();

            while(rs.next()){
                int aid = rs.getInt("aid");
                double balance = rs.getDouble("balance");
                int type = rs.getInt("type");
                int currency = rs.getInt("currency");
                double interest = rs.getDouble("interest");
                double loan = rs.getDouble("loan");
                Currency c = generateCurrency(currency);
                Account account = null;
                // 0 for checking // 1 for saving
                if(type == 0){
                    account = new Account("Checking",balance,c,(""+aid));
                    lst.add(account);
                } else if(type == 1){
                    account = new Account("Savings",balance,c,(""+aid));
                    lst.add(account);
                }else{
                    account = new Account("Securities",balance,c,(""+aid));
                    lst.add(account);
                }

                System.out.print("AID: " + aid);
                System.out.print(", ID: " + id);
                System.out.print(", Balance: " + balance);
                System.out.print(", Type: " + type);
                System.out.print(", Currency: " + currency);
                System.out.print(", Interest: " + interest);
                System.out.print(", Loan: " + loan);
                System.out.print("\n");
            }
            return lst;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public static Account getAccountByAid(int aid){
        try {
            String sql;
            sql = "SELECT * FROM Account where aid = " + aid;
            rs = stmt.executeQuery(sql);
            Account account = null;

            while(rs.next()){
                int id = rs.getInt("id");
                double balance = rs.getDouble("balance");
                int type = rs.getInt("type");
                int currency = rs.getInt("currency");
                double interest = rs.getDouble("interest");
                double loan = rs.getDouble("loan");
                //need more parameter in account constructor account id,
                Currency c = generateCurrency(currency);
                if(type == 0){
                    account = new Account("Checking",balance,c,(""+aid));
                }else if(type == 1){
                    account = new Account("Savings",balance,c,(""+aid));
                }else{
                    account = new Account("Securities",balance,c,(""+aid));
                }

                System.out.print("AID: " + aid);
                System.out.print(", ID: " + id);
                System.out.print(", Balance: " + balance);
                System.out.print(", Type: " + type);
                System.out.print(", Currency: " + currency);
                System.out.print(", Interest: " + interest);
                System.out.print(", Loan: " + loan);
                System.out.print("\n");
            }
            return account;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }

    }

    public static boolean insertAccount(int aid,int id,double balance,String typeAccount,Currency currency, int interest,double loan){
        //0 for checking, 1 for saving 2 for Savings
        System.out.print("AID: " + aid);
        System.out.print(", ID: " + id);
        System.out.print(", Balance: " + balance);
        System.out.print(", Type: " + typeAccount);
        System.out.print(", Currency: " + currency.getAcronym());
        System.out.print(", Interest: " + interest);
        System.out.print(", Loan: " + loan);
        System.out.print("\n");
        int type = 0;
        if(typeAccount.equals("Savings")){
            type = 1;
        }else if (typeAccount.equals("Securities")){
            type = 2;
        }else{
            type = 0;
        }
        int c = generateIntCurrency(currency);
        try {
            String sql;
            sql = "INSERT INTO Account (aid,id,balance,type,currency,interest,loan) VALUES " +
                    "(" + aid + "," + id + "," + balance + "," + type + "," + c + "," + interest + "," + loan + ")";
            int result = stmt.executeUpdate(sql);
            if(result == 1){
                return true;
            }
            return false;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }

    }

    public static boolean updateAccountCurrencyByAid(int aid, int currency){
        try {
            String sql;
            sql = "UPDATE Account SET currency = " + currency + " WHERE aid = " + aid;
            int result = stmt.executeUpdate(sql);
            if(result == 1){
                return true;
            }
            return false;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteAccountByAid(int aid){
        try {
            String sql;
            sql = "DELETE FROM Account WHERE aid = " + aid;
            int result = stmt.executeUpdate(sql);
            if(result == 1){
                return true;
            }
            return false;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteboughtBondById(String id){
        try {
            String sql;
            sql = "DELETE FROM boughtBond WHERE id = '" + id + "'";
            int result = stmt.executeUpdate(sql);
            if(result == 1){
                return true;
            }
            return false;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }



    //    public static List<Stock> getAllStock(){
//        try {
//            String sql;
//            sql = "SELECT * FROM Stock";
//            rs = stmt.executeQuery(sql);
//            List<Stock> lst = new ArrayList<Stock>();
//
//            while(rs.next()){
//                String ticker  = rs.getString("ticker");
//                double price = rs.getDouble("price");
//                String name = rs.getString("name");
//                Timestamp date = rs.getTimestamp("date");
//                Stock stock = new Stock(ticker,price,name,date);
//                lst.add(stock);
//                System.out.print("TICKER: " + ticker);
//                System.out.print(", PRICE: " + price);
//                System.out.print(", NAME: " + name);
//                System.out.print(", DATE: " + date);
//                System.out.print("\n");
//            }
//            return lst;
//        } catch (SQLException e){
//            e.printStackTrace();
//            return null;
//        }
//
//    }
//
//    public static Stock getStockByTicker(String ticker){
//        try {
//            String sql;
//            sql = "SELECT * FROM Stock where ticker = '" + ticker + "'";
//            rs = stmt.executeQuery(sql);
//            Stock stock = null;
//
//            while(rs.next()){
//                double price = rs.getDouble("price");
//                String name = rs.getString("name");
//                Timestamp date = rs.getTimestamp("date");
//                stock = new Stock(ticker,price,name,date);
//                System.out.print("TICKER: " + ticker);
//                System.out.print(", PRICE: " + price);
//                System.out.print(", NAME: " + name);
//                System.out.print(", DATE: " + date);
//                System.out.print("\n");
//            }
//            return stock;
//        } catch (SQLException e){
//            e.printStackTrace();
//            return null;
//        }
//
//    }
//
//    public static boolean insertStock(String ticker,double price,String name,Timestamp date){
//        try {
//            String sql;
//            sql = "INSERT INTO Stock (ticker,price,name,date) VALUES " +
//                    "('" + ticker + "'," + price + "," + "'" + name + "'," + "'" + date + "')";
//            int result = stmt.executeUpdate(sql);
//            if(result == 1){
//                return true;
//            }
//            return false;
//        } catch (SQLException e){
//            e.printStackTrace();
//            return false;
//        }
//
//    }
//
//
    public static ArrayList<Bond> getAllBond(){
        try {
            String sql;
            sql = "SELECT * FROM Bond";
            rs = stmt.executeQuery(sql);
            ArrayList<Bond> lst = new ArrayList<Bond>();

            while(rs.next()){
                String name  = rs.getString("name");
                int type = rs.getInt("type");
                Double interest = rs.getDouble("interest");
                Bond bond = new Bond(name,interest,type);
                lst.add(bond);

                System.out.print("NAME: " + name);
                System.out.print(", TYPE: " + type);
                System.out.print(", INTEREST: " + interest);
                System.out.print("\n");
            }
            return lst;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }

    }
//
//    public static List<boughtStock> showStockBought(){
//        try {
//            String sql;
//            sql = "SELECT * FROM boughtstock";
//            rs = stmt.executeQuery(sql);
//            List<boughtStock> lst = new ArrayList<boughtStock>();
//            while(rs.next()){
//                int customerid  = rs.getInt("customerid");
//                String ticker = rs.getString("ticker");
//                int numshare = rs.getInt("numshare");
//                Timestamp date = rs.getTimestamp("date");
//                int sb = rs.getInt("sb");
//                double price = rs.getDouble("price");
//                boughtStock boughtstock = new boughtStock(customerid,ticker,numshare,date,sb,price);
//                lst.add(boughtstock);
//                System.out.print("CUSTOMERID: " + customerid);
//                System.out.print(", TICKER: " + ticker);
//                System.out.print(", NUMSHARE: " + numshare);
//                System.out.print(", DATE: " + date);
//                System.out.print(", SELL/BUY: " + sb);
//                System.out.print(", PRICE: " + price);
//                System.out.print("\n");
//            }
//            return lst;
//        } catch (SQLException e){
//            e.printStackTrace();
//            return null;
//        }
//
//    }
//
    public static List<boughtBond> getAllBondBought(){
        try {
            String sql;
            sql = "SELECT * FROM boughtBond";
            rs = stmt.executeQuery(sql);
            List<boughtBond> lst = new ArrayList<boughtBond>();

            while(rs.next()){
                int customerid  = rs.getInt("customerid");
                String id  = rs.getString("id");
                int type = rs.getInt("type");
                double boughtAmount = rs.getDouble("boughtAmount");
                LocalDate date = rs.getDate("date").toLocalDate();
                int sb = rs.getInt("sb");
                String bondName = rs.getString("bondName");
                boughtBond boughtbond = new boughtBond(customerid,id,bondName,boughtAmount,date,sb,type);
                lst.add(boughtbond);

                System.out.print("CUSTOMERID: " + customerid);
                System.out.print("ID: " + id);
                System.out.print(", TYPE: " + type);
                System.out.print(", BOUGHTAMOUNT: " + boughtAmount);
                System.out.print(", DATE: " + date);
                System.out.print(", SELL/BUY: " + sb);
                System.out.print(", BONDNAME: " + bondName);
                System.out.print("\n");

            }
            return lst;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }

    }
//
//
//
//
//
//
//
    public static Bond getBondByName(String name){
        try {
            String sql;
            sql = "SELECT * FROM Bond where name = '" + name + "'";
            rs = stmt.executeQuery(sql);
            Bond bond = null;

            while(rs.next()){
                int type = rs.getInt("type");
                Double interest = rs.getDouble("interest");
                bond = new Bond(name,interest,type);

                System.out.print("NAME: " + name);
                System.out.print(", TYPE: " + type);
                System.out.print(", INTEREST: " + interest);
                System.out.print("\n");
            }
            return bond;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }

    }
//
//    public static Bond getBondByType(int type){
//        try {
//            String sql;
//            sql = "SELECT * FROM Bond where type = " + type;
//            rs = stmt.executeQuery(sql);
//            Bond bond = null;
//
//            while(rs.next()){
//                String name = rs.getString("name");
//                Double interest = rs.getDouble("interest");
//                bond = new Bond(name,type,interest);
//
//                System.out.print("NAME: " + name);
//                System.out.print(", TYPE: " + type);
//                System.out.print(", INTEREST: " + interest);
//                System.out.print("\n");
//            }
//            return bond;
//        } catch (SQLException e){
//            e.printStackTrace();
//            return null;
//        }
//
//    }
//
//    public static boughtStock getStockBoughtByCustomerId(int customerid){
//        try {
//            String sql;
//            sql = "SELECT * FROM boughtstock where customerid = " + customerid;
//            rs = stmt.executeQuery(sql);
//            boughtStock boughtstock = null;
//            while(rs.next()){
//                String ticker = rs.getString("ticker");
//                int numshare = rs.getInt("numshare");
//                Timestamp date = rs.getTimestamp("date");
//                int sb = rs.getInt("sb");
//                double price = rs.getDouble("price");
//                boughtstock = new boughtStock(customerid,ticker,numshare,date,sb,price);
//                System.out.print("CUSTOMERID: " + customerid);
//                System.out.print(", TICKER: " + ticker);
//                System.out.print(", NUMSHARE: " + numshare);
//                System.out.print(", DATE: " + date);
//                System.out.print(", SELL/BUY: " + sb);
//                System.out.print(", Price: " + price);
//                System.out.print("\n");
//            }
//            return boughtstock;
//        } catch (SQLException e){
//            e.printStackTrace();
//            return null;
//        }
//
//    }
//
    public static ArrayList<boughtBond> getBondBoughtByCustomerId(int customerid){
        try {
            String sql;
            sql = "SELECT * FROM boughtBond where customerid = " + customerid;
            rs = stmt.executeQuery(sql);
            boughtBond boughtbond = null;
            ArrayList<boughtBond> lst = new ArrayList<boughtBond>();
            while(rs.next()){
                String id = rs.getString("id");
                int type = rs.getInt("type");
                double boughtAmount = rs.getDouble("boughtAmount");
                LocalDate date = rs.getDate("date").toLocalDate();
                int sb = rs.getInt("sb");
                String bondName = rs.getString("bondName");
                boughtbond = new boughtBond(customerid,id,bondName,boughtAmount,date,sb,type);
                lst.add(boughtbond);
                System.out.print("CUSTOMERID: " + customerid);
                System.out.print("ID: " + id);
                System.out.print(", TYPECHOSE: " + type);
                System.out.print(", BOUGHTAMOUNT: " + boughtAmount);
                System.out.print(", DATE: " + date);
                System.out.print(", SELL/BUY: " + sb);
                System.out.print(", BONDNAME: " + bondName);
                System.out.print("\n");

            }
            return lst;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }

    }
//
//
//
//
//
//
//
//    public static boolean insertBond(String name,int type,double interest){
//        try {
//            String sql;
//            sql = "INSERT INTO Bond (name,type,interest) VALUES " +
//                    "('" + name + "'," + type + "," + interest + ")";
//            int result = stmt.executeUpdate(sql);
//            if(result == 1){
//                  return true;
//            }
//            return false;
//        } catch (SQLException e){
//            e.printStackTrace();
//            return false;
//        }
//
//    }
//
    public static boolean insertBondBought(int customerid, String bondName, double boughtAmount, LocalDate date, int sb, int type, String id){
        try {
            String sql;
            sql = "INSERT INTO boughtBond (customerid,bondName,boughtAmount,date,sb,type,id) VALUES " +
                    "(" + customerid + ",'" + bondName + "'," + boughtAmount + ",'" + java.sql.Date.valueOf(date) + "',"+ sb +"," + type + ",'" + id +"')";
            int result = stmt.executeUpdate(sql);
            if(result == 1){
                  return true;
            }
            return false;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }

    }
//
//    public static boolean insertStockBought(int customerid, String ticker, int numshare, Timestamp date, int sb, double price){
//        try {
//            String sql;
//            sql = "INSERT INTO boughtStock (customerid,ticker,numshare,date,sb,price) VALUES " +
//                    "(" + customerid + ", '" + ticker + "'," + numshare + ",'" + date + "',"+ sb +"', '" + price + "')";
//            int result = stmt.executeUpdate(sql);
//            if(result == 1){
//                  return true;
//            }
//            return false;
//        } catch (SQLException e){
//            e.printStackTrace();
//            return false;
//        }
//
//    }

    public static boolean updateAccountBalanceByAid(int aid,double balance){
        try {
            String sql;
            sql = "UPDATE Account SET balance = " + balance + " WHERE aid = " + aid;
            int result = stmt.executeUpdate(sql);
            if(result == 1){
                  return true;
            }
            return false;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }

    }

    public static boolean updateAccountLoanByAid(int aid,double balance){
        try {
            String sql;
            sql = "UPDATE Account SET loan = " + balance + " WHERE aid = " + aid;
            int result = stmt.executeUpdate(sql);
            if(result == 1){
                return true;
            }
            return false;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }

    }

    public static boolean updateStockPriceByTicker(String ticker,double price){
        try {
            String sql;
            sql = "UPDATE Stock SET price = " + price + " WHERE ticker = '" + ticker + "'";
            int result = stmt.executeUpdate(sql);
            if(result == 1){
                  return true;
            }
            return false;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }

    }

    public static boolean insertTransaction(int aid,double amount, double newBalance, String reference,int type, Date date){
        try {
            String d = "";
            d = date.getYear() + "-" + date.getMonth() + "-" + date.getDay();
            String sql;
            sql = "INSERT INTO Transaction (aid,amount,newBalance,reference,type,date) VALUES " +
                    "(" + aid + "," + amount + "," + newBalance + ",'" + reference + "',"+ type +",'" + d + "')";
            int result = stmt.executeUpdate(sql);
            if(result == 1){
                  return true;
            }
            return false;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }

    }

    public static List<Transaction> getAllTransactionByAccountid(int aid){
        try {
            String sql;
            sql = "SELECT * FROM Transaction where aid = " + aid;
            rs = stmt.executeQuery(sql);
            List<Transaction> lst = new ArrayList<Transaction>();

            while(rs.next()){
                String date = rs.getString("date");
                double amount = rs.getDouble("amount");
                double newBalance = rs.getDouble("newBalance");
                String reference = rs.getString("reference");
                int type = rs.getInt("type");
                String[] d = date.split("-");
                Date tmpDate = new Date(Integer.parseInt(d[1]),Integer.parseInt(d[2]),Integer.parseInt(d[0]));
                String typeString = "";
                if(type == 0){
                    typeString = "IN";
                } else {
                    typeString = "OUT";
                }
                Transaction tmp = new Transaction(tmpDate,newBalance,""+aid,amount,reference,typeString);
                lst.add(tmp);

                // 输出数据
                System.out.print("Date: " + date);
                System.out.print(", Amount: " + amount);
                System.out.print(", newBalance: " + newBalance);
                System.out.print(", reference: " + reference);
                System.out.print(", type: " + type);
                System.out.print("\n");
            }
            return lst;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public static List<Transaction> getAllTransaction(){
        try {
            String sql;
            sql = "SELECT * FROM Transaction";
            rs = stmt.executeQuery(sql);
            List<Transaction> lst = new ArrayList<Transaction>();

            while(rs.next()){
                int aid = rs.getInt("aid");
                String date = rs.getString("date");
                double amount = rs.getDouble("amount");
                double newBalance = rs.getDouble("newBalance");
                String reference = rs.getString("reference");
                int type = rs.getInt("type");
                String[] d = date.split("-");
                Date tmpDate = new Date(Integer.parseInt(d[1]),Integer.parseInt(d[2]),Integer.parseInt(d[0]));
                String typeString = "";
                if(type == 0){
                    typeString = "IN";
                } else {
                    typeString = "OUT";
                }
                Transaction tmp = new Transaction(tmpDate,newBalance,""+aid,amount,reference,typeString);
                lst.add(tmp);

                // 输出数据
                System.out.print("Date: " + date);
                System.out.print(", Aid: " + aid);
                System.out.print(", Amount: " + amount);
                System.out.print(", newBalance: " + newBalance);
                System.out.print(", reference: " + reference);
                System.out.print(", type: " + type);
                System.out.print("\n");
            }
            return lst;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }



    public static boolean close(){
        try{
            stmt.close();
            conn.close();
            System.out.println("Disconnect Succesfully!\n");
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public static Currency generateCurrency(int currency){
        Currency c = null;
        switch (currency){
            case 0:
                c = new Currency("USD", "Dollars");
                break;
            case 1:
                c = new Currency("RUB", "Rubles");
                break;
            case 2:
                c = new Currency("CNY", "Yen");
                break;
            case 3:
                c = new Currency("GBP", "Pounds");
                break;
            default:
                break;
        }
        return c;
    }

    public static int generateIntCurrency(Currency currency){
        int c = 0;
        switch (currency.getAcronym()){
            case "USD":
                c = 0;
                break;
            case "RUB":
                c = 1;
                break;
            case "CNY":
                c = 2;
                break;
            case "GBP":
                c = 3;
                break;
            default:
                break;
        }
        return c;
    }

    public static String bondType(int type){
        String t = "";
        switch (type){
            case 0:
                t = "One Week";
                break;
            case 1:
                t = "One Month";
                break;
            case 2:
                t = "Three Month";
                break;
            default:
                break;
        }
        return t;
    }

    public static int bondTypeTransfer(String type){
        int t = 0;
        switch (type){
            case "One Week":
                t = 0;
                break;
            case "One Month":
                t = 1;
                break;
            case "Three Month":
                t = 2;
                break;
            default:
                break;
        }
        return t;
    }

    public static boolean checkUserPass(String username, String password, List<Client> lst) {
        for (int i = 0; i < lst.size(); i++) {
            if (lst.get(i).checkLogin(username)) {
                if (lst.get(i).checkPassword(password))
                    return true;
            }
        }
        return false;
    }

    public static String displayBond(List<boughtBond> lst) { // USE WHEN DISPLAYING YOUR BONDS
        Bond tmp = null;
        String str = "";
        for(int i = 0; i < lst.size(); i++){
            tmp = SqlFunc.getBondByName(lst.get(i).getBondName());
            str += lst.get(i).getBondName() + " | ID: " + lst.get(i).getId() + " | $"  + lst.get(i).getBoughtAmount()
                    + " | " + "at "+ tmp.getInterest() +  " interest | Maturity: " + lst.get(i).getDate() + "\n";
        }
        return str;
    }
}
