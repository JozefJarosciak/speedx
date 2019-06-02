package com.avos.avoscloud;

import com.alibaba.fastjson.JSON;
import com.avos.avoscloud.callback.AVFriendshipCallback;
import com.loopj.android.http.RequestParams;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class AVFriendshipQuery<T extends AVUser> {
    QueryConditions conditions;
    Class<T> userClazz;
    String userId;

    AVFriendshipQuery(String str) {
        this(str, null);
    }

    AVFriendshipQuery(String str, Class<T> cls) {
        this.userId = str;
        this.userClazz = cls;
        this.conditions = new QueryConditions();
    }

    protected void getInBackground(String str, boolean z, final AVFriendshipCallback aVFriendshipCallback) {
        String format = String.format("users/%s/followersAndFollowees", new Object[]{str});
        this.conditions.assembleParameters();
        PaasClient.storageInstance().getObject(format, new RequestParams(this.conditions.getParameters()), z, null, new GenericObjectCallback() {
            public void onSuccess(String str, AVException aVException) {
                Object obj;
                Throwable th;
                if (AVUtils.isBlankContent(str)) {
                    aVException = new AVException(101, "Object is not found.");
                    obj = null;
                } else {
                    try {
                        AVFriendship aVFriendship = new AVFriendship();
                        try {
                            AVFriendshipResponse aVFriendshipResponse = (AVFriendshipResponse) JSON.parseObject(str, AVFriendshipResponse.class);
                            if (AVFriendshipQuery.this.userClazz != null) {
                                AVObject newAVUser;
                                List followers = aVFriendship.getFollowers();
                                List followees = aVFriendship.getFollowees();
                                for (Map map : aVFriendshipResponse.followers) {
                                    AVObject newAVUser2 = AVUser.newAVUser(AVFriendshipQuery.this.userClazz, null);
                                    AVUtils.copyPropertiesFromMapToAVObject((Map) map.get(AVUser.FOLLOWER_TAG), newAVUser2);
                                    followers.add(newAVUser2);
                                    if (aVFriendship.getUser() == null) {
                                        newAVUser = AVUser.newAVUser(AVFriendshipQuery.this.userClazz, null);
                                        AVUtils.copyPropertiesFromMapToAVObject((Map) map.get("user"), newAVUser);
                                        aVFriendship.setUser(newAVUser);
                                    }
                                }
                                aVFriendship.setFollowers(followers);
                                for (Map map2 : aVFriendshipResponse.followees) {
                                    newAVUser = AVUser.newAVUser(AVFriendshipQuery.this.userClazz, null);
                                    AVUtils.copyPropertiesFromMapToAVObject((Map) map2.get(AVUser.FOLLOWEE_TAG), newAVUser);
                                    followees.add(newAVUser);
                                }
                                aVFriendship.setFollowees(followees);
                            }
                            AVFriendship aVFriendship2 = aVFriendship;
                        } catch (Throwable e) {
                            th = e;
                            obj = aVFriendship;
                            if (aVFriendshipCallback != null) {
                                aVFriendshipCallback.internalDone(null, AVErrorUtils.createException(th, str));
                            }
                            if (aVFriendshipCallback == null) {
                                aVFriendshipCallback.internalDone(obj, aVException);
                            }
                        }
                    } catch (Throwable e2) {
                        th = e2;
                        obj = null;
                        if (aVFriendshipCallback != null) {
                            aVFriendshipCallback.internalDone(null, AVErrorUtils.createException(th, str));
                        }
                        if (aVFriendshipCallback == null) {
                            aVFriendshipCallback.internalDone(obj, aVException);
                        }
                    }
                }
                if (aVFriendshipCallback == null) {
                    aVFriendshipCallback.internalDone(obj, aVException);
                }
            }

            public void onFailure(Throwable th, String str) {
                if (aVFriendshipCallback != null) {
                    aVFriendshipCallback.internalDone(null, AVErrorUtils.createException(th, str));
                }
            }
        });
    }

    public AVFriendship get() throws AVException {
        final Object[] objArr = new Object[]{null};
        getInBackground(this.userId, true, new AVFriendshipCallback() {
            public void done(AVFriendship aVFriendship, AVException aVException) {
                if (aVException == null) {
                    objArr[0] = aVFriendship;
                } else {
                    AVExceptionHolder.add(aVException);
                }
            }

            protected boolean mustRunOnUIThread() {
                return false;
            }
        });
        if (!AVExceptionHolder.exists()) {
            return (AVFriendship) objArr[0];
        }
        throw AVExceptionHolder.remove();
    }

    public void getInBackground(AVFriendshipCallback aVFriendshipCallback) {
        getInBackground(this.userId, false, aVFriendshipCallback);
    }

    public int getLimit() {
        return this.conditions.getLimit();
    }

    public AVFriendshipQuery<T> setLimit(int i) {
        this.conditions.setLimit(i);
        return this;
    }

    public AVFriendshipQuery<T> limit(int i) {
        setLimit(i);
        return this;
    }

    public AVFriendshipQuery<T> skip(int i) {
        setSkip(i);
        return this;
    }

    public int getSkip() {
        return this.conditions.getSkip();
    }

    public AVFriendshipQuery<T> setSkip(int i) {
        this.conditions.setSkip(i);
        return this;
    }

    public String getOrder() {
        return this.conditions.getOrder();
    }

    public AVFriendshipQuery<T> setOrder(String str) {
        this.conditions.setOrder(str);
        return this;
    }

    public AVFriendshipQuery<T> order(String str) {
        setOrder(str);
        return this;
    }

    public AVFriendshipQuery<T> addAscendingOrder(String str) {
        this.conditions.addAscendingOrder(str);
        return this;
    }

    public AVFriendshipQuery<T> addDescendingOrder(String str) {
        this.conditions.addDescendingOrder(str);
        return this;
    }

    public AVFriendshipQuery<T> include(String str) {
        this.conditions.include(str);
        return this;
    }

    public AVFriendshipQuery<T> selectKeys(Collection<String> collection) {
        this.conditions.selectKeys(collection);
        return this;
    }

    public AVFriendshipQuery<T> orderByAscending(String str) {
        this.conditions.orderByAscending(str);
        return this;
    }

    public AVFriendshipQuery<T> orderByDescending(String str) {
        this.conditions.orderByDescending(str);
        return this;
    }

    public AVFriendshipQuery<T> whereContainedIn(String str, Collection<? extends Object> collection) {
        this.conditions.whereContainedIn(str, collection);
        return this;
    }

    public AVFriendshipQuery<T> whereContains(String str, String str2) {
        this.conditions.whereContains(str, str2);
        return this;
    }

    public AVFriendshipQuery<T> whereSizeEqual(String str, int i) {
        this.conditions.whereSizeEqual(str, i);
        return this;
    }

    public AVFriendshipQuery<T> whereContainsAll(String str, Collection<?> collection) {
        this.conditions.whereContainsAll(str, collection);
        return this;
    }

    public AVFriendshipQuery<T> whereDoesNotExist(String str) {
        this.conditions.whereDoesNotExist(str);
        return this;
    }

    public AVFriendshipQuery<T> whereEndsWith(String str, String str2) {
        this.conditions.whereEndsWith(str, str2);
        return this;
    }

    public AVFriendshipQuery<T> whereEqualTo(String str, Object obj) {
        if (obj instanceof AVObject) {
            addWhereItem(str, QueryOperation.EQUAL_OP, AVUtils.mapFromPointerObject((AVObject) obj));
        } else {
            addWhereItem(str, QueryOperation.EQUAL_OP, obj);
        }
        return this;
    }

    private AVFriendshipQuery<T> addWhereItem(QueryOperation queryOperation) {
        this.conditions.addWhereItem(queryOperation);
        return this;
    }

    private AVFriendshipQuery<T> addOrItems(QueryOperation queryOperation) {
        this.conditions.addOrItems(queryOperation);
        return this;
    }

    protected AVFriendshipQuery<T> addWhereItem(String str, String str2, Object obj) {
        return addWhereItem(new QueryOperation(str, str2, obj));
    }

    public AVFriendshipQuery<T> whereExists(String str) {
        this.conditions.whereExists(str);
        return this;
    }

    public AVFriendshipQuery<T> whereGreaterThan(String str, Object obj) {
        this.conditions.whereGreaterThan(str, obj);
        return this;
    }

    public AVFriendshipQuery<T> whereGreaterThanOrEqualTo(String str, Object obj) {
        this.conditions.whereGreaterThanOrEqualTo(str, obj);
        return this;
    }

    public AVFriendshipQuery<T> whereLessThan(String str, Object obj) {
        this.conditions.whereLessThan(str, obj);
        return this;
    }

    public AVFriendshipQuery<T> whereLessThanOrEqualTo(String str, Object obj) {
        this.conditions.whereLessThanOrEqualTo(str, obj);
        return this;
    }

    public AVFriendshipQuery<T> whereMatches(String str, String str2) {
        this.conditions.whereMatches(str, str2);
        return this;
    }

    public AVFriendshipQuery<T> whereMatches(String str, String str2, String str3) {
        this.conditions.whereMatches(str, str2, str3);
        return this;
    }

    public AVFriendshipQuery<T> whereNear(String str, AVGeoPoint aVGeoPoint) {
        this.conditions.whereNear(str, aVGeoPoint);
        return this;
    }

    public AVFriendshipQuery<T> whereNotContainedIn(String str, Collection<? extends Object> collection) {
        this.conditions.whereNotContainedIn(str, collection);
        return this;
    }

    public AVFriendshipQuery<T> whereNotEqualTo(String str, Object obj) {
        this.conditions.whereNotEqualTo(str, obj);
        return this;
    }

    public AVFriendshipQuery<T> whereStartsWith(String str, String str2) {
        this.conditions.whereStartsWith(str, str2);
        return this;
    }

    public AVFriendshipQuery<T> whereWithinGeoBox(String str, AVGeoPoint aVGeoPoint, AVGeoPoint aVGeoPoint2) {
        this.conditions.whereWithinGeoBox(str, aVGeoPoint, aVGeoPoint2);
        return this;
    }

    public AVFriendshipQuery<T> whereWithinKilometers(String str, AVGeoPoint aVGeoPoint, double d) {
        this.conditions.whereWithinKilometers(str, aVGeoPoint, d);
        return this;
    }

    public AVFriendshipQuery<T> whereWithinMiles(String str, AVGeoPoint aVGeoPoint, double d) {
        this.conditions.whereWithinMiles(str, aVGeoPoint, d);
        return this;
    }

    public AVFriendshipQuery<T> whereWithinRadians(String str, AVGeoPoint aVGeoPoint, double d) {
        this.conditions.whereWithinRadians(str, aVGeoPoint, d);
        return this;
    }
}
