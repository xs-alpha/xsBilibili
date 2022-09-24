package com.xiaosheng.video.facade.support;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;


public class BeanConvertorUtils {
    private static DozerBeanMapper dozer = new DozerBeanMapper();

    public BeanConvertorUtils() {
    }

    public static <T> T map(Object source, Class<T> destinationClass) {
        return null == source ? null : dozer.map(source, destinationClass);
    }

    public static <T> List<T> copyList(Collection sourceList, Class<T> destinationClass) {
        List<T> destinationList = new ArrayList();
        if (null != sourceList) {
            Iterator var3 = sourceList.iterator();

            while (var3.hasNext()) {
                Object sourceObject = var3.next();
                T destinationObject = dozer.map(sourceObject, destinationClass);
                destinationList.add(destinationObject);
            }
        }

        return destinationList;
    }

    public static void copy(Object source, Object destinationObject) {
        dozer.map(source, destinationObject);
    }
}
