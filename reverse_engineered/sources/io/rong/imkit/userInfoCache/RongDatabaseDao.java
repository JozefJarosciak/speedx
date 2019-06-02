package io.rong.imkit.userInfoCache;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import io.rong.common.RLog;
import io.rong.imkit.model.GroupUserInfo;
import io.rong.imlib.model.Discussion;
import io.rong.imlib.model.Group;
import io.rong.imlib.model.UserInfo;

class RongDatabaseDao {
    private static final String TAG = "RongDatabaseDao";
    private SQLiteDatabase db;
    private final String discussionsTable = "discussions";
    private final String groupUsersTable = "group_users";
    private final String groupsTable = "groups";
    private RongUserCacheDatabaseHelper rongUserCacheDatabaseHelper;
    private final String usersTable = "users";

    RongDatabaseDao() {
    }

    void open(Context context, String str, String str2) {
        RongUserCacheDatabaseHelper.setDbPath(context, str, str2);
        this.rongUserCacheDatabaseHelper = new RongUserCacheDatabaseHelper(context);
        this.db = this.rongUserCacheDatabaseHelper.getReadableDatabase();
    }

    void close() {
        if (this.db != null) {
            this.db.close();
            this.db = null;
        }
    }

    protected void finalize() throws Throwable {
        if (this.db != null) {
            this.db.close();
        }
        super.finalize();
    }

    UserInfo getUserInfo(String str) {
        UserInfo userInfo = null;
        if (str == null) {
            RLog.m19424w(TAG, "getUserInfo userId is invalid");
        } else if (this.db == null) {
            RLog.m19424w(TAG, "getUserInfo db is invalid");
        } else {
            Cursor query = this.db.query("users", null, "id = ?", new String[]{str}, null, null, null);
            if (query.moveToFirst()) {
                userInfo = new UserInfo(query.getString(query.getColumnIndex("id")), query.getString(query.getColumnIndex("name")), Uri.parse(query.getString(query.getColumnIndex("portrait"))));
            }
            query.close();
        }
        return userInfo;
    }

    synchronized void insertUserInfo(UserInfo userInfo) {
        if (userInfo != null) {
            if (userInfo.getUserId() != null) {
                if (this.db == null) {
                    RLog.m19424w(TAG, "insertUserInfo db is invalid");
                } else {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("id", userInfo.getUserId());
                    contentValues.put("name", userInfo.getName());
                    contentValues.put("portrait", userInfo.getPortraitUri() + "");
                    this.db.insert("users", null, contentValues);
                }
            }
        }
        RLog.m19424w(TAG, "insertUserInfo userId is invalid");
    }

    synchronized void updateUserInfo(UserInfo userInfo) {
        if (userInfo != null) {
            if (userInfo.getUserId() != null) {
                if (this.db == null) {
                    RLog.m19424w(TAG, "updateUserInfo db is invalid");
                } else {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("id", userInfo.getUserId());
                    contentValues.put("name", userInfo.getName());
                    contentValues.put("portrait", userInfo.getPortraitUri() + "");
                    this.db.update("users", contentValues, "id = ?", new String[]{userInfo.getUserId()});
                }
            }
        }
        RLog.m19424w(TAG, "updateUserInfo userId is invalid");
    }

    synchronized void putUserInfo(UserInfo userInfo) {
        if (userInfo != null) {
            if (userInfo.getUserId() != null) {
                if (this.db == null) {
                    RLog.m19424w(TAG, "putUserInfo db is invalid");
                } else {
                    this.db.execSQL("replace into users (id, name, portrait) values (?, ?, ?)", new String[]{userInfo.getUserId(), userInfo.getName(), userInfo.getPortraitUri() + ""});
                }
            }
        }
        RLog.m19424w(TAG, "putUserInfo userId is invalid");
    }

    GroupUserInfo getGroupUserInfo(String str, String str2) {
        GroupUserInfo groupUserInfo = null;
        if (str2 == null || str == null) {
            RLog.m19424w(TAG, "getGroupUserInfo parameter is invalid");
        } else if (this.db == null) {
            RLog.m19424w(TAG, "getGroupUserInfo db is invalid");
        } else {
            Cursor query = this.db.query("group_users", null, "group_id = ? and user_id = ?", new String[]{str, str2}, null, null, null);
            if (query.moveToFirst()) {
                groupUserInfo = new GroupUserInfo(query.getString(query.getColumnIndex("group_id")), query.getString(query.getColumnIndex("user_id")), query.getString(query.getColumnIndex("nickname")));
            }
            query.close();
        }
        return groupUserInfo;
    }

    synchronized void insertGroupUserInfo(GroupUserInfo groupUserInfo) {
        if (groupUserInfo != null) {
            if (!(groupUserInfo.getGroupId() == null || groupUserInfo.getUserId() == null)) {
                if (this.db == null) {
                    RLog.m19424w(TAG, "insertGroupUserInfo db is invalid");
                } else {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("group_id", groupUserInfo.getGroupId());
                    contentValues.put("user_id", groupUserInfo.getUserId());
                    contentValues.put("nickname", groupUserInfo.getNickname());
                    this.db.insert("group_users", null, contentValues);
                }
            }
        }
        RLog.m19424w(TAG, "insertGroupUserInfo parameter is invalid");
    }

    synchronized void updateGroupUserInfo(GroupUserInfo groupUserInfo) {
        if (groupUserInfo != null) {
            if (!(groupUserInfo.getGroupId() == null || groupUserInfo.getUserId() == null)) {
                if (this.db == null) {
                    RLog.m19424w(TAG, "updateGroupUserInfo db is invalid");
                } else {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("group_id", groupUserInfo.getGroupId());
                    contentValues.put("user_id", groupUserInfo.getUserId());
                    contentValues.put("nickname", groupUserInfo.getNickname());
                    this.db.update("group_users", contentValues, "group_id=? and user_id=?", new String[]{groupUserInfo.getGroupId(), groupUserInfo.getUserId()});
                }
            }
        }
        RLog.m19424w(TAG, "updateGroupUserInfo parameter is invalid");
    }

