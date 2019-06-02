package io.rong.imlib;

import io.rong.imlib.model.CSGroupItem;
import io.rong.imlib.model.CustomServiceMode;
import java.util.List;

public interface ICustomServiceListener {
    void onError(int i, String str);

    void onModeChanged(CustomServiceMode customServiceMode);

    void onPullEvaluation(String str);

    void onQuit(String str);

    void onSelectGroup(List<CSGroupItem> list);

    void onSuccess(CustomServiceConfig customServiceConfig);
}
