package p203u.aly;

/* compiled from: ShortStack */
/* renamed from: u.aly.aj */
public class aj {
    /* renamed from: a */
    private short[] f18583a;
    /* renamed from: b */
    private int f18584b = -1;

    public aj(int i) {
        this.f18583a = new short[i];
    }

    /* renamed from: a */
    public short m21174a() {
        short[] sArr = this.f18583a;
        int i = this.f18584b;
        this.f18584b = i - 1;
        return sArr[i];
    }

    /* renamed from: a */
    public void m21175a(short s) {
        if (this.f18583a.length == this.f18584b + 1) {
            m21173c();
        }
        short[] sArr = this.f18583a;
        int i = this.f18584b + 1;
        this.f18584b = i;
        sArr[i] = s;
    }

    /* renamed from: c */
    private void m21173c() {
        Object obj = new short[(this.f18583a.length * 2)];
        System.arraycopy(this.f18583a, 0, obj, 0, this.f18583a.length);
        this.f18583a = obj;
    }

    /* renamed from: b */
    public void m21176b() {
        this.f18584b = -1;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<ShortStack vector:[");
        for (int i = 0; i < this.f18583a.length; i++) {
            if (i != 0) {
                stringBuilder.append(" ");
            }
            if (i == this.f18584b) {
                stringBuilder.append(">>");
            }
            stringBuilder.append(this.f18583a[i]);
            if (i == this.f18584b) {
                stringBuilder.append("<<");
            }
        }
        stringBuilder.append("]>");
        return stringBuilder.toString();
    }
}
