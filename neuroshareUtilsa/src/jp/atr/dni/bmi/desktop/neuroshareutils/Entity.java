/**
 * 
 */
package jp.atr.dni.bmi.desktop.neuroshareutils;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 *
 * @author kharada
 * @version 2011/01/13
 */
@XStreamAlias("entity")
public abstract class Entity {

    private Tag tag;
    @XStreamAlias("entityInfo")
    @XStreamAsAttribute
    private EntityInfo entityInfo;

    /**
     * @param tag
     * @param entityInfo
     */
    public Entity(Tag tag, EntityInfo entityInfo) {
        super();
        this.tag = tag;
        this.entityInfo = entityInfo;
    }

    /**
     * @return the tag
     */
    public Tag getTag() {
        return tag;
    }

    /**
     * @param tag the tag to set
     */
    public void setTag(Tag tag) {
        this.tag = tag;
    }

    /**
     * @return the entityInfo
     */
    public EntityInfo getEntityInfo() {
        return entityInfo;
    }

    /**
     * @param entityInfo the entityInfo to set
     */
    public void setEntityInfo(EntityInfo entityInfo) {
        this.entityInfo = entityInfo;
    }
}
