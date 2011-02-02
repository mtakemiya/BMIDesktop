/**
 * 
 */
package jp.atr.dni.bmi.desktop.neuroshareutils;

/**
 *
 * @author kharada
 * @version 2011/01/28
 */
public class EntityInfo {

    private String entityLabel;
    private long entityType;
    private long itemCount;
    private long dataPosition;
    private String filePath;

    /**
     * Default constructor.
     */
    public EntityInfo() {
        super();
    }

    /**
     * @param entityLabel
     * @param entityType
     * @param itemCount
     */
    public EntityInfo(String entityLabel, long entityType, long itemCount) {
        super();
        if (entityLabel == null) {
            entityLabel = "";
        }
        this.entityLabel = entityLabel.trim();
        this.entityType = entityType;
        this.itemCount = itemCount;
        this.dataPosition=0;
        this.filePath="";
    }

    /**
     * @return the entityLabel
     */
    public String getEntityLabel() {
        return entityLabel;
    }

    /**
     * @param entityLabel the entityLabel to set
     */
    public void setEntityLabel(String entityLabel) {
        this.entityLabel = entityLabel;
    }

    /**
     * @return the entityType
     */
    public long getEntityType() {
        return entityType;
    }

    /**
     * @param entityType the entityType to set
     */
    public void setEntityType(long entityType) {
        this.entityType = entityType;
    }

    /**
     * @return the itemCount
     */
    public long getItemCount() {
        return itemCount;
    }

    /**
     * @param itemCount the itemCount to set
     */
    public void setItemCount(long itemCount) {
        this.itemCount = itemCount;
    }

    /**
     * @return the dataPosition
     */
    public long getDataPosition() {
        return dataPosition;
    }

    /**
     * @param dataPosition the dataPosition to set
     */
    public void setDataPosition(long dataPosition) {
        this.dataPosition = dataPosition;
    }

    /**
     * @return the filePath
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * @param filePath the filePath to set
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

}
