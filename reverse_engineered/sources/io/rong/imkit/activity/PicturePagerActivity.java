package io.rong.imkit.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.alipay.sdk.cons.C0845b;
import com.avos.avoscloud.AVStatus;
import io.rong.common.RLog;
import io.rong.imageloader.core.DisplayImageOptions;
import io.rong.imageloader.core.DisplayImageOptions$Builder;
import io.rong.imageloader.core.ImageLoader;
import io.rong.imageloader.core.assist.FailReason;
import io.rong.imageloader.core.imageaware.ImageAware;
import io.rong.imageloader.core.imageaware.ImageViewAware;
import io.rong.imageloader.core.listener.ImageLoadingListener;
import io.rong.imageloader.core.listener.ImageLoadingProgressListener;
import io.rong.imkit.C4974R;
import io.rong.imkit.activity.AlbumBitmapCacheHelper.ILoadImageCallback;
import io.rong.imkit.widget.HackyViewPager;
import io.rong.imkit.widget.PicturePopupWindow;
import io.rong.imlib.RongCommonDefine.GetMessageDirection;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Conversation.ConversationType;
import io.rong.imlib.model.Message;
import io.rong.message.ImageMessage;
import io.rong.photoview.PhotoView;
import io.rong.photoview.PhotoViewAttacher$OnPhotoTapListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.http.HttpHost;

public class PicturePagerActivity extends Activity {
    private static final int IMAGE_MESSAGE_COUNT = 10;
    private static final String TAG = "PicturePagerActivity";
    private boolean isFirstTime = false;
    private ConversationType mConversationType;
    private ImageMessage mCurrentImageMessage;
    private int mCurrentIndex = 0;
    private int mCurrentMessageId;
    private ImageAware mDownloadingImageAware;
    private ImageAdapter mImageAdapter;
    private OnPageChangeListener mPageChangeListener = new C49821();
    private String mTargetId = null;
    private HackyViewPager mViewPager;

    /* renamed from: io.rong.imkit.activity.PicturePagerActivity$1 */
    class C49821 implements OnPageChangeListener {
        C49821() {
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageSelected(int i) {
            RLog.m19422i(PicturePagerActivity.TAG, "onPageSelected. position:" + i);
            PicturePagerActivity.this.mCurrentIndex = i;
            View findViewById = PicturePagerActivity.this.mViewPager.findViewById(i);
            if (findViewById != null) {
                PicturePagerActivity.this.mImageAdapter.updatePhotoView(i, findViewById);
            }
            if (i == PicturePagerActivity.this.mImageAdapter.getCount() - 1) {
                PicturePagerActivity.this.getConversationImageUris(PicturePagerActivity.this.mImageAdapter.getItem(i).getMessageId(), GetMessageDirection.BEHIND);
            } else if (i == 0) {
                PicturePagerActivity.this.getConversationImageUris(PicturePagerActivity.this.mImageAdapter.getItem(i).getMessageId(), GetMessageDirection.FRONT);
            }
        }

        public void onPageScrollStateChanged(int i) {
        }
    }

    private class ImageAdapter extends PagerAdapter {
        private ArrayList<ImageInfo> mImageList;
        private PicturePopupWindow menuWindow;
        private OnClickListener onMenuWindowClick;

        /* renamed from: io.rong.imkit.activity.PicturePagerActivity$ImageAdapter$1 */
        class C49841 implements OnClickListener {
            C49841() {
            }

            public void onClick(View view) {
                if (view.getId() == C4974R.id.rc_content) {
                    ImageAdapter.this.menuWindow.dismiss();
                } else {
                    ImageAdapter.this.menuWindow.dismiss();
                }
            }
        }

        /* renamed from: io.rong.imkit.activity.PicturePagerActivity$ImageAdapter$3 */
        class C49863 implements PhotoViewAttacher$OnPhotoTapListener {
            C49863() {
            }

            public void onPhotoTap(View view, float f, float f2) {
                PicturePagerActivity.this.finish();
            }

            public void onOutsidePhotoTap() {
            }
        }

