package com.saasovation.agilepm.domain.model.team;

import java.util.Date;

/**
 * 为了保证member修改事件的执行顺序导致的bug，对事件的执行事件进行跟踪
 * @author : huanghy
 * @create : 2016/4/27 0027 下午 4:02
 * @since : ${VERSION}
 */
public class MemberChangeTracker {
    private Date enablingOn;
    private Date nameChangedOn;
    private Date emailAddressChangedOn;

    public MemberChangeTracker(Date asOfDate, Date date, Date date1) {

    }

    public boolean canToggleEnabling(Date asOfDate){
        return this.enablingOn().before(asOfDate);
    }


    public MemberChangeTracker enablingOn(Date asOfDate){
        return new MemberChangeTracker(asOfDate,
                nameChangedOn(),
                emailAddressChangedOn());
    }


    public Date enablingOn(){
        return this.enablingOn;
    }
    public Date nameChangedOn(){
        return this.nameChangedOn;
    }

    public Date emailAddressChangedOn(){
        return this.emailAddressChangedOn;
    }
}
