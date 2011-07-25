package jp.atr.dni.bmi.desktop.model.api;

import jp.atr.dni.bmi.desktop.model.api.data.APIData;

/**
 *
 * @author Makoto Takemiya - [武宮 誠] <br />
 * <a href="http://www.atr.jp">ATR - [株式会社・国際電氣通信基礎技術研究所]</a>
 *
 * @version 2011/07/22
 */
public interface Channel<E> {

    public int getId();

    /**
     * @return the type
     */
    public ChannelType getType();

    /**
     * 
     * @return the label
     */
    public String getLabel();

    /**
     * @param label the label to set
     */
    public void setLabel(String label);

    /**
     * @return the itemCount
     */
    public long getItemCount();

    /**
     * @param itemCount the itemCount to set
     */
    public void setItemCount(long itemCount);
    
    public String getURI();

    public APIData getData();
}