        public class ViewHolder {
            PhotoView photoView;
            ProgressBar progressBar;
            TextView progressText;
        }

        private ImageAdapter() {
            this.mImageList = new ArrayList();
            this.onMenuWindowClick = new C49841();
        }

        private View newView(Context context, final ImageInfo imageInfo) {
            View inflate = LayoutInflater.from(context).inflate(C4974R.layout.rc_fr_image, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.progressBar = (ProgressBar) inflate.findViewById(C4974R.id.rc_progress);
            viewHolder.progressText = (TextView) inflate.findViewById(C4974R.id.rc_txt);
            viewHolder.photoView = (PhotoView) inflate.findViewById(C4974R.id.rc_photoView);
            viewHolder.photoView.setOnLongClickListener(new OnLongClickListener() {
                public boolean onLongClick(View view) {
                    Uri largeImageUri = imageInfo.getLargeImageUri();
                    File file = null;
                    if (largeImageUri != null) {
                        if (largeImageUri.getScheme().startsWith(HttpHost.DEFAULT_SCHEME_NAME) || largeImageUri.getScheme().startsWith(C0845b.f2060a)) {
                            file = ImageLoader.getInstance().getDiskCache().get(largeImageUri.toString());
                        } else {
                            file = new File(largeImageUri.getPath());
                        }
                    }
                    ImageAdapter.this.menuWindow = new PicturePopupWindow(PicturePagerActivity.this, file);
                    ImageAdapter.this.menuWindow.showAtLocation(view, 81, 0, 0);
                    ImageAdapter.this.menuWindow.setOutsideTouchable(false);
                    return false;
                }
            });
            viewHolder.photoView.setOnPhotoTapListener(new C49863());
            inflate.setTag(viewHolder);
            return inflate;
        }

        public void addData(ArrayList<ImageInfo> arrayList, boolean z) {
            if (arrayList != null && arrayList.size() != 0) {
                if (this.mImageList.size() == 0) {
                    this.mImageList.addAll(arrayList);
                } else if (z && !PicturePagerActivity.this.isFirstTime && !isDuplicate(((ImageInfo) arrayList.get(0)).getMessageId())) {
                    Collection arrayList2 = new ArrayList();
                    arrayList2.addAll(this.mImageList);
                    this.mImageList.clear();
                    this.mImageList.addAll(arrayList);
                    this.mImageList.addAll(this.mImageList.size(), arrayList2);
                } else if (!PicturePagerActivity.this.isFirstTime && !isDuplicate(((ImageInfo) arrayList.get(0)).getMessageId())) {
                    this.mImageList.addAll(this.mImageList.size(), arrayList);
                }
            }
        }

        private boolean isDuplicate(int i) {
            Iterator it = this.mImageList.iterator();
            while (it.hasNext()) {
                if (((ImageInfo) it.next()).getMessageId() == i) {
                    return true;
                }
            }
            return false;
        }

        public ImageInfo getItem(int i) {
            return (ImageInfo) this.mImageList.get(i);
        }

        public int getItemPosition(Object obj) {
            return -2;
        }

        public int getCount() {
            return this.mImageList.size();
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            RLog.m19422i(PicturePagerActivity.TAG, "instantiateItem.position:" + i);
            View newView = newView(viewGroup.getContext(), (ImageInfo) this.mImageList.get(i));
            updatePhotoView(i, newView);
            newView.setId(i);
            viewGroup.addView(newView);
            return newView;
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            RLog.m19422i(PicturePagerActivity.TAG, "destroyItem.position:" + i);
            ((ViewHolder) viewGroup.findViewById(i).getTag()).photoView.setImageURI(null);
            viewGroup.removeView((View) obj);
        }

        private void updatePhotoView(int i, View view) {
            final ViewHolder viewHolder = (ViewHolder) view.getTag();
            Uri largeImageUri = ((ImageInfo) this.mImageList.get(i)).getLargeImageUri();
            Uri thumbUri = ((ImageInfo) this.mImageList.get(i)).getThumbUri();
            if (largeImageUri == null || thumbUri == null) {
                RLog.m19420e(PicturePagerActivity.TAG, "large uri and thumbnail uri of the image should not be null.");
                return;
            }
            File file;
            if (largeImageUri.getScheme().startsWith(HttpHost.DEFAULT_SCHEME_NAME) || largeImageUri.getScheme().startsWith(C0845b.f2060a)) {
                file = ImageLoader.getInstance().getDiskCache().get(largeImageUri.toString());
            } else {
                file = new File(largeImageUri.getPath());
            }
            if (file != null && file.exists()) {
                AlbumBitmapCacheHelper.getInstance().addPathToShowlist(file.getAbsolutePath());
                Bitmap bitmap = AlbumBitmapCacheHelper.getInstance().getBitmap(file.getAbsolutePath(), 0, 0, new ILoadImageCallback() {
                    public void onLoadImageCallBack(Bitmap bitmap, String str, Object... objArr) {
                        if (bitmap != null) {
                            viewHolder.photoView.setImageBitmap(bitmap);
                        }
                    }
                }, Integer.valueOf(i));
                if (bitmap != null) {
                    viewHolder.photoView.setImageBitmap(bitmap);
                    return;
                }
                viewHolder.photoView.setImageDrawable(Drawable.createFromPath(thumbUri.getPath()));
            } else if (i != PicturePagerActivity.this.mCurrentIndex) {
                viewHolder.photoView.setImageDrawable(Drawable.createFromPath(thumbUri.getPath()));
            } else {
                ImageAware imageViewAware = new ImageViewAware(viewHolder.photoView);
                if (PicturePagerActivity.this.mDownloadingImageAware != null) {
                    ImageLoader.getInstance().cancelDisplayTask(PicturePagerActivity.this.mDownloadingImageAware);
                }
                ImageLoader.getInstance().displayImage(largeImageUri.toString(), imageViewAware, createDisplayImageOptions(thumbUri), new ImageLoadingListener() {
                    public void onLoadingStarted(String str, View view) {
                        viewHolder.progressText.setVisibility(0);
                        viewHolder.progressBar.setVisibility(0);
                        viewHolder.progressText.setText("0%");
                    }

                    public void onLoadingFailed(String str, View view, FailReason failReason) {
                        viewHolder.progressText.setVisibility(8);
                        viewHolder.progressBar.setVisibility(8);
                    }

                    public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                        viewHolder.progressText.setVisibility(8);
                        viewHolder.progressBar.setVisibility(8);
                    }

                    public void onLoadingCancelled(String str, View view) {
                        viewHolder.progressText.setVisibility(8);
                        viewHolder.progressText.setVisibility(8);
                    }
                }, new ImageLoadingProgressListener() {
                    public void onProgressUpdate(String str, View view, int i, int i2) {
                        viewHolder.progressText.setText(((i * 100) / i2) + "%");
                        if (i == i2) {
                            viewHolder.progressText.setVisibility(8);
                            viewHolder.progressBar.setVisibility(8);
                            return;
                        }
                        viewHolder.progressText.setVisibility(0);
                        viewHolder.progressBar.setVisibility(0);
                    }
                });
                PicturePagerActivity.this.mDownloadingImageAware = imageViewAware;
            }
        }

