package com.beastbikes.framework.persistence;

import java.io.Serializable;

public interface PersistentObject extends Serializable {
    String getId();
}
