package track.b.desktop.application.Controller;

import track.b.desktop.application.Data.StockIssuanceRepository;
import track.b.desktop.application.Model.Material;
import track.b.desktop.application.Model.StockIssuance;

import java.sql.Date;
import java.util.ArrayList;

public class StockIssuanceController 
{

    private final StockIssuanceRepository repository;
    private final MaterialController materialController;

    public StockIssuanceController() 
    {
        repository = new StockIssuanceRepository();
        materialController = new MaterialController();
    }

    public ArrayList<StockIssuance> getAllIssuances() 
    {
        return repository.findAll();
    }

    public StockIssuance issueStock(
            int materialsId,
            int cleanerId,
            int userId,
            int quantity) 
    {

        if (quantity <= 0) 
        {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }

        Material material = materialController.findMaterialById(materialsId);

        if (material == null) 
        {
            throw new IllegalArgumentException("Material not found.");
        }

        if (quantity > material.getQuantity()) 
        {
            throw new IllegalArgumentException(
                    "Cannot issue " + quantity + " units — only "
                    + material.getQuantity() + " in stock."
            );
        }

        int newId = repository.findMaxId() + 1;

        StockIssuance issuance = new StockIssuance(
                newId,
                materialsId,
                cleanerId,
                userId,
                quantity,
                new Date(System.currentTimeMillis())
        );

        repository.insert(issuance);

        materialController.decreaseStock(materialsId, quantity);

        return issuance;
    }

    public ArrayList<StockIssuance> getIssuanceHistoryForCleaner(int cleanerId) 
    {

        ArrayList<StockIssuance> results = new ArrayList<>();

        for (StockIssuance issuance : getAllIssuances()) 
        {
            if (issuance.getCleanerId() == cleanerId) 
            {
                results.add(issuance);
            }
        }

        return results;
    }

    public ArrayList<StockIssuance> getIssuanceHistoryForMaterial(int materialsId) 
    {

        ArrayList<StockIssuance> results = new ArrayList<>();

        for (StockIssuance issuance : getAllIssuances()) 
        {
            if (issuance.getMaterialsId() == materialsId) 
            {
                results.add(issuance);
            }
        }

        return results;
    }
}