        private DisplayImageOptions createDisplayImageOptions(Uri uri) {
            DisplayImageOptions$Builder displayImageOptions$Builder = new DisplayImageOptions$Builder();
            Drawable createFromPath = Drawable.createFromPath(uri.getPath());
            return displayImageOptions$Builder.resetViewBeforeLoading(false).cacheInMemory(false).cacheOnDisk(true).bitmapConfig(Config.RGB_565).showImageForEmptyUri(createFromPath).showImageOnFail(createFromPath).showImageOnLoading(createFromPath).handler(new Handler()).build();
        }
    }

    private class ImageInfo {
        private Uri largeImageUri;
        private int messageId;
        private Uri thumbUri;

        ImageInfo(int i, Uri uri, Uri uri2) {
            this.messageId = i;
            this.thumbUri = uri;
            this.largeImageUri = uri2;
        }

        public int getMessageId() {
            return this.messageId;
        }

        public Uri getLargeImageUri() {
            return this.largeImageUri;
        }

        public Uri getThumbUri() {
            return this.thumbUri;
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C4974R.layout.rc_fr_photo);
        Message message = (Message) getIntent().getParcelableExtra(AVStatus.MESSAGE_TAG);
        this.mCurrentImageMessage = (ImageMessage) message.getContent();
        this.mConversationType = message.getConversationType();
        this.mCurrentMessageId = message.getMessageId();
        this.mTargetId = message.getTargetId();
        this.mViewPager = (HackyViewPager) findViewById(C4974R.id.viewpager);
        this.mViewPager.setOnPageChangeListener(this.mPageChangeListener);
        this.mImageAdapter = new ImageAdapter();
        this.isFirstTime = true;
        AlbumBitmapCacheHelper.init(this);
        getConversationImageUris(this.mCurrentMessageId, GetMessageDirection.FRONT);
        getConversationImageUris(this.mCurrentMessageId, GetMessageDirection.BEHIND);
    }

