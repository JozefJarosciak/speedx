package com.beastbikes.android.modules.cycling.club.dao;

import com.beastbikes.android.modules.cycling.club.dao.entity.Club;
import com.beastbikes.android.p057b.C1571f;
import com.beastbikes.framework.persistence.PersistenceException;
import com.beastbikes.framework.persistence.android.ormlite.C1380c;
import com.beastbikes.framework.persistence.android.ormlite.C1664a;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: ClubsDao */
/* renamed from: com.beastbikes.android.modules.cycling.club.dao.a */
public class C2061a extends C1664a<Club> implements C1571f {
    /* renamed from: a */
    private static final Logger f9361a = LoggerFactory.getLogger(C2061a.class);

    public C2061a(C1380c c1380c) {
        super(c1380c, Club.class);
    }

    /* renamed from: a */
    public Club m10619a(String str) throws PersistenceException {
        try {
            List b = super.m9025b("WHERE user_id=?", str);
            if (b == null || b.isEmpty()) {
                return null;
            }
            return (Club) b.get(0);
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: b */
    public void m10620b(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DELETE FROM ").append("club").append(" WHERE ");
        stringBuilder.append("club_id=?");
        try {
            m9021a(stringBuilder.toString(), str);
            f9361a.trace("Delete club " + str + "success");
        } catch (PersistenceException e) {
            f9361a.error("Delete club " + str + " error");
        }
    }
}
