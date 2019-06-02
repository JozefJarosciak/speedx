package com.beastbikes.android.modules.cycling.grid.dao;

import com.beastbikes.android.modules.cycling.grid.dao.entity.Grid;
import com.beastbikes.android.p057b.C1573h;
import com.beastbikes.framework.persistence.PersistenceException;
import com.beastbikes.framework.persistence.android.ormlite.C1380c;
import com.beastbikes.framework.persistence.android.ormlite.C1664a;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: GridDao */
/* renamed from: com.beastbikes.android.modules.cycling.grid.dao.a */
public class C2171a extends C1664a<Grid> implements C1573h {
    /* renamed from: a */
    private static final Logger f10163a = LoggerFactory.getLogger(C2171a.class);

    public C2171a(C1380c c1380c) {
        super(c1380c, Grid.class);
    }

    /* renamed from: a */
    public List<Grid> m11131a(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("WHERE user_id").append(" =?");
        try {
            return super.m9025b(stringBuilder.toString(), str);
        } catch (PersistenceException e) {
            f10163a.error("Query grid by userId is error, " + e);
            return null;
        }
    }
}
