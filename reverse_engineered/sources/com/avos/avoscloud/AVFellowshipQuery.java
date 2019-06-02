package com.avos.avoscloud;

import com.alibaba.fastjson.JSON;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class AVFellowshipQuery<T extends AVUser> extends AVQuery<T> {
    private String friendshipTag;

    AVFellowshipQuery(String str, Class<T> cls) {
        super(str, cls);
    }

    void setFriendshipTag(String str) {
        this.friendshipTag = str;
    }

    String getFriendshipTag() {
        return this.friendshipTag;
    }

    private void processResultList(Map[] mapArr, List<T> list, String str) throws Exception {
        for (Map map : mapArr) {
            if (!(map == null || map.isEmpty())) {
                AVObject aVObject;
                if (getClazz() != null) {
                    aVObject = (AVUser) getClazz().newInstance();
                } else {
                    aVObject = (AVUser) AVUtils.objectFromClassName(getClassName());
                }
                if (!(map.get(str) == null || ((Map) map.get(str)).isEmpty())) {
                    AVUtils.copyPropertiesFromMapToAVObject((Map) map.get(str), aVObject);
                    list.add(aVObject);
                }
            }
        }
    }

    private List<T> processResultByTag(String str, String str2) throws Exception {
        if (AVUtils.isBlankString(str)) {
            return Collections.EMPTY_LIST;
        }
        List<T> linkedList = new LinkedList();
        processResultList(((AVFollowResponse) JSON.parseObject(str, new AVFollowResponse().getClass())).results, linkedList, str2);
        return linkedList;
    }

    protected List<T> processResults(String str) throws Exception {
        if (AVUtils.isBlankContent(str)) {
            return Collections.emptyList();
        }
        return processResultByTag(str, this.friendshipTag);
    }
}