    private void getConversationImageUris(int i, final GetMessageDirection getMessageDirection) {
        if (this.mConversationType != null && !TextUtils.isEmpty(this.mTargetId)) {
            RongIMClient.getInstance().getHistoryMessages(this.mConversationType, this.mTargetId, "RC:ImgMsg", i, 10, getMessageDirection, new ResultCallback<List<Message>>() {
                public void onSuccess(List<Message> list) {
                    ArrayList arrayList = new ArrayList();
                    if (list != null) {
                        if (getMessageDirection.equals(GetMessageDirection.FRONT)) {
                            Collections.reverse(list);
                        }
                        for (int i = 0; i < list.size(); i++) {
                            Message message = (Message) list.get(i);
                            if (message.getContent() instanceof ImageMessage) {
                                ImageMessage imageMessage = (ImageMessage) message.getContent();
                                Uri remoteUri = imageMessage.getLocalUri() == null ? imageMessage.getRemoteUri() : imageMessage.getLocalUri();
                                if (!(imageMessage.getThumUri() == null || remoteUri == null)) {
                                    arrayList.add(new ImageInfo(message.getMessageId(), imageMessage.getThumUri(), remoteUri));
                                }
                            }
                        }
                    }
                    if (getMessageDirection.equals(GetMessageDirection.FRONT) && PicturePagerActivity.this.isFirstTime) {
                        arrayList.add(new ImageInfo(PicturePagerActivity.this.mCurrentMessageId, PicturePagerActivity.this.mCurrentImageMessage.getThumUri(), PicturePagerActivity.this.mCurrentImageMessage.getLocalUri() == null ? PicturePagerActivity.this.mCurrentImageMessage.getRemoteUri() : PicturePagerActivity.this.mCurrentImageMessage.getLocalUri()));
                        PicturePagerActivity.this.mImageAdapter.addData(arrayList, getMessageDirection.equals(GetMessageDirection.FRONT));
                        PicturePagerActivity.this.mViewPager.setAdapter(PicturePagerActivity.this.mImageAdapter);
                        PicturePagerActivity.this.isFirstTime = false;
                        PicturePagerActivity.this.mViewPager.setCurrentItem(arrayList.size() - 1);
                        PicturePagerActivity.this.mCurrentIndex = arrayList.size() - 1;
                    } else if (arrayList.size() > 0) {
                        PicturePagerActivity.this.mImageAdapter.addData(arrayList, getMessageDirection.equals(GetMessageDirection.FRONT));
                        PicturePagerActivity.this.mImageAdapter.notifyDataSetChanged();
                        if (getMessageDirection.equals(GetMessageDirection.FRONT)) {
                            PicturePagerActivity.this.mViewPager.setCurrentItem(arrayList.size());
                            PicturePagerActivity.this.mCurrentIndex = arrayList.size();
                        }
                    }
                }

                public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
                }
            });
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        AlbumBitmapCacheHelper.getInstance().uninit();
    }
}
