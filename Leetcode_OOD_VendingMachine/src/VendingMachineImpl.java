import java.util.LinkedList;
import java.util.List;

import javax.naming.InitialContext;

import Exception.NotFullPaidException;
import Exception.NotSufficientChangeException;
import Exception.SoldOutException;

public class VendingMachineImpl implements VendingMachine 
{   
	// vending machine status
    private Inventory<Coin> coinInventory;
    private Inventory<Item> itemInventory;  
    private long totalSales;
    
    // customer status
    private Item currentItem;
    private long currentBalance; 
   
    public VendingMachineImpl()
    {
    	totalSales = 0;
    	currentBalance = 0;
    	currentItem = null;
        initInventory();
    }
   
    private void initInventory()
    {   
    	coinInventory = new Inventory<Coin>();
    	itemInventory = new Inventory<Item>(); 
    	
        //initialize machine with 5 coins of each denomination and 5 cans of each Item       
        for (Coin coin : Coin.values())
        {
            coinInventory.put(coin, 5);
        }
       
        for(Item item : Item.values())
        {
            itemInventory.put(item, 5);
        }
    }
   
   @Override
   public long selectItemAndGetPrice(Item item) throws SoldOutException
   {
        if (!itemInventory.hasItem(item))
        {
        	throw new SoldOutException("Sold Out, Please buy another item");
        }
        else
        {
        	currentItem = item;
            return currentItem.getPrice();
        }
    }

   @Override
   public long insertCoin(Coin coin) 
   {
       currentBalance = currentBalance + coin.getDenomination();
       coinInventory.add(coin);
       return currentBalance;
   }

   @Override
   public Bucket<Item, List<Coin>> collectItemAndChange() 
   {
       Item item = collectItem();
       totalSales = totalSales + currentItem.getPrice();
       List<Coin> change = collectChange();
       return new Bucket<Item, List<Coin>>(item, change);
   }
   
   @Override
   public List<Coin> refund()
   {
       List<Coin> refund = getChange(currentBalance);
       updateCashInventory(refund);
       currentBalance = 0;
       currentItem = null;
       return refund;
   }
   
   @Override
   public void reset()
   {
       coinInventory.clear();
       itemInventory.clear();
       totalSales = 0;
       currentItem = null;
       currentBalance = 0;
   }
       
   private Item collectItem() throws NotSufficientChangeException, NotFullPaidException
   {
    	if (!isFullPaid())
    	{
    		long remainingBalance = currentItem.getPrice() - currentBalance;
            throw new NotFullPaidException("Price not full paid, remaining : ", remainingBalance);
    	}
    	
    	if (hasSufficientChangeForAmount(currentBalance - currentItem.getPrice()))
        {
        	throw new NotSufficientChangeException("Not Sufficient change in Inventory");
        }           
        
        itemInventory.deduct(currentItem);
        return currentItem;
    }
    
    private boolean isFullPaid() 
    {
    	return (currentBalance >= currentItem.getPrice()) ? true : false;
    }
   
    private List<Coin> collectChange() 
    {
        long changeAmount = currentBalance - currentItem.getPrice();
        List<Coin> change = getChange(changeAmount);
        updateCashInventory(change);
        currentBalance = 0;
        currentItem = null;
        return change;
    }

    private List<Coin> getChange(long balance) throws NotSufficientChangeException
    {
        List<Coin> changes = new LinkedList<>();
        
        if (balance <= 0)
        {
        	return changes;
        }
        
        while (balance > 0)
        {
            if (balance >= Coin.QUARTER.getDenomination() && coinInventory.hasItem(Coin.QUARTER))
            {
                changes.add(Coin.QUARTER);
                balance = balance - Coin.QUARTER.getDenomination();
            }
            else if (balance >= Coin.DIME.getDenomination() && coinInventory.hasItem(Coin.DIME)) 
            {
                changes.add(Coin.DIME);
                balance = balance - Coin.DIME.getDenomination();
            }
            else if (balance >= Coin.NICKLE.getDenomination() && coinInventory.hasItem(Coin.NICKLE)) 
            {
                changes.add(Coin.NICKLE);
                balance = balance - Coin.NICKLE.getDenomination();
            }
            else if (balance >= Coin.PENNY.getDenomination() && coinInventory.hasItem(Coin.PENNY)) 
            {
                changes.add(Coin.PENNY);
                balance = balance - Coin.PENNY.getDenomination();
            }
            else
            {
                throw new NotSufficientChangeException("NotSufficientChange, Please try another product");
            }
        }
       
        return changes;
    }
   
    private boolean hasSufficientChangeForAmount(long amount)
    {
        boolean hasChange = true;
        
        try
        {
            getChange(amount);
        }
        catch (NotSufficientChangeException nsce)
        {
            return hasChange = false;
        }
       
        return hasChange;
    }

    private void updateCashInventory(List<Coin> change) 
    {
        for (Coin c : change)
        {
            coinInventory.deduct(c);
        }
    }
   
    public long getTotalSales()
    {
        return totalSales;
    }
   
    public void printStats()
    {
        System.out.println("Total Sales : " + totalSales);
        System.out.println("Current Item Inventory : " + itemInventory);
        System.out.println("Current Cash Inventory : " + coinInventory);
    } 
}


