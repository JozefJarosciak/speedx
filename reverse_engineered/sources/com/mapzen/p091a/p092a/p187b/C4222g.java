package com.mapzen.p091a.p092a.p187b;

import android.content.Context;
import android.location.Location;
import android.os.Handler;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import com.mapzen.p091a.p092a.p187b.C4216e.C4214a;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/* compiled from: MockEngine */
/* renamed from: com.mapzen.a.a.b.g */
public class C4222g extends C4216e {
    /* renamed from: a */
    protected C4221a f14870a;
    /* renamed from: b */
    private Location f14871b;
    /* renamed from: c */
    private File f14872c;

    /* compiled from: MockEngine */
    /* renamed from: com.mapzen.a.a.b.g$a */
    protected class C4221a extends Thread {
        /* renamed from: a */
        final /* synthetic */ C4222g f14867a;
        /* renamed from: b */
        private boolean f14868b;
        /* renamed from: c */
        private Location f14869c;

        protected C4221a(C4222g c4222g) {
            this.f14867a = c4222g;
        }

        /* renamed from: a */
        public void m16755a() {
            this.f14868b = true;
            interrupt();
        }

        public void run() {
            NodeList nodeList;
            NodeList nodeList2;
            ParserConfigurationException e;
            SAXException e2;
            IOException e3;
            XPathExpressionException e4;
            DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
            XPath newXPath = XPathFactory.newInstance().newXPath();
            String str = "//trkpt";
            str = "//speed";
            try {
                Document parse = newInstance.newDocumentBuilder().parse(this.f14867a.f14872c);
                nodeList = (NodeList) newXPath.compile("//trkpt").evaluate(parse, XPathConstants.NODESET);
                try {
                    nodeList2 = (NodeList) newXPath.compile("//speed").evaluate(parse, XPathConstants.NODESET);
                } catch (ParserConfigurationException e5) {
                    e = e5;
                    e.printStackTrace();
                    nodeList2 = null;
                    m16752a(nodeList, nodeList2);
                } catch (SAXException e6) {
                    e2 = e6;
                    e2.printStackTrace();
                    nodeList2 = null;
                    m16752a(nodeList, nodeList2);
                } catch (IOException e7) {
                    e3 = e7;
                    e3.printStackTrace();
                    nodeList2 = null;
                    m16752a(nodeList, nodeList2);
                } catch (XPathExpressionException e8) {
                    e4 = e8;
                    e4.printStackTrace();
                    nodeList2 = null;
                    m16752a(nodeList, nodeList2);
                }
            } catch (ParserConfigurationException e9) {
                e = e9;
                nodeList = null;
                e.printStackTrace();
                nodeList2 = null;
                m16752a(nodeList, nodeList2);
            } catch (SAXException e10) {
                e2 = e10;
                nodeList = null;
                e2.printStackTrace();
                nodeList2 = null;
                m16752a(nodeList, nodeList2);
            } catch (IOException e11) {
                e3 = e11;
                nodeList = null;
                e3.printStackTrace();
                nodeList2 = null;
                m16752a(nodeList, nodeList2);
            } catch (XPathExpressionException e12) {
                e4 = e12;
                nodeList = null;
                e4.printStackTrace();
                nodeList2 = null;
                m16752a(nodeList, nodeList2);
            }
            m16752a(nodeList, nodeList2);
        }

        /* renamed from: a */
        private void m16752a(NodeList nodeList, NodeList nodeList2) {
            if (nodeList != null) {
                for (int i = 0; i < nodeList.getLength(); i++) {
                    m16751a(m16750a(nodeList, nodeList2, i));
                    m16754b();
                }
            }
        }

        /* renamed from: a */
        private Location m16750a(NodeList nodeList, NodeList nodeList2, int i) {
            Node item = nodeList.item(i);
            String nodeValue = item.getAttributes().getNamedItem(MapboxEvent.KEY_LATITUDE).getNodeValue();
            String nodeValue2 = item.getAttributes().getNamedItem("lon").getNodeValue();
            Location location = new Location("mock");
            location.setLatitude(Double.parseDouble(nodeValue));
            location.setLongitude(Double.parseDouble(nodeValue2));
            location.setTime(System.currentTimeMillis());
            if (!(nodeList2.item(i) == null || nodeList2.item(i).getFirstChild() == null)) {
                location.setSpeed(Float.parseFloat(nodeList2.item(i).getFirstChild().getNodeValue()));
            }
            if (this.f14869c != null) {
                location.setBearing(this.f14869c.bearingTo(location));
            }
            this.f14869c = location;
            return location;
        }

        /* renamed from: b */
        private void m16754b() {
            if (this.f14867a.m16739f() != null) {
                try {
                    Thread.sleep(this.f14867a.m16739f().m16714b());
                } catch (InterruptedException e) {
                    this.f14868b = true;
                }
            }
        }

        /* renamed from: a */
        private void m16751a(final Location location) {
            new Handler(this.f14867a.m16737d().getMainLooper()).post(new Runnable(this) {
                /* renamed from: b */
                final /* synthetic */ C4221a f14866b;

                public void run() {
                    if (!this.f14866b.f14868b) {
                        this.f14866b.f14867a.m16758a(location);
                    }
                }
            });
        }
    }

    public C4222g(Context context, C4214a c4214a) {
        super(context, c4214a);
    }

    /* renamed from: a */
    public Location mo5988a() {
        return this.f14871b;
    }

    /* renamed from: b */
    protected void mo5989b() {
        if (this.f14872c != null) {
            this.f14870a = new C4221a(this);
            this.f14870a.start();
        }
    }

    /* renamed from: c */
    protected void mo5990c() {
        if (this.f14870a != null) {
            this.f14870a.m16755a();
        }
    }

    /* renamed from: a */
    public void m16758a(Location location) {
        this.f14871b = location;
        if (m16738e() != null) {
            m16738e().mo5987a(this, location);
        }
    }

    /* renamed from: a */
    public void m16759a(File file) {
        this.f14872c = file;
    }
}
