package track.b.desktop.application.Model;

import java.sql.Date;

public class StockIssuance 
{

    private int issueId;
    private int materialsId;
    private int cleanerId;
    private int userId;
    private int quantity;
    private Date dateIssued;

    private String materialName; // display only, not written to DB directly
    private String cleanerName;  // display only, not written to DB directly
    private String userName;     // display only, not written to DB directly

    public StockIssuance() 
    {
    }

    public StockIssuance(
            int issueId,
            int materialsId,
            int cleanerId,
            int userId,
            int quantity,
            Date dateIssued) 
    {

        this.issueId = issueId;
        this.materialsId = materialsId;
        this.cleanerId = cleanerId;
        this.userId = userId;
        this.quantity = quantity;
        this.dateIssued = dateIssued;
    }

    public int getIssueId() 
    {
        return issueId;
    }

    public int getMaterialsId() 
    {
        return materialsId;
    }

    public int getCleanerId() 
    {
        return cleanerId;
    }

    public int getUserId() 
    {
        return userId;
    }

    public int getQuantity() 
    {
        return quantity;
    }

    public Date getDateIssued() 
    {
        return dateIssued;
    }

    public String getMaterialName() 
    {
        return materialName;
    }

    public String getCleanerName() 
    {
        return cleanerName;
    }

    public String getUserName() 
    {
        return userName;
    }

    public void setIssueId(int issueId) 
    {
        this.issueId = issueId;
    }

    public void setMaterialsId(int materialsId) 
    {
        this.materialsId = materialsId;
    }

    public void setCleanerId(int cleanerId) 
    {
        this.cleanerId = cleanerId;
    }

    public void setUserId(int userId) 
    {
        this.userId = userId;
    }

    public void setQuantity(int quantity) 
    {
        this.quantity = quantity;
    }

    public void setDateIssued(Date dateIssued) 
    {
        this.dateIssued = dateIssued;
    }

    public void setMaterialName(String materialName) 
    {
        this.materialName = materialName;
    }

    public void setCleanerName(String cleanerName) 
    {
        this.cleanerName = cleanerName;
    }

    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    @Override
    public String toString() 
    {
        return issueId + " - " + materialName + " x" + quantity;
    }
}