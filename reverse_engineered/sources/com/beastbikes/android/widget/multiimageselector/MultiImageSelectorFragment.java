package com.beastbikes.android.widget.multiimageselector;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore.Images.Media;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.ListPopupWindow;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.widget.multiimageselector.p170a.C2715a;
import com.beastbikes.android.widget.multiimageselector.p170a.C2717b;
import com.beastbikes.android.widget.multiimageselector.p171b.C2718a;
import com.beastbikes.android.widget.multiimageselector.p171b.C2719b;
import com.beastbikes.android.widget.multiimageselector.utils.C2729a;
import com.beastbikes.android.widget.multiimageselector.utils.C2730b;
import com.j256.ormlite.field.FieldType;
import com.squareup.picasso.Picasso;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MultiImageSelectorFragment extends Fragment {
    /* renamed from: a */
    private boolean f12649a = false;
    /* renamed from: b */
    private ArrayList<String> f12650b = new ArrayList();
    /* renamed from: c */
    private ArrayList<C2718a> f12651c = new ArrayList();
    /* renamed from: d */
    private GridView f12652d;
    /* renamed from: e */
    private C2704a f12653e;
    /* renamed from: f */
    private C2717b f12654f;
    /* renamed from: g */
    private C2715a f12655g;
    /* renamed from: h */
    private ListPopupWindow f12656h;
    /* renamed from: i */
    private TextView f12657i;
    /* renamed from: j */
    private TextView f12658j;
    /* renamed from: k */
    private Button f12659k;
    /* renamed from: l */
    private View f12660l;
    /* renamed from: m */
    private int f12661m;
    /* renamed from: n */
    private boolean f12662n = false;
    /* renamed from: o */
    private boolean f12663o = false;
    /* renamed from: p */
    private int f12664p;
    /* renamed from: q */
    private int f12665q;
    /* renamed from: r */
    private File f12666r;
    /* renamed from: s */
    private boolean f12667s = true;
    /* renamed from: t */
    private LoaderCallbacks<Cursor> f12668t = new C27138(this);

    /* renamed from: com.beastbikes.android.widget.multiimageselector.MultiImageSelectorFragment$a */
    public interface C2704a {
        /* renamed from: a */
        void mo3543a(File file);

        /* renamed from: a */
        void mo3544a(String str);

        /* renamed from: b */
        void mo3545b(String str);

        /* renamed from: c */
        void mo3546c(String str);
    }

    /* renamed from: com.beastbikes.android.widget.multiimageselector.MultiImageSelectorFragment$1 */
    class C27051 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ MultiImageSelectorFragment f12636a;

        C27051(MultiImageSelectorFragment multiImageSelectorFragment) {
            this.f12636a = multiImageSelectorFragment;
        }

        public void onClick(View view) {
            if (this.f12636a.f12656h == null) {
                this.f12636a.m13365a(this.f12636a.f12664p, this.f12636a.f12665q);
            }
            if (this.f12636a.f12656h.isShowing()) {
                this.f12636a.f12656h.dismiss();
                return;
            }
            this.f12636a.f12656h.show();
            int a = this.f12636a.f12655g.m13387a();
            if (a != 0) {
                a--;
            }
            this.f12636a.f12656h.getListView().setSelection(a);
        }
    }

    /* renamed from: com.beastbikes.android.widget.multiimageselector.MultiImageSelectorFragment$2 */
    class C27062 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ MultiImageSelectorFragment f12637a;

        C27062(MultiImageSelectorFragment multiImageSelectorFragment) {
            this.f12637a = multiImageSelectorFragment;
        }

        public void onClick(View view) {
            Intent intent = new Intent(this.f12637a.getActivity(), LookImageActivity.class);
            intent.putStringArrayListExtra("images", this.f12637a.f12650b);
            this.f12637a.startActivity(intent);
        }
    }

    /* renamed from: com.beastbikes.android.widget.multiimageselector.MultiImageSelectorFragment$3 */
    class C27073 implements OnScrollListener {
        /* renamed from: a */
        final /* synthetic */ MultiImageSelectorFragment f12638a;

        C27073(MultiImageSelectorFragment multiImageSelectorFragment) {
            this.f12638a = multiImageSelectorFragment;
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
            Picasso with = Picasso.with(this.f12638a.getActivity());
            if (i == 0 || i == 1) {
                with.resumeTag(this.f12638a.getActivity());
            } else {
                with.pauseTag(this.f12638a.getActivity());
            }
            if (i == 0) {
                this.f12638a.f12657i.setVisibility(8);
            } else if (i == 2) {
                this.f12638a.f12657i.setVisibility(0);
            }
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            if (this.f12638a.f12657i.getVisibility() == 0) {
                C2719b c2719b = (C2719b) ((ListAdapter) absListView.getAdapter()).getItem(i + 1 == ((ListAdapter) absListView.getAdapter()).getCount() ? ((ListAdapter) absListView.getAdapter()).getCount() - 1 : i + 1);
                if (c2719b != null) {
                    this.f12638a.f12657i.setText(C2730b.m13462a(c2719b.f12695a));
                }
            }
        }
    }

    /* renamed from: com.beastbikes.android.widget.multiimageselector.MultiImageSelectorFragment$4 */
    class C27084 implements OnGlobalLayoutListener {
        /* renamed from: a */
        final /* synthetic */ MultiImageSelectorFragment f12639a;

        C27084(MultiImageSelectorFragment multiImageSelectorFragment) {
            this.f12639a = multiImageSelectorFragment;
        }

        @TargetApi(16)
        public void onGlobalLayout() {
            int width = this.f12639a.f12652d.getWidth();
            int height = this.f12639a.f12652d.getHeight();
            this.f12639a.f12664p = width;
            this.f12639a.f12665q = height;
            height = width / this.f12639a.getResources().getDimensionPixelOffset(C1373R.dimen.image_size);
            this.f12639a.f12654f.m13397a((width - (this.f12639a.getResources().getDimensionPixelOffset(C1373R.dimen.space_size) * (height - 1))) / height);
            if (VERSION.SDK_INT >= 16) {
                this.f12639a.f12652d.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            } else {
                this.f12639a.f12652d.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        }
    }

    /* renamed from: com.beastbikes.android.widget.multiimageselector.MultiImageSelectorFragment$6 */
    class C27116 implements OnItemClickListener {
        /* renamed from: a */
        final /* synthetic */ MultiImageSelectorFragment f12645a;

        C27116(MultiImageSelectorFragment multiImageSelectorFragment) {
            this.f12645a = multiImageSelectorFragment;
        }

        public void onItemClick(final AdapterView<?> adapterView, View view, final int i, long j) {
            this.f12645a.f12655g.m13390b(i);
            new Handler().postDelayed(new Runnable(this) {
                /* renamed from: c */
                final /* synthetic */ C27116 f12644c;

                public void run() {
                    this.f12644c.f12645a.f12656h.dismiss();
                    if (i == 0) {
                        this.f12644c.f12645a.getActivity().getSupportLoaderManager().restartLoader(0, null, this.f12644c.f12645a.f12668t);
                        this.f12644c.f12645a.f12658j.setText(C1373R.string.folder_all);
                        if (this.f12644c.f12645a.f12663o) {
                            this.f12644c.f12645a.f12654f.m13404b(true);
                        } else {
                            this.f12644c.f12645a.f12654f.m13404b(false);
                        }
                    } else {
                        C2718a c2718a = (C2718a) adapterView.getAdapter().getItem(i);
                        if (c2718a != null) {
                            this.f12644c.f12645a.f12654f.m13400a(c2718a.f12694d);
                            this.f12644c.f12645a.f12658j.setText(c2718a.f12691a);
                            if (this.f12644c.f12645a.f12650b != null && this.f12644c.f12645a.f12650b.size() > 0) {
                                this.f12644c.f12645a.f12654f.m13399a(this.f12644c.f12645a.f12650b);
                            }
                        }
                        this.f12644c.f12645a.f12654f.m13404b(false);
                    }
                    this.f12644c.f12645a.f12652d.smoothScrollToPosition(0);
                }
            }, 100);
        }
    }

    /* renamed from: com.beastbikes.android.widget.multiimageselector.MultiImageSelectorFragment$7 */
    class C27127 implements OnGlobalLayoutListener {
        /* renamed from: a */
        final /* synthetic */ MultiImageSelectorFragment f12646a;

        C27127(MultiImageSelectorFragment multiImageSelectorFragment) {
            this.f12646a = multiImageSelectorFragment;
        }

        @TargetApi(16)
        public void onGlobalLayout() {
            int height = this.f12646a.f12652d.getHeight();
            int width = this.f12646a.f12652d.getWidth() / this.f12646a.getResources().getDimensionPixelOffset(C1373R.dimen.image_size);
            this.f12646a.f12654f.m13397a((this.f12646a.f12652d.getWidth() - (this.f12646a.getResources().getDimensionPixelOffset(C1373R.dimen.space_size) * (width - 1))) / width);
            if (this.f12646a.f12656h != null) {
                this.f12646a.f12656h.setHeight((height * 5) / 8);
            }
            if (VERSION.SDK_INT >= 16) {
                this.f12646a.f12652d.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            } else {
                this.f12646a.f12652d.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        }
    }

    /* renamed from: com.beastbikes.android.widget.multiimageselector.MultiImageSelectorFragment$8 */
    class C27138 implements LoaderCallbacks<Cursor> {
        /* renamed from: a */
        final /* synthetic */ MultiImageSelectorFragment f12647a;
        /* renamed from: b */
        private final String[] f12648b = new String[]{"_data", "_display_name", "date_added", FieldType.FOREIGN_ID_FIELD_SUFFIX};

        C27138(MultiImageSelectorFragment multiImageSelectorFragment) {
            this.f12647a = multiImageSelectorFragment;
        }

        public /* synthetic */ void onLoadFinished(Loader loader, Object obj) {
            m13361a(loader, (Cursor) obj);
        }

        public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
            if (i == 0) {
                return new CursorLoader(this.f12647a.getActivity(), Media.EXTERNAL_CONTENT_URI, this.f12648b, null, null, this.f12648b[2] + " DESC");
            } else if (i != 1) {
                return null;
            } else {
                return new CursorLoader(this.f12647a.getActivity(), Media.EXTERNAL_CONTENT_URI, this.f12648b, this.f12648b[0] + " like '%" + bundle.getString("path") + "%'", null, this.f12648b[2] + " DESC");
            }
        }

        /* renamed from: a */
        public void m13361a(Loader<Cursor> loader, Cursor cursor) {
            if (cursor != null) {
                List arrayList = new ArrayList();
                if (cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    do {
                        String string = cursor.getString(cursor.getColumnIndexOrThrow(this.f12648b[0]));
                        C2719b c2719b = new C2719b(string, cursor.getString(cursor.getColumnIndexOrThrow(this.f12648b[1])), cursor.getLong(cursor.getColumnIndexOrThrow(this.f12648b[2])));
                        arrayList.add(c2719b);
                        if (!this.f12647a.f12662n) {
                            File parentFile = new File(string).getParentFile();
                            C2718a c2718a = new C2718a();
                            c2718a.f12691a = parentFile.getName();
                            c2718a.f12692b = parentFile.getAbsolutePath();
                            c2718a.f12693c = c2719b;
                            if (this.f12647a.f12651c.contains(c2718a)) {
                                ((C2718a) this.f12647a.f12651c.get(this.f12647a.f12651c.indexOf(c2718a))).f12694d.add(c2719b);
                            } else {
                                List arrayList2 = new ArrayList();
                                arrayList2.add(c2719b);
                                c2718a.f12694d = arrayList2;
                                this.f12647a.f12651c.add(c2718a);
                            }
                        }
                    } while (cursor.moveToNext());
                    this.f12647a.f12654f.m13400a(arrayList);
                    if (this.f12647a.f12650b != null && this.f12647a.f12650b.size() > 0) {
                        this.f12647a.f12654f.m13399a(this.f12647a.f12650b);
                    }
                    this.f12647a.f12655g.m13389a(this.f12647a.f12651c);
                    this.f12647a.f12662n = true;
                }
            }
        }

        public void onLoaderReset(Loader<Cursor> loader) {
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.f12653e = (C2704a) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("The Activity must implement MultiImageSelectorFragment.Callback interface...");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(C1373R.layout.multi_image_selector_fragment_multi_image, viewGroup, false);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        boolean z = true;
        super.onViewCreated(view, bundle);
        this.f12661m = getArguments().getInt("max_select_count");
        final int i = getArguments().getInt("select_count_mode");
        if (i == 1) {
            ArrayList stringArrayList = getArguments().getStringArrayList("default_result");
            if (stringArrayList != null && stringArrayList.size() > 0) {
                this.f12650b = stringArrayList;
            }
        }
        this.f12667s = getArguments().getBoolean("is_max", true);
        this.f12663o = getArguments().getBoolean("show_camera", true);
        this.f12654f = new C2717b(getActivity(), this.f12663o);
        this.f12649a = getArguments().getBoolean("gallery_full", false);
        C2717b c2717b = this.f12654f;
        if (i != 1) {
            z = false;
        }
        c2717b.m13401a(z);
        this.f12660l = view.findViewById(C1373R.id.footer);
        this.f12657i = (TextView) view.findViewById(C1373R.id.timeline_area);
        this.f12657i.setVisibility(8);
        this.f12658j = (TextView) view.findViewById(C1373R.id.category_btn);
        this.f12658j.setText(C1373R.string.folder_all);
        this.f12658j.setOnClickListener(new C27051(this));
        this.f12659k = (Button) view.findViewById(C1373R.id.preview);
        if (this.f12650b == null || this.f12650b.size() <= 0) {
            this.f12659k.setText(C1373R.string.preview);
            this.f12659k.setEnabled(false);
        }
        this.f12659k.setOnClickListener(new C27062(this));
        this.f12652d = (GridView) view.findViewById(C1373R.id.grid);
        this.f12652d.setOnScrollListener(new C27073(this));
        this.f12652d.setAdapter(this.f12654f);
        this.f12652d.getViewTreeObserver().addOnGlobalLayoutListener(new C27084(this));
        this.f12652d.setOnItemClickListener(new OnItemClickListener(this) {
            /* renamed from: b */
            final /* synthetic */ MultiImageSelectorFragment f12641b;

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (!this.f12641b.f12654f.m13402a()) {
                    this.f12641b.m13368a((C2719b) adapterView.getAdapter().getItem(i), i);
                } else if (i == 0) {
                    this.f12641b.m13364a();
                } else {
                    this.f12641b.m13368a((C2719b) adapterView.getAdapter().getItem(i), i);
                }
            }
        });
        this.f12655g = new C2715a(getActivity());
    }

    /* renamed from: a */
    private void m13365a(int i, int i2) {
        this.f12656h = new ListPopupWindow(getActivity());
        this.f12656h.setBackgroundDrawable(new ColorDrawable(0));
        this.f12656h.setAdapter(this.f12655g);
        this.f12656h.setContentWidth(i);
        this.f12656h.setWidth(i);
        this.f12656h.setHeight((i2 * 5) / 8);
        this.f12656h.setAnchorView(this.f12660l);
        this.f12656h.setModal(true);
        this.f12656h.setOnItemClickListener(new C27116(this));
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        getActivity().getSupportLoaderManager().initLoader(0, null, this.f12668t);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 100) {
            return;
        }
        if (i2 == -1 && this.f12666r != null && this.f12653e != null) {
            this.f12653e.mo3543a(this.f12666r);
        } else if (this.f12666r != null && this.f12666r.exists()) {
            this.f12666r.delete();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (this.f12656h != null && this.f12656h.isShowing()) {
            this.f12656h.dismiss();
        }
        this.f12652d.getViewTreeObserver().addOnGlobalLayoutListener(new C27127(this));
        super.onConfigurationChanged(configuration);
    }

    /* renamed from: a */
    private void m13364a() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            this.f12666r = C2729a.m13459a(getActivity());
            intent.putExtra("output", Uri.fromFile(this.f12666r));
            startActivityForResult(intent, 100);
            return;
        }
        Toast.makeText(getActivity(), C1373R.string.msg_no_camera, 0).show();
    }

    /* renamed from: a */
    private void m13368a(C2719b c2719b, int i) {
        if (c2719b == null) {
            return;
        }
        if (i == 1) {
            if (this.f12650b.contains(c2719b.f12695a)) {
                this.f12650b.remove(c2719b.f12695a);
                if (this.f12650b.size() != 0) {
                    this.f12659k.setEnabled(true);
                    this.f12659k.setText(getResources().getString(C1373R.string.preview) + "(" + this.f12650b.size() + ")");
                } else {
                    this.f12659k.setEnabled(false);
                    this.f12659k.setText(C1373R.string.preview);
                }
                if (this.f12653e != null) {
                    this.f12653e.mo3546c(c2719b.f12695a);
                }
            } else if (this.f12661m != this.f12650b.size()) {
                this.f12650b.add(c2719b.f12695a);
                this.f12659k.setEnabled(true);
                this.f12659k.setText(getResources().getString(C1373R.string.preview) + "(" + this.f12650b.size() + ")");
                if (this.f12653e != null) {
                    this.f12653e.mo3545b(c2719b.f12695a);
                }
            } else if (this.f12649a && this.f12667s) {
                Toast.makeText(getActivity(), C1373R.string.club_gallery_full, 0).show();
                return;
            } else {
                Toast.makeText(getActivity(), C1373R.string.msg_amount_limit, 0).show();
                return;
            }
            this.f12654f.m13398a(c2719b);
        } else if (i == 0 && this.f12653e != null) {
            this.f12653e.mo3544a(c2719b.f12695a);
        }
    }
}
