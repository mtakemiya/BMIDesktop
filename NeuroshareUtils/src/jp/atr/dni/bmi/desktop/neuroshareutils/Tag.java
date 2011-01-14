package jp.atr.dni.bmi.desktop.neuroshareutils;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 *
 * @author kharada
 * @version 2011/01/13
 */
@XStreamAlias("tag")
public class Tag {

    private ElemType elemType;
    private long elemLength;

    /**
     * Default constructor.
     */
    public Tag() {
        super();
    }

    /**
     * @param elemType
     * @param elemLength
     */
    public Tag(ElemType elemType, long elemLength) {
        super();
        this.elemType = elemType;
        this.elemLength = elemLength;
    }

    /**
     * @return the elemType
     */
    public ElemType getElemType() {
        return elemType;
    }

    /**
     * @param elemType the elemType to set
     */
    public void setElemType(ElemType elemType) {
        this.elemType = elemType;
    }

    /**
     * @return the elemLength
     */
    public long getElemLength() {
        return elemLength;
    }

    /**
     * @param elemLength the elemLength to set
     */
    public void setElemLength(long elemLength) {
        this.elemLength = elemLength;
    }
}
