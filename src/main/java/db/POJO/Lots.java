package db.POJO;

import org.apache.log4j.Logger;

public class Lots {
    private static final Logger log = Logger.getLogger(Lots.class);
    private int id;
    //id владельца
    private int id_user_owner;
    //название лота
    private String name;
    //описание лота
    private String comment;
    //начальная цена
    private Double startPrice;
    //текущая цена
    private Double currentPrice;
    //id юзера, который совершил ставку последним
    private int currentUserId;
    //флаг добавления дополнительного времени продолжения аукциона
    private boolean isAddadTime;
    //продолжительность торгов в мс
    private long auctionTimeInMs;
    // дата начала торгов в мс
    private long startTimeInMs;

    public Lots(int id, int id_user_owner, String name, String comment, Double startPrice, Double currentPrice, int currentUserId, boolean isAddedTime, long auctionTimeInMs, long startTimeInMs) {
        this.id = id;
        this.id_user_owner = id_user_owner;
        this.name = name;
        this.comment = comment;
        this.startPrice = startPrice;
        this.currentPrice = currentPrice;
        this.currentUserId = currentUserId;
        this.isAddadTime = isAddedTime;
        this.auctionTimeInMs = auctionTimeInMs;
        this.startTimeInMs = startTimeInMs;
    }

    public int getId()
    {
        return id;
    }

    public int getId_user_owner() {
        return id_user_owner;
    }

    public String getName() {
        return name;
    }

    public String getComment() {
        return comment;
    }

    public Double getStartPrice() {
        return startPrice;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public int getCurrentUserId() {
        return currentUserId;
    }

    public boolean isAddedTime() {
        return isAddadTime;
    }

    public long getAuctionTimeInMs() {
        return auctionTimeInMs;
    }

    public long getStartTimeInMs() {
        return startTimeInMs;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_user_owner(int id_user_owner) {
        this.id_user_owner = id_user_owner;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setStartPrice(Double startPrice) {
        this.startPrice = startPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public void setCurrentUserId(int currentUserId) {
        this.currentUserId = currentUserId;
    }

    public void setAddadTime(boolean addadTime) {
        isAddadTime = addadTime;
    }

    public void setAuctionTimeInMs(long auctionTimeInMs) {
        this.auctionTimeInMs = auctionTimeInMs;
    }

    public void setStartTimeInMs(long startTimeInMs) {
        this.startTimeInMs = startTimeInMs;
    }

    @Override
    public String toString() {
        return "Lots{" +
                "id=" + id +
                ", id_user_owner=" + id_user_owner +
                ", name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                ", startPrice=" + startPrice +
                ", currentPrice=" + currentPrice +
                ", currentUserId=" + currentUserId +
                ", isAddadTime=" + isAddadTime +
                ", auctionTimeInMs=" + auctionTimeInMs +
                ", startTimeInMs=" + startTimeInMs +
                '}';
    }
}