    synchronized void putGroupUserInfo(GroupUserInfo groupUserInfo) {
        if (groupUserInfo != null) {
            if (!(groupUserInfo.getGroupId() == null || groupUserInfo.getUserId() == null)) {
                if (this.db == null) {
                    RLog.m19424w(TAG, "putGroupUserInfo db is invalid");
                } else {
                    this.db.execSQL("delete from group_users where group_id=? and user_id=?", new String[]{groupUserInfo.getGroupId(), groupUserInfo.getUserId()});
                    this.db.execSQL("insert into group_users (group_id, user_id, nickname) values (?, ?, ?)", new String[]{groupUserInfo.getGroupId(), groupUserInfo.getUserId(), groupUserInfo.getNickname()});
                }
            }
        }
        RLog.m19424w(TAG, "putGroupUserInfo parameter is invalid");
    }

    Group getGroupInfo(String str) {
        Group group = null;
        if (str == null) {
            RLog.m19424w(TAG, "getGroupInfo parameter is invalid");
        } else if (this.db == null) {
            RLog.m19424w(TAG, "getGroupInfo db is invalid");
        } else {
            Cursor query = this.db.query("groups", null, "id = ?", new String[]{str}, null, null, null);
            if (query.moveToFirst()) {
                group = new Group(query.getString(query.getColumnIndex("id")), query.getString(query.getColumnIndex("name")), Uri.parse(query.getString(query.getColumnIndex("portrait"))));
            }
            query.close();
        }
        return group;
    }

    synchronized void insertGroupInfo(Group group) {
        if (group != null) {
            if (group.getId() != null) {
                if (this.db == null) {
                    RLog.m19424w(TAG, "insertGroupInfo db is invalid");
                } else {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("id", group.getId());
                    contentValues.put("name", group.getName());
                    contentValues.put("portrait", group.getPortraitUri() + "");
                    this.db.insert("groups", null, contentValues);
                }
            }
        }
        RLog.m19424w(TAG, "insertGroupInfo parameter is invalid");
    }

    synchronized void updateGroupInfo(Group group) {
        if (group != null) {
            if (group.getId() != null) {
                if (this.db == null) {
                    RLog.m19424w(TAG, "updateGroupInfo db is invalid");
                } else {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("id", group.getId());
                    contentValues.put("name", group.getName());
                    contentValues.put("portrait", group.getPortraitUri() + "");
                    this.db.update("groups", contentValues, "id = ?", new String[]{group.getId()});
                }
            }
        }
        RLog.m19424w(TAG, "updateGroupInfo parameter is invalid");
    }

    synchronized void putGroupInfo(Group group) {
        if (group != null) {
            if (group.getId() != null) {
                if (this.db == null) {
                    RLog.m19424w(TAG, "putGroupInfo db is invalid");
                } else {
                    this.db.execSQL("replace into groups (id, name, portrait) values (?, ?, ?)", new String[]{group.getId(), group.getName(), group.getPortraitUri() + ""});
                }
            }
        }
        RLog.m19424w(TAG, "putGroupInfo parameter is invalid");
    }

    Discussion getDiscussionInfo(String str) {
        Discussion discussion = null;
        if (str == null) {
            RLog.m19424w(TAG, "getDiscussionInfo parameter is invalid");
        } else if (this.db == null) {
            RLog.m19424w(TAG, "getDiscussionInfo db is invalid");
        } else {
            Cursor query = this.db.query("discussions", null, "id = ?", new String[]{str}, null, null, null);
            if (query.moveToFirst()) {
                discussion = new Discussion(query.getString(query.getColumnIndex("id")), query.getString(query.getColumnIndex("name")));
            }
            query.close();
        }
        return discussion;
    }

    synchronized void insertDiscussionInfo(Discussion discussion) {
        if (discussion != null) {
            if (discussion.getId() != null) {
                if (this.db == null) {
                    RLog.m19424w(TAG, "insertDiscussionInfo db is invalid");
                } else {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("id", discussion.getId());
                    contentValues.put("name", discussion.getName());
                    contentValues.put("portrait", "");
                    this.db.insert("discussions", null, contentValues);
                }
            }
        }
        RLog.m19424w(TAG, "insertDiscussionInfo parameter is invalid");
    }

    synchronized void updateDiscussionInfo(Discussion discussion) {
        if (discussion != null) {
            if (discussion.getId() != null) {
                if (this.db == null) {
                    RLog.m19424w(TAG, "updateDiscussionInfo db is invalid");
                } else {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("id", discussion.getId());
                    contentValues.put("name", discussion.getName());
                    contentValues.put("portrait", "");
                    this.db.update("discussions", contentValues, "id = ?", new String[]{discussion.getId()});
                }
            }
        }
        RLog.m19424w(TAG, "updateDiscussionInfo parameter is invalid");
    }

    synchronized void putDiscussionInfo(Discussion discussion) {
        if (discussion != null) {
            if (discussion.getId() != null) {
                if (this.db == null) {
                    RLog.m19424w(TAG, "putDiscussionInfo db is invalid");
                } else {
                    this.db.execSQL("replace into discussions (id, name, portrait) values (?, ?, ?)", new String[]{discussion.getId(), discussion.getName(), ""});
                }
            }
        }
        RLog.m19424w(TAG, "putDiscussionInfo parameter is invalid");
    }
}